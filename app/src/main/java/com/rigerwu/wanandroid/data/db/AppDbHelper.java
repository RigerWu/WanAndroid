package com.rigerwu.wanandroid.data.db;

import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.data.model.main.BannerData;
import com.rigerwu.wanandroid.data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.data.model.tree.TreeData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/22.
 */
public class AppDbHelper implements DbHelper {

    AppDatabase mDatabase;

    @Inject
    public AppDbHelper(AppDatabase database) {
        mDatabase = database;
    }

    @Override
    public Flowable<List<BannerData>> loadBanners() {
        return mDatabase.getHomeInfoDao().loadBanners();
    }

    @Override
    public void insertBanners(List<BannerData> bannerDatas) {
        mDatabase.getHomeInfoDao().insertBanners(bannerDatas);
    }

    @Override
    public void deleteAllBanner() {
        mDatabase.getHomeInfoDao().deleteAllBanner();
    }

    @Override
    public Flowable<List<CommonUseNet>> loadCommonUseNets() {
        return mDatabase.getHomeInfoDao().loadCommonUseNets();
    }

    @Override
    public void insertCommonUseNets(List<CommonUseNet> commonUseNets) {
        mDatabase.getHomeInfoDao().insertCommonUseNets(commonUseNets);
    }

    @Override
    public void deleteAllCommonUseNets() {
        mDatabase.getHomeInfoDao().deleteAllCommonUseNets();
    }

    @Override
    public void insertArticles(List<ArticleData> articleData) {
        mDatabase.getArticleDao().insertArticles(articleData);
    }

    @Override
    public Flowable<List<ArticleData>> loadArticles(int page) {
        return mDatabase.getArticleDao().loadArticles(page);
    }

    @Override
    public Flowable<List<ArticleData>> loadAllArticles() {
        return mDatabase.getArticleDao().loadAllArticles();
    }

    @Override
    public Flowable<List<ArticleData>> loadTreeArticle(int cid) {
        return mDatabase.getArticleDao().loadTreeArticle(cid);
    }

    @Override
    public void deleteArticle(ArticleData... articleData) {
        mDatabase.getArticleDao().deleteArticle(articleData);
    }

    @Override
    public void deleteAllArticles() {
        mDatabase.getArticleDao().deleteAllArticles();
    }

    @Override
    public Flowable<List<ArticleData>> loadCollectedArticles() {
        return mDatabase.getArticleDao().loadCollectedArticles();
    }

    @Override
    public void insertTreeDatas(List<TreeData> treeDataList) {
        mDatabase.getHomeInfoDao().insertTreeDatas(treeDataList);
    }

    @Override
    public Flowable<List<TreeData>> loadTreeDatas() {
        return mDatabase.getHomeInfoDao().loadTreeDatas();
    }
}
