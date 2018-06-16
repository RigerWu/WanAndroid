package com.rigerwu.wanandroid.ui.main.tree;

import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.ui.base.BaseViewModel;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/6/16.
 */
public class TreePageViewModel extends BaseViewModel {

    @Inject
    public TreePageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
