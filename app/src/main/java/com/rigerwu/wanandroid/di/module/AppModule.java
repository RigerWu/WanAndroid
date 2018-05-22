package com.rigerwu.wanandroid.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.rigerwu.wanandroid.Data.AppDataManager;
import com.rigerwu.wanandroid.Data.DataManager;
import com.rigerwu.wanandroid.Data.http.api.WanAndroidApi;
import com.rigerwu.wanandroid.Data.http.client.RetrofitClient;
import com.rigerwu.wanandroid.app.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

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

    @Provides
    @Singleton
    WanAndroidApi provideWanAndroidApi(Retrofit retrofit) {
        return retrofit.create(WanAndroidApi.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return RetrofitClient.getInstance().createRetrofit(AppConstants.BASE_URL);
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new Gson();
    }



}
