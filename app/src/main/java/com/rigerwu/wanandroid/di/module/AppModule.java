package com.rigerwu.wanandroid.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;
import com.rigerwu.wanandroid.app.AppConstants;
import com.rigerwu.wanandroid.data.AppDataManager;
import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.data.db.AppDatabase;
import com.rigerwu.wanandroid.data.db.AppDbHelper;
import com.rigerwu.wanandroid.data.db.DbHelper;
import com.rigerwu.wanandroid.data.http.AppHttpHelper;
import com.rigerwu.wanandroid.data.http.HttpHelper;
import com.rigerwu.wanandroid.data.http.api.WanAndroidApi;
import com.rigerwu.wanandroid.data.http.client.RetrofitClient;
import com.rigerwu.wanandroid.data.prefs.AppPreferencesHelper;
import com.rigerwu.wanandroid.data.prefs.PreferencesHelper;
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
    DbHelper providerDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppConstants.DB_NAME).fallbackToDestructiveMigration()
                .build();
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
