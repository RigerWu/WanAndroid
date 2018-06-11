package com.rigerwu.wanandroid.ui.splash;

import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.ui.base.BaseViewModel;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/21.
 */
public class SplashViewModel extends BaseViewModel {

    @Inject
    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
