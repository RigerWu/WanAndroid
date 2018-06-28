package com.rigerwu.wanandroid.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.rigerwu.wanandroid.ui.main.MainFragmentViewModel;
import com.rigerwu.wanandroid.ui.main.home.HomePageViewModel;
import com.rigerwu.wanandroid.ui.main.tree.TreePageDetailViewModel;
import com.rigerwu.wanandroid.ui.main.tree.TreePageViewModel;
import com.rigerwu.wanandroid.ui.main.tree.treearticle.TreeArticleListViewModel;
import com.rigerwu.wanandroid.ui.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by RigerWu on 2018/5/21.
 */
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(WanAndroidViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel.class)
    abstract ViewModel bindMainFragmentViewModel(MainFragmentViewModel mainFragmentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel.class)
    abstract ViewModel bindHomePageFragmentViewModel(HomePageViewModel homePageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TreePageViewModel.class)
    abstract ViewModel bindTreePageFragmentViewModel(TreePageViewModel treePageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TreePageDetailViewModel.class)
    abstract ViewModel bindTreePageDetailFragmentViewModel(TreePageDetailViewModel treePageDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TreeArticleListViewModel.class)
    abstract ViewModel bindTreeArticleListFragmentViewModel(TreeArticleListViewModel treeArticleListViewModel);
}
