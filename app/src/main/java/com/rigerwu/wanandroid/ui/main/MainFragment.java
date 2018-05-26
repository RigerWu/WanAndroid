package com.rigerwu.wanandroid.ui.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.databinding.FragmentMainBinding;
import com.rigerwu.wanandroid.ui.base.BaseFragment;
import com.rigerwu.wanandroid.utils.BottomNavigationViewHelper;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/24.
 */
public class MainFragment extends BaseFragment<FragmentMainBinding, MainFragmentViewModel> {

    @Inject
    MainFragmentViewModel mViewModel;

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
    public MainFragmentViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void initDataAndEvent() {
        mBinding = getViewDataBinding();
        mBottomNavigationView = mBinding.bottomNavigationView;
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

    }

    @Override
    public void onNetReload(View v) {

    }
}
