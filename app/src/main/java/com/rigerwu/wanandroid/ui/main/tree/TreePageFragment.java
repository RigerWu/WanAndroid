package com.rigerwu.wanandroid.ui.main.tree;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.databinding.FragmentTreePageBinding;
import com.rigerwu.wanandroid.ui.base.BaseFragment;
import com.rigerwu.wanandroid.ui.navigation.NavigationController;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/6/16.
 */
public class TreePageFragment extends BaseFragment<FragmentTreePageBinding, TreePageViewModel> {

    public static TreePageFragment newInstance() {
        Bundle args = new Bundle();
        TreePageFragment fragment = new TreePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TreePageViewModel mViewModel;
    @Inject
    ViewModelProvider.Factory mFactory;
    @Inject
    NavigationController mNavigationController;

    FragmentTreePageBinding mBinding;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tree_page;
    }

    @Override
    public int getContainerId() {
        return R.id.refreshLayout;
    }

    @Override
    public TreePageViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mFactory).get(TreePageViewModel.class);
        return mViewModel;
    }

    @Override
    public void initDataAndEvent() {

    }

    @Override
    protected void setUp() {
        mBinding = getViewDataBinding();
        mBinding = getViewDataBinding();
        mRefreshLayout = mBinding.refreshLayout;
        mRecyclerView = mBinding.recyclerView;
    }
}
