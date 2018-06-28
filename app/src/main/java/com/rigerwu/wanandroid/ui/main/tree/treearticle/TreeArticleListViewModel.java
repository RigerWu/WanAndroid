package com.rigerwu.wanandroid.ui.main.tree.treearticle;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.blankj.utilcode.util.LogUtils;
import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.data.model.main.ArticleListData;
import com.rigerwu.wanandroid.ui.base.BaseFragment;
import com.rigerwu.wanandroid.ui.base.BaseViewModel;
import com.rigerwu.wanandroid.ui.base.status.ListStatus;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by RigerWu on 2018/6/28.
 */
public class TreeArticleListViewModel extends BaseViewModel {

    private int mCid;

    private int mCurrentPage = 0;
    private int mTotalPage;
    private boolean mHasMore;

    public final ObservableList<ArticleData> mObservableArticleList = new ObservableArrayList<>();

    private MutableLiveData<List<ArticleData>> mArticleListLiveData;
    private List<ArticleData> mCurrentList;
    private PublishSubject<ListStatus> mRefreshState = PublishSubject.create();

    @Inject
    public TreeArticleListViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        if (mArticleListLiveData == null) {
            mArticleListLiveData = new MutableLiveData<>();
        }
        initData();
    }

    public void refresh() {
        mCurrentPage = 0;
        fetchArticleData(mCurrentPage);
    }

    public void loadMore() {
        mCurrentPage++;
        fetchArticleData(mCurrentPage);
    }

    private void initData() {
        getLoadingStatus().onNext(BaseFragment.STATUS_LOADING);

        getCompositeDisposable().add(getDataManager()
                .loadTreeArticle(mCid)
                // get initial data from db only once, or it will observe when db changed
                .take(1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(articleData -> {
                    if (articleData == null || articleData.size() == 0) {
                        fetchArticleData(0);
                    } else {
                        LogUtils.i("HomePageViewModel.initData->: room database call");
                        mHasMore = true;
                        mCurrentList = articleData;
                        mArticleListLiveData.setValue(articleData);
                        getLoadingStatus().onNext(BaseFragment.STATUS_NOMAL);
                    }
                }));
    }

    private void fetchArticleData(int pageNum) {
        LogUtils.i("HomePageViewModel.fetchArticleData->加载页数为:" + pageNum);
        mRefreshState.onNext(pageNum == 0 ? ListStatus.REFRESHING : ListStatus.REFRESHING);
        getCompositeDisposable().add(getDataManager()
                .getTreeArticleList(pageNum, mCid)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response != null && response.getData() != null) {
                        ArticleListData data = response.getData();
                        LogUtils.i("HomePageViewModel.fetchArticleData->:" + data.toString());
                        mTotalPage = data.getTotal() - 1;
                        mHasMore = mCurrentPage < mTotalPage;
                        handleFetchData(data.getDatas());
                        mRefreshState.onNext(pageNum == 0 ? ListStatus.REFRESH_FINISH : ListStatus.LOAD_MORE_FINISH);

                    } else {
                        getLoadingStatus().onNext(BaseFragment.STATUS_EMPTY);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    getLoadingStatus().onNext(BaseFragment.STATUS_NET_ERROR);
                })
        );
    }

    private void handleFetchData(List<ArticleData> newList) {

        if (mCurrentPage == 0) {
            mCurrentList = newList;
            mArticleListLiveData.setValue(mCurrentList);
            return;
        }

        Observable<List<ArticleData>> oldData = Observable.just(mCurrentList);
        Observable<List<ArticleData>> newData = Observable.just(newList);
        getCompositeDisposable().add(Observable.concat(oldData, newData)
                .flatMap((Function<List<ArticleData>, ObservableSource<ArticleData>>) Observable::fromIterable)
                .distinct(ArticleData::getId)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleData -> {
                    mCurrentList = articleData;
                    mArticleListLiveData.setValue(mCurrentList);
                }));
    }


    public void setCid(int cid) {
        mCid = cid;
    }

    public MutableLiveData<List<ArticleData>> getArticleListLiveData() {
        return mArticleListLiveData;
    }

    public PublishSubject<ListStatus> getRefreshState() {
        return mRefreshState;
    }

    public void setObservableList(List<ArticleData> articleDataList) {
        mObservableArticleList.clear();
        mObservableArticleList.addAll(articleDataList);
    }

    public boolean isHasMore() {
        return mHasMore;
    }
}
