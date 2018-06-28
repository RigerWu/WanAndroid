package com.rigerwu.wanandroid.data.http.api;

import com.rigerwu.wanandroid.data.model.BaseResponse;
import com.rigerwu.wanandroid.data.model.main.ArticleListData;
import com.rigerwu.wanandroid.data.model.main.BannerData;
import com.rigerwu.wanandroid.data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.data.model.main.HotkeyData;
import com.rigerwu.wanandroid.data.model.tree.TreeData;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by RigerWu on 2018/5/22.
 */
public interface WanAndroidApi {

    String HOST = "http://www.wanandroid.com/";


    @GET("article/list/{num}/json")
    Flowable<BaseResponse<ArticleListData>> getHomeArticleList(@Path("num") int pageNum);

    @GET("banner/json")
    Flowable<BaseResponse<List<BannerData>>> getBanners();

    @GET("friend/json")
    Flowable<BaseResponse<List<CommonUseNet>>> getCommonUseNets();

    @GET("hotkey/json")
    Flowable<BaseResponse<List<HotkeyData>>> getHotkeyData();

    @GET("tree/json")
    Flowable<BaseResponse<List<TreeData>>> getTreeData();

    @GET("article/list/{num}/json")
    Flowable<BaseResponse<ArticleListData>> getTreeArticleList(@Path("num") int pageNum, @Query("cid") int cid);
}
