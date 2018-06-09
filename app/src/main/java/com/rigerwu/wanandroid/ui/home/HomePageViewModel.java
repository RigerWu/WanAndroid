package com.rigerwu.wanandroid.ui.home;

import android.arch.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.data.model.main.ArticleListData;
import com.rigerwu.wanandroid.data.model.main.BannerData;
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
 * Created by RigerWu on 2018/5/29.
 */
public class HomePageViewModel extends BaseViewModel<HomePageNavigator> {


    private int mCurrentPage = 0;
    private int mTotalPage;
    private boolean mHasMore;

    private MutableLiveData<List<ArticleData>> mArticleListLiveData;
    private MutableLiveData<List<BannerData>> mBannerListLiveData;
    private List<ArticleData> mCurrentList;
    private PublishSubject<ListStatus> mRefreshState = PublishSubject.create();

    @Inject
    public HomePageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        if (mBannerListLiveData == null) {
            mBannerListLiveData = new MutableLiveData<>();
        }
        if (mArticleListLiveData == null) {
            mArticleListLiveData = new MutableLiveData<>();
        }
        initData();

    }

    public void refresh() {
        mCurrentPage = 0;
        fetchBannerData();
        fetchArticleData(mCurrentPage);
    }

    public void loadMore() {
        mCurrentPage++;
        fetchArticleData(mCurrentPage);
    }

    public boolean isHasMore() {
        return mHasMore;
    }

    private void initData() {
        LogUtils.i("HomePageViewModel.initData->:module init ====");
        getLoadingStatus().onNext(BaseFragment.STATUS_LOADING);

        getCompositeDisposable().add(getDataManager()
                .loadBanners()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(bannerData -> {
                    if (bannerData == null || bannerData.size() == 0) {
                        fetchBannerData();
                    } else {
                        mBannerListLiveData.setValue(bannerData);
                    }
                }));

        getCompositeDisposable().add(getDataManager()
                .loadArticles(0)
                // get initial data from db only once, or it will observe when db changed
                .take(1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(articleData -> {
                    if (articleData == null || articleData.size() == 0) {
                        fetchArticleData(0);
                    } else {
                        LogUtils.i("HomePageViewModel.initData->: room database call");
                        mCurrentList = articleData;
                        mArticleListLiveData.setValue(articleData);
                        getLoadingStatus().onNext(BaseFragment.STATUS_NOMAL);
                    }
                }));
    }

    private void fetchBannerData() {
        getCompositeDisposable().add(getDataManager()
                .getBanners()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(listBaseResponse -> {
                    if (listBaseResponse != null && listBaseResponse.getData() != null) {
                        mBannerListLiveData.setValue(listBaseResponse.getData());
                    }
                }));
    }

    private void fetchArticleData(int pageNum) {
        LogUtils.i("HomePageViewModel.fetchArticleData->加载页数为:" + pageNum);
        mRefreshState.onNext(pageNum == 0 ? ListStatus.REFRESHING : ListStatus.REFRESHING);
        getCompositeDisposable().add(getDataManager()
                .getHomeArticleList(pageNum)
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

    public MutableLiveData<List<ArticleData>> getArticleListLiveData() {
        return mArticleListLiveData;
    }

    public PublishSubject<ListStatus> getRefreshState() {
        return mRefreshState;
    }

    public MutableLiveData<List<BannerData>> getBannerListLiveData() {
        return mBannerListLiveData;
    }
}
