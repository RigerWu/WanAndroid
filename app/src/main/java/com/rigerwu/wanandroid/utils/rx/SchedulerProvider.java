package com.rigerwu.wanandroid.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by RigerWu on 2018/5/21.
 */
public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
