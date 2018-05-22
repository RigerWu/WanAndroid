package com.rigerwu.wanandroid.Data;

import android.content.Context;

import com.google.gson.Gson;
import com.rigerwu.wanandroid.Data.db.DbHelper;
import com.rigerwu.wanandroid.Data.http.HttpHelper;
import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;
import com.rigerwu.wanandroid.Data.prefs.PreferencesHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by RigerWu on 2018/5/21.
 */
@Singleton
public class AppDataManager implements DataManager {

    private final HttpHelper mHttpHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(HttpHelper httpHelper, Context context, DbHelper dbHelper, Gson gson, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mContext = context;
        mDbHelper = dbHelper;
        mGson = gson;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getHomeArticleList(int pageNum) {
        return null;
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferencesHelper.setLoginAccount(account);
    }

    @Override
    public String getLoginAccount() {
        return mPreferencesHelper.getLoginAccount();
    }

    @Override
    public void setLoginPwd(String pwd) {
        mPreferencesHelper.setLoginPwd(pwd);
    }

    @Override
    public String getLoginPwd() {
        return mPreferencesHelper.getLoginPwd();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferencesHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferencesHelper.getLoginStatus();
    }
}
