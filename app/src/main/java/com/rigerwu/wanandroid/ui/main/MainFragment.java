package com.rigerwu.wanandroid.ui.main;

import android.os.Bundle;

import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.databinding.FragmentMainBinding;
import com.rigerwu.wanandroid.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/24.
 */
public class MainFragment extends BaseFragment<FragmentMainBinding, MainFragmentViewModel> {

    @Inject
    MainFragmentViewModel mViewModel;

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

    }
}
