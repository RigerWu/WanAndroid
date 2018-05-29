package com.rigerwu.wanandroid.Data;

import android.content.Context;

import com.google.gson.Gson;
import com.rigerwu.wanandroid.Data.db.DbHelper;
import com.rigerwu.wanandroid.Data.http.HttpHelper;
import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleData;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;
import com.rigerwu.wanandroid.Data.model.main.BannerData;
import com.rigerwu.wanandroid.Data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.Data.model.main.HotkeyData;
import com.rigerwu.wanandroid.Data.prefs.PreferencesHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

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
    public Flowable<BaseResponse<ArticleListData>> getHomeArticleList(int pageNum) {
        return mHttpHelper.getHomeArticleList(pageNum);
    }

    @Override
    public Flowable<BaseResponse<List<BannerData>>> getBanners() {
        return mHttpHelper.getBanners();
    }

    @Override
    public Flowable<BaseResponse<List<CommonUseNet>>> getCommonUseNets() {
        return mHttpHelper.getCommonUseNets();
    }

    @Override
    public Flowable<BaseResponse<List<HotkeyData>>> getHotkeyData() {
        return mHttpHelper.getHotkeyData();
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

    @Override
    public Flowable<List<BannerData>> loadBanners() {
        return null;
    }

    @Override
    public void insertBanners(BannerData... bannerDatas) {
        mDbHelper.insertBanners(bannerDatas);
    }

    @Override
    public void deleteAllBanner() {
        mDbHelper.deleteAllBanner();
    }

    @Override
    public Flowable<List<CommonUseNet>> loadCommonUseNets() {
        return mDbHelper.loadCommonUseNets();
    }

    @Override
    public void insertCommonUseNets(CommonUseNet... commonUseNets) {
        mDbHelper.insertCommonUseNets(commonUseNets);
    }

    @Override
    public void deleteAllCommonUseNets() {
        mDbHelper.deleteAllCommonUseNets();
    }

    @Override
    public void insertArticles(ArticleData... articleData) {
        mDbHelper.insertArticles(articleData);
    }

    @Override
    public Flowable<List<ArticleData>> loadArticles(int page) {
        return mDbHelper.loadArticles(page);
    }

    @Override
    public Flowable<List<ArticleData>> loadAllArticles() {
        return mDbHelper.loadAllArticles();
    }

    @Override
    public void deleteArticle(ArticleData... articleData) {
        mDbHelper.deleteArticle(articleData);
    }

    @Override
    public void deleteAllArticles() {
        mDbHelper.deleteAllArticles();
    }

    @Override
    public Flowable<List<ArticleData>> loadCollectedArticles() {
        return mDbHelper.loadCollectedArticles();
    }
}
