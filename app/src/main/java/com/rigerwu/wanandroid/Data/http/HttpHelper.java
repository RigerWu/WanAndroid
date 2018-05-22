package com.rigerwu.wanandroid.Data.http;

import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;

import io.reactivex.Observable;

/**
 * Created by RigerWu on 2018/5/22.
 */
public interface HttpHelper {

    /**
     * 获取主页文章列表
     * @param pageNum 页号
     * @return
     */
    Observable<BaseResponse<ArticleListData>>  getHomeArticleList(int pageNum);

}
