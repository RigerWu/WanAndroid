package com.rigerwu.wanandroid.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.databinding.FragmentHomePageBinding;
import com.rigerwu.wanandroid.ui.base.BaseFragment;
import com.rigerwu.wanandroid.ui.base.status.ListStatus;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/27.
 */
public class HomePageFragment extends BaseFragment<FragmentHomePageBinding, HomePageViewModel> {

    @Inject
    HomePageViewModel mViewModel;
    @Inject
    ViewModelProvider.Factory mFactory;

    FragmentHomePageBinding mBinding;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private HomePageAdapter mHomePageAdapter;
    private boolean isRefresh = true;

    public static HomePageFragment newInstance() {
        Bundle args = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    public int getContainerId() {
        return R.id.refreshLayout;
    }

    @Override
    public HomePageViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mFactory).get(HomePageViewModel.class);
        return mViewModel;
    }


    @Override
    public void initDataAndEvent() {
        mViewModel.getCompositeDisposable().add(
                mViewModel.getLoadingStatus().subscribe(this::changeViewStatus));

        mViewModel.getCompositeDisposable().add(
                mViewModel.getRefreshState().subscribe(refreshState -> {
                    changeRefreshStatus(refreshState);
                })
        );

        mViewModel.getArticleListLiveData().observe(this, articleDataList -> {

            if (isRefresh) {
                LogUtils.i("HomePageFragment.initDataAndEvent->:replace");
                mHomePageAdapter.replaceData(articleDataList);
            } else {
                LogUtils.i("HomePageFragment.initDataAndEvent->:add");
                mHomePageAdapter.addData(articleDataList);
            }
            LogUtils.i("HomePageFragment.initDataAndEvent->:itemcount====" + mHomePageAdapter.getItemCount());
            mBinding.executePendingBindings();
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                mViewModel.refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = false;
                mViewModel.loadMore();
            }
        });
    }

    private void changeRefreshStatus(ListStatus status) {
        switch (status) {
            case REFRESHING:
                break;
            case LOADING_MORE:
                break;
            case REFRESH_ERROR:
                mRefreshLayout.finishRefresh(false);
                break;
            case REFRESH_FINISH:
                mRefreshLayout.finishRefresh();
                break;
            case LOAD_MORE_ERROR:
                mRefreshLayout.finishLoadMore(false);
                break;
            case LOAD_MORE_FINISH:
                mRefreshLayout.finishLoadMore();
                break;
        }
    }


    @Override
    protected void setUp() {
        mBinding = getViewDataBinding();
        mRefreshLayout = mBinding.refreshLayout;
        mRecyclerView = mBinding.recyclerView;
        mHomePageAdapter = new HomePageAdapter(R.layout.fragment_home_item, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mHomePageAdapter);
    }

    @Override
    protected void onRetryCall() {
        isRefresh = true;
        mViewModel.refresh();
    }
}
