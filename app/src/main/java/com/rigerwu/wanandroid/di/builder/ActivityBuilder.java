package com.rigerwu.wanandroid.di.builder;

import com.rigerwu.wanandroid.di.module.FragmentBuildersModule;
import com.rigerwu.wanandroid.ui.main.MainActivity;
import com.rigerwu.wanandroid.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by RigerWu on 2018/5/21.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity bindMainActivity();
}
