package com.rigerwu.wanandroid.Data.http.api;

import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by RigerWu on 2018/5/22.
 */
public interface WanAndroidApi {

    String HOST = "http://www.wanandroid.com/";


    @GET("article/list/{num}/json")
    Observable<BaseResponse<ArticleListData>> getHomeArticleList(@Path("num") int pageNum);
}
