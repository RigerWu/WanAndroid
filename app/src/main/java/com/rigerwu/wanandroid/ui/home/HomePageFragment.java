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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
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
                mViewModel.getLoadingStatus().subscribe(status -> {
                    changeViewStatus(status);
                    changeRefreshStatus(status);
                }));

        mViewModel.getArticleListLiveData().observe(this, articleDataList -> {
            LogUtils.i("HomePageFragment.initDataAndEvent->:" + articleDataList.size());
            if (mRefreshLayout.getState() == RefreshState.Refreshing) {
                LogUtils.i("HomePageFragment.initDataAndEvent->:replace");
                mHomePageAdapter.replaceData(articleDataList);
            } else {
                LogUtils.i("HomePageFragment.initDataAndEvent->:add");
                mHomePageAdapter.addData(articleDataList);
            }
            mBinding.executePendingBindings();
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mViewModel.refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                mViewModel.loadMore();
            }
        });
    }

    private void changeRefreshStatus(Integer status) {
        switch (status) {
            case STATUS_NOMAL:
                if (mRefreshLayout.getState() == RefreshState.Refreshing) {
                    mRefreshLayout.finishRefresh();
                } else if (mRefreshLayout.getState() == RefreshState.Loading) {
                    mRefreshLayout.finishLoadMore();
                }
                break;
            case STATUS_ERROR:
                if (mRefreshLayout.getState() == RefreshState.Refreshing) {
                    mRefreshLayout.finishRefresh(false);
                } else if (mRefreshLayout.getState() == RefreshState.Loading) {
                    mRefreshLayout.finishLoadMore(false);
                }
                break;
            case STATUS_NET_ERROR:
                if (mRefreshLayout.getState() == RefreshState.Refreshing) {
                    mRefreshLayout.finishRefresh(false);
                } else if (mRefreshLayout.getState() == RefreshState.Loading) {
                    mRefreshLayout.finishLoadMore(false);
                }
                break;
            case STATUS_LOADING:

                break;
            case STATUS_EMPTY:

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
}
