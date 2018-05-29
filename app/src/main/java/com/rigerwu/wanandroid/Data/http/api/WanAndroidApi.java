package com.rigerwu.wanandroid.Data.http.api;

import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;
import com.rigerwu.wanandroid.Data.model.main.BannerData;
import com.rigerwu.wanandroid.Data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.Data.model.main.HotkeyData;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;


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
}
