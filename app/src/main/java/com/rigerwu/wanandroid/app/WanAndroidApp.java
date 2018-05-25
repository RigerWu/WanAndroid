package com.rigerwu.wanandroid.app;

import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.kingja.loadsir.core.LoadSir;
import com.rigerwu.wanandroid.BuildConfig;
import com.rigerwu.wanandroid.di.component.DaggerAppComponent;
import com.rigerwu.wanandroid.ui.base.status.LottieEmptyCallback;
import com.rigerwu.wanandroid.ui.base.status.LottieErrorCallback;
import com.rigerwu.wanandroid.ui.base.status.LottieLoadingCallback;
import com.rigerwu.wanandroid.ui.base.status.LottieNetErrorCallback;

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
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        // logger


        // Fragmentation
        Fragmentation.builder()
                // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();

        // loadsir
        LoadSir.beginBuilder()
                .addCallback(new LottieLoadingCallback())//添加各种状态页
                .addCallback(new LottieEmptyCallback())
                .addCallback(new LottieErrorCallback())
                .addCallback(new LottieNetErrorCallback())
                .setDefaultCallback(LottieLoadingCallback.class)//设置默认状态页
                .commit();
    }
}
