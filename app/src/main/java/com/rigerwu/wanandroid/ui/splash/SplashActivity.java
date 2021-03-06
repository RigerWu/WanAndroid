package com.rigerwu.wanandroid.ui.splash;

import android.animation.Animator;

import com.airbnb.lottie.LottieAnimationView;
import com.blankj.utilcode.util.BarUtils;
import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.BuildConfig;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.databinding.ActivitySplashBinding;
import com.rigerwu.wanandroid.ui.base.BaseActivity;
import com.rigerwu.wanandroid.ui.navigation.NavigationController;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Inject
    SplashViewModel mSplashViewModel;

    @Inject
    NavigationController mNavigationController;

    private ActivitySplashBinding mBinding;

    @Override
    protected void initDataAndEvent() {
        // set status bar invisiable
        BarUtils.setStatusBarVisibility(this, false);

        mBinding = getViewDataBinding();
        LottieAnimationView animationView = mBinding.lottieAnimationView;
        animationView.setSpeed(2.0f);
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                openMainActivity();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        if (BuildConfig.DEBUG) {
            openMainActivity();
        } else {
            animationView.playAnimation();
        }
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

    public void openMainActivity() {
        mNavigationController.showMain();
        finish();
    }
}
