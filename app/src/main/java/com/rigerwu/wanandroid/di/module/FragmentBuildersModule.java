package com.rigerwu.wanandroid.di.module;

import com.rigerwu.wanandroid.ui.home.HomePageFragment;
import com.rigerwu.wanandroid.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by RigerWu on 2018/5/24.
 */
@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract MainFragment bindMainFragment();

    @ContributesAndroidInjector
    abstract HomePageFragment bindHomePageFragment();
}
