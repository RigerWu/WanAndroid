package com.rigerwu.wanandroid.di.module;

import com.rigerwu.wanandroid.ui.main.MainFragment;
import com.rigerwu.wanandroid.ui.main.home.HomePageFragment;
import com.rigerwu.wanandroid.ui.main.tree.TreePageDetailFragment;
import com.rigerwu.wanandroid.ui.main.tree.TreePageFragment;
import com.rigerwu.wanandroid.ui.main.tree.treearticle.TreeArticleListFragment;

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

    @ContributesAndroidInjector
    abstract TreePageFragment bindTreePageFragment();

    @ContributesAndroidInjector
    abstract TreePageDetailFragment bindTreePageDetailFragment();

    @ContributesAndroidInjector
    abstract TreeArticleListFragment bindTreeArticleListFragment();
}
