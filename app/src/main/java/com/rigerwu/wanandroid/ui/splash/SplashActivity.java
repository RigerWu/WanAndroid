package com.rigerwu.wanandroid.ui.splash;

import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.databinding.ActivitySplashBinding;
import com.rigerwu.wanandroid.ui.base.BaseActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    @Override
    protected void initDataAndEvent() {
        mSplashViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openMainActivity() {

    }
}
