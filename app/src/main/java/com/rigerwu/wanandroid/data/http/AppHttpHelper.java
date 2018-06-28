package com.rigerwu.wanandroid.data.http;

import com.rigerwu.wanandroid.data.http.api.WanAndroidApi;
import com.rigerwu.wanandroid.data.model.BaseResponse;
import com.rigerwu.wanandroid.data.model.main.ArticleListData;
import com.rigerwu.wanandroid.data.model.main.BannerData;
import com.rigerwu.wanandroid.data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.data.model.main.HotkeyData;
import com.rigerwu.wanandroid.data.model.tree.TreeData;

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

    @Override
    public Flowable<BaseResponse<List<TreeData>>> getTreeData() {
        return mWanAndroidApi.getTreeData();
    }

    @Override
    public Flowable<BaseResponse<ArticleListData>> getTreeArticleList(int pageNum, int cid) {
        return mWanAndroidApi.getTreeArticleList(pageNum, cid);
    }
}
