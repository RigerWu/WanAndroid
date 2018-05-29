package com.rigerwu.wanandroid.Data.db;

import com.rigerwu.wanandroid.Data.model.main.ArticleData;
import com.rigerwu.wanandroid.Data.model.main.BannerData;
import com.rigerwu.wanandroid.Data.model.main.CommonUseNet;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/22.
 */
public interface DbHelper {

    Flowable<List<BannerData>> loadBanners();

    void insertBanners(BannerData... bannerDatas);

    void deleteAllBanner();


    Flowable<List<CommonUseNet>> loadCommonUseNets();

    void insertCommonUseNets(CommonUseNet... commonUseNets);

    void deleteAllCommonUseNets();

    void insertArticles(ArticleData... articleData);

    Flowable<List<ArticleData>> loadArticles(int page);

    Flowable<List<ArticleData>> loadAllArticles();

    void deleteArticle(ArticleData... articleData);

    void deleteAllArticles();

    Flowable<List<ArticleData>> loadCollectedArticles();
}