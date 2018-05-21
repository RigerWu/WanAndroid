package com.rigerwu.wanandroid.ui.splash;

import com.rigerwu.wanandroid.Data.DataManager;
import com.rigerwu.wanandroid.ui.base.BaseViewModel;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

/**
 * Created by RigerWu on 2018/5/21.
 */
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
