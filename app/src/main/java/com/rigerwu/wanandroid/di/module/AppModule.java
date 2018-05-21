package com.rigerwu.wanandroid.di.module;

import android.app.Application;
import android.content.Context;

import com.rigerwu.wanandroid.Data.AppDataManager;
import com.rigerwu.wanandroid.Data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RigerWu on 2018/5/21.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }
}
