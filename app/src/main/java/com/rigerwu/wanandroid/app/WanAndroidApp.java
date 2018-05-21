package com.rigerwu.wanandroid.app;

import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.rigerwu.wanandroid.BuildConfig;
import com.rigerwu.wanandroid.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by RigerWu on 2018/5/21.
 */
public class WanAndroidApp extends Application implements HasActivityInjector {

    private static WanAndroidApp sInstance;

    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityDispatchingAndroidInjector;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initLibrarys();
    }

    public synchronized static WanAndroidApp getInstance() {
        return sInstance;
    }

    private void initLibrarys() {
        // androidutilcode
        Utils.init(this);

        // di
        AppInjector.init(this);

        // logger

        //Fragmentation
        Fragmentation.builder()
                // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
    }
}
