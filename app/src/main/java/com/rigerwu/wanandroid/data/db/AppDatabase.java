package com.rigerwu.wanandroid.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.rigerwu.wanandroid.data.db.converter.RoomDataConverter;
import com.rigerwu.wanandroid.data.db.dao.ArticleDao;
import com.rigerwu.wanandroid.data.db.dao.HomeInfoDao;
import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.data.model.main.BannerData;
import com.rigerwu.wanandroid.data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.data.model.main.HotkeyData;
import com.rigerwu.wanandroid.data.model.tree.TreeData;

/**
 * Created by RigerWu on 2018/5/28.
 */
@Database(entities = {
        BannerData.class,
        ArticleData.class,
        CommonUseNet.class,
        HotkeyData.class,
        TreeData.class
}, version = 1, exportSchema = false)
@TypeConverters({RoomDataConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract HomeInfoDao getHomeInfoDao();

    public abstract ArticleDao getArticleDao();
}
