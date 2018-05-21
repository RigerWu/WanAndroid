package com.rigerwu.wanandroid.di.builder;

import com.rigerwu.wanandroid.ui.splash.SplashActivity;
import com.rigerwu.wanandroid.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by RigerWu on 2018/5/21.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();
}
