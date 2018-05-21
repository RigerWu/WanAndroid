package com.rigerwu.wanandroid.ui.splash;

import com.rigerwu.wanandroid.Data.DataManager;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RigerWu on 2018/5/21.
 */
@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new SplashViewModel(dataManager, schedulerProvider);
    }
}
