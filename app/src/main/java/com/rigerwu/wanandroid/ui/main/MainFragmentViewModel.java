package com.rigerwu.wanandroid.ui.main;

import com.rigerwu.wanandroid.Data.DataManager;
import com.rigerwu.wanandroid.ui.base.BaseViewModel;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/24.
 */
public class MainFragmentViewModel extends BaseViewModel<MainFragmentNavigator> {

    @Inject
    public MainFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
