package com.rigerwu.wanandroid.data.db;

import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.data.model.main.BannerData;
import com.rigerwu.wanandroid.data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.data.model.tree.TreeData;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/22.
 */
public interface DbHelper {

    Flowable<List<BannerData>> loadBanners();

    void insertBanners(List<BannerData> bannerDatas);

    void deleteAllBanner();


    Flowable<List<CommonUseNet>> loadCommonUseNets();

    void insertCommonUseNets(List<CommonUseNet> commonUseNets);

    void deleteAllCommonUseNets();

    void insertArticles(List<ArticleData> articleDatas);

    Flowable<List<ArticleData>> loadArticles(int page);

    Flowable<List<ArticleData>> loadAllArticles();

    void deleteArticle(ArticleData... articleData);

    void deleteAllArticles();

    Flowable<List<ArticleData>> loadCollectedArticles();


    void insertTreeDatas(List<TreeData> treeDataList);

    Flowable<List<TreeData>> loadTreeDatas();

}