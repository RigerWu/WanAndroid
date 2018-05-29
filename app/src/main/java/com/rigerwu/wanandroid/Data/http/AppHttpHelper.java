package com.rigerwu.wanandroid.Data.http;

import com.rigerwu.wanandroid.Data.http.api.WanAndroidApi;
import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;
import com.rigerwu.wanandroid.Data.model.main.BannerData;
import com.rigerwu.wanandroid.Data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.Data.model.main.HotkeyData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/22.
 */
@Singleton
public class AppHttpHelper implements HttpHelper{

    private WanAndroidApi mWanAndroidApi;

    @Inject
    public AppHttpHelper(WanAndroidApi wanAndroidApi) {
        mWanAndroidApi = wanAndroidApi;
    }

    @Override
    public Flowable<BaseResponse<ArticleListData>> getHomeArticleList(int pageNum) {
        return mWanAndroidApi.getHomeArticleList(pageNum);
    }

    @Override
    public Flowable<BaseResponse<List<BannerData>>> getBanners() {
        return mWanAndroidApi.getBanners();
    }

    @Override
    public Flowable<BaseResponse<List<CommonUseNet>>> getCommonUseNets() {
        return mWanAndroidApi.getCommonUseNets();
    }

    @Override
    public Flowable<BaseResponse<List<HotkeyData>>> getHotkeyData() {
        return mWanAndroidApi.getHotkeyData();
    }
}
