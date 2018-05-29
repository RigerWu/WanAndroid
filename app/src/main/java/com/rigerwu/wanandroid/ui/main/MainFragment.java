package com.rigerwu.wanandroid.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.databinding.FragmentMainBinding;
import com.rigerwu.wanandroid.ui.base.BaseToolbarFragment;
import com.rigerwu.wanandroid.utils.BottomNavigationViewHelper;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/24.
 */
public class MainFragment extends BaseToolbarFragment<FragmentMainBinding, MainFragmentViewModel> {

    @Inject
    MainFragmentViewModel mViewModel;
    @Inject
    ViewModelProvider.Factory mFactory;

    private FragmentMainBinding mBinding;
    private BottomNavigationView mBottomNavigationView;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public int getContainerId() {
        return R.id.fm_tab_container;
    }

    @Override
    public MainFragmentViewModel getViewModel() {
        mViewModel = ViewModelProviders.of(this, mFactory).get(MainFragmentViewModel.class);
        return mViewModel;
    }

    @Override
    public void initDataAndEvent() {

    }

    @Override
    public void onNetReload(View v) {

    }

    @Override
    protected void setUp() {
        mBinding = getViewDataBinding();
        mBottomNavigationView = mBinding.bottomNavigationView;
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_tab_home:
                    mToolbar.setTitle(R.string.tab_home);
                    break;
                case R.id.menu_tab_tree:
                    mToolbar.setTitle(R.string.tab_tree);
                    break;
                case R.id.menu_tab_navigation:
                    mToolbar.setTitle(R.string.tab_navigation);
                    break;
                case R.id.menu_tab_project:
                    mToolbar.setTitle(R.string.tab_project);
                    break;
                case R.id.menu_tab_mine:
                    mToolbar.setTitle(R.string.tab_mine);
                    break;
            }
            return true;
        });
    }

    @Override
    protected void setUpToolBar() {
        mToolbar.setTitle(R.string.tab_home);
    }
}
