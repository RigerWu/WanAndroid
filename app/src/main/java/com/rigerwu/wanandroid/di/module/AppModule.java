package com.rigerwu.wanandroid.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.rigerwu.wanandroid.Data.AppDataManager;
import com.rigerwu.wanandroid.Data.DataManager;
import com.rigerwu.wanandroid.Data.db.AppDbHelper;
import com.rigerwu.wanandroid.Data.db.DbHelper;
import com.rigerwu.wanandroid.Data.http.AppHttpHelper;
import com.rigerwu.wanandroid.Data.http.HttpHelper;
import com.rigerwu.wanandroid.Data.http.api.WanAndroidApi;
import com.rigerwu.wanandroid.Data.http.client.RetrofitClient;
import com.rigerwu.wanandroid.Data.prefs.AppPreferencesHelper;
import com.rigerwu.wanandroid.Data.prefs.PreferencesHelper;
import com.rigerwu.wanandroid.app.AppConstants;
import com.rigerwu.wanandroid.utils.rx.AppSchedulerProvider;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by RigerWu on 2018/5/21.
 */
@Module(includes = ViewModelModule.class)
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
    HttpHelper provideHttpHelper(AppHttpHelper appHttpHelper) {
        return appHttpHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper dbHelper) {
        // TODO: 2018/5/23 init room when needed
        return dbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper preferencesHelper) {
        return preferencesHelper;
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

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


}
