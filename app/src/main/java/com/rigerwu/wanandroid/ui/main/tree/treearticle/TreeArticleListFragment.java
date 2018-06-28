package com.rigerwu.wanandroid.ui.main.tree.treearticle;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.app.AppConstants;
import com.rigerwu.wanandroid.data.model.tree.TreeData;
import com.rigerwu.wanandroid.databinding.FragmentTreeArticlelistBinding;
import com.rigerwu.wanandroid.ui.base.BaseSwipeBackFragment;
import com.rigerwu.wanandroid.ui.base.status.ListStatus;
import com.rigerwu.wanandroid.ui.main.home.HomePageAdapter;
import com.rigerwu.wanandroid.ui.navigation.NavigationController;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by RigerWu on 2018/6/24.
 */
public class TreeArticleListFragment extends BaseSwipeBackFragment<FragmentTreeArticlelistBinding, TreeArticleListViewModel> {

    private HomePageAdapter mHomePageAdapter;
    private TreeData mTreeData;

    public static TreeArticleListFragment newInstance(TreeData treeData) {
        Bundle args = new Bundle();
        args.putSerializable(AppConstants.ARG_PARAM1, treeData);
        TreeArticleListFragment fragment = new TreeArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    ViewModelProvider.Factory mFactory;
    @Inject
    NavigationController mNavigationController;

    private TreeArticleListViewModel mViewModel;
    private FragmentTreeArticlelistBinding mBinding;

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tree_articlelist;
    }

    @Override
    public int getContainerId() {
        return R.id.refreshLayout;
    }

    @Override
    public TreeArticleListViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mFactory).get(TreeArticleListViewModel.class);
        mTreeData = (TreeData) getArguments().getSerializable(AppConstants.ARG_PARAM1);
        mViewModel.setCid(mTreeData.getId());
        return mViewModel;
    }

    @Override
    public void initDataAndEvent() {
        mViewModel.getCompositeDisposable().add(
                mViewModel.getLoadingStatus().subscribe(this::changeViewStatus));

        mViewModel.getCompositeDisposable().add(
                mViewModel.getRefreshState().subscribe(this::changeRefreshStatus)
        );


        mViewModel.getArticleListLiveData().observe(this, articleDataList -> {

            if (articleDataList != null) {
                if (!mViewModel.isHasMore()) {
                    mRefreshLayout.setNoMoreData(true);
                }
                mViewModel.setObservableList(articleDataList);
            }
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

        mHomePageAdapter.setOnItemClickListener((adapter, view, position) -> {
            String link = mHomePageAdapter.getItem(position).getLink();
            // the article fragment is the same level as mainfragment
            mNavigationController.readArticleDetail((SupportFragment) getParentFragment(), link);
        });
    }

    @Override
    protected void setUp() {
        mBinding = getViewDataBinding();
        mRefreshLayout = mBinding.refreshLayout;
        mRecyclerView = mBinding.recyclerView;
        mHomePageAdapter = new HomePageAdapter(R.layout.fragment_home_item, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mHomePageAdapter);
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
    protected void onRetryCall() {
        mViewModel.refresh();
    }
}
