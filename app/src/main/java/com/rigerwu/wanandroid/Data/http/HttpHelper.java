package com.rigerwu.wanandroid.Data.http;

import com.rigerwu.wanandroid.Data.model.BaseResponse;
import com.rigerwu.wanandroid.Data.model.main.ArticleListData;
import com.rigerwu.wanandroid.Data.model.main.BannerData;
import com.rigerwu.wanandroid.Data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.Data.model.main.HotkeyData;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/22.
 */
public interface HttpHelper {

    /**
     * 获取主页文章列表
     * @param pageNum 页号
     * @return list
     */
    Flowable<BaseResponse<ArticleListData>> getHomeArticleList(int pageNum);

    /**
     * 获取 banner 列表
     * @return list
     */
    Flowable<BaseResponse<List<BannerData>>> getBanners();

    /**
     * 常用网址
     *
     * @return list
     */
    Flowable<BaseResponse<List<CommonUseNet>>> getCommonUseNets();

    /**
     * 获取热词
     *
     * @return list
     */
    Flowable<BaseResponse<List<HotkeyData>>> getHotkeyData();

}
