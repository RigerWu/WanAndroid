package com.rigerwu.wanandroid.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.rigerwu.wanandroid.data.model.main.ArticleData;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/28.
 */
@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<ArticleData> articleDatas);

    @Query("select * from article_data order by id desc limit (:page-1) * 20, 20")
    Flowable<List<ArticleData>> loadArticles(int page);

    @Query("select * from article_data order by id desc")
    Flowable<List<ArticleData>> loadAllArticles();

    @Query("select * from article_data where chapter_id = (:cid)")
    Flowable<List<ArticleData>> loadTreeArticle(int cid);

    @Delete
    void deleteArticle(ArticleData... articleData);

    @Query("delete from article_data")
    void deleteAllArticles();

    @Query("select * from article_data where collect = 1")
    Flowable<List<ArticleData>> loadCollectedArticles();
}
