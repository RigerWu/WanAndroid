package com.rigerwu.wanandroid.ui.main.tree;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.LogUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.app.AppConstants;
import com.rigerwu.wanandroid.data.model.tree.TreeData;
import com.rigerwu.wanandroid.databinding.FragmentTreePageDetailBinding;
import com.rigerwu.wanandroid.ui.base.BaseToolbarFragment;
import com.rigerwu.wanandroid.ui.main.tree.treearticle.TreeArticleListFragment;
import com.rigerwu.wanandroid.ui.navigation.NavigationController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/6/23.
 */
public class TreePageDetailFragment extends
        BaseToolbarFragment<FragmentTreePageDetailBinding, TreePageDetailViewModel> {

    private SlidingTabLayout mTreeTabLayout;
    private ViewPager mTreeViewpager;
    private TreeData mTreeData;

    private List<TreeArticleListFragment> mFragments = new ArrayList<>();

    public static TreePageDetailFragment newInstance(TreeData treeData) {
        Bundle args = new Bundle();
        args.putSerializable(AppConstants.ARG_PARAM1, treeData);
        TreePageDetailFragment fragment = new TreePageDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TreePageDetailViewModel mViewModel;
    @Inject
    ViewModelProvider.Factory mFactory;
    @Inject
    NavigationController mNavigationController;

    FragmentTreePageDetailBinding mBinding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tree_page_detail;
    }

    @Override
    public int getContainerId() {
        return R.id.tree_tab_continer;
    }

    @Override
    public TreePageDetailViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mFactory).get(TreePageDetailViewModel.class);
        return mViewModel;
    }

    @Override
    public void initDataAndEvent() {
        mTreeData = (TreeData) getArguments().getSerializable(AppConstants.ARG_PARAM1);
        mViewModel.setTreeData(mTreeData);
        mToolbar.setTitle(mTreeData.getName());

        List<TreeData> children = mTreeData.getChildren();
        for (TreeData child : children) {
            mFragments.add(TreeArticleListFragment.newInstance(child));
        }

        LogUtils.i("TreePageDetailFragment.initDataAndEvent->:" + mFragments.size());

        mTreeViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTreeData.getChildren().get(position).getName();
            }
        });
        mTreeTabLayout.setViewPager(mTreeViewpager);
    }

    @Override
    protected void setUp() {
        mBinding = getViewDataBinding();
        mTreeTabLayout = mBinding.treeTabLayout;
        mTreeViewpager = mBinding.treeViewpager;

    }

    @Override
    protected void setUpToolBar() {
        initToolbarNav(mToolbar);
    }
}
