package com.rigerwu.wanandroid.ui.home;

import android.arch.lifecycle.MutableLiveData;

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

import io.reactivex.subjects.PublishSubject;

/**
 * Created by RigerWu on 2018/5/29.
 */
public class HomePageViewModel extends BaseViewModel<HomePageNavigator> {

    private MutableLiveData<List<ArticleData>> mArticleListLiveData;

    private int currentPage = 0;
    private int totalPage;
    private boolean hasMore;
    private boolean hasData;

    private PublishSubject<ListStatus> mRefreshState = PublishSubject.create();

    @Inject
    public HomePageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mArticleListLiveData = new MutableLiveData<>();
        initData();
    }

    public void refresh() {
        currentPage = 0;
        fetchArticleData(currentPage);
    }

    public void loadMore() {
        currentPage++;
        fetchArticleData(currentPage);
    }

    public boolean isHasMore() {
        return hasMore;
    }

    private void initData() {
        getLoadingStatus().onNext(BaseFragment.STATUS_LOADING);
        getCompositeDisposable().add(getDataManager()
                .loadArticles(0)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(articleData -> {
                    if (articleData == null || articleData.size() == 0) {
                        fetchArticleData(0);
                    } else {
                        mArticleListLiveData.setValue(articleData);
                        getLoadingStatus().onNext(BaseFragment.STATUS_NOMAL);
                    }
                }));
    }

    private void fetchArticleData(int pageNum) {
        mRefreshState.onNext(pageNum == 0 ? ListStatus.REFRESHING : ListStatus.REFRESHING);
        getCompositeDisposable().add(getDataManager()
                .getHomeArticleList(pageNum)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response != null && response.getData() != null) {
                        ArticleListData data = response.getData();
                        LogUtils.i("HomePageViewModel.fetchArticleData->:" + data.toString());
                        currentPage = data.getCurPage();
                        totalPage = data.getTotal();
                        hasMore = currentPage < totalPage;
                        mArticleListLiveData.setValue(data.getDatas());
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

    public MutableLiveData<List<ArticleData>> getArticleListLiveData() {
        return mArticleListLiveData;
    }

    public PublishSubject<ListStatus> getRefreshState() {
        return mRefreshState;
    }
}
