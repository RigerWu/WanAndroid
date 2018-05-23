package com.rigerwu.wanandroid.Data.http;

import com.rigerwu.wanandroid.Data.http.api.WanAndroidApi;
import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

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
    public Observable<BaseResponse<ArticleListData>> getHomeArticleList(int pageNum) {
        return mWanAndroidApi.getHomeArticleList(pageNum);
    }
}
