package com.rigerwu.wanandroid.Data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.rigerwu.wanandroid.Data.db.converter.RoomDataConverter;
import com.rigerwu.wanandroid.Data.db.dao.ArticleDao;
import com.rigerwu.wanandroid.Data.db.dao.HomeInfoDao;
import com.rigerwu.wanandroid.Data.model.main.ArticleData;
import com.rigerwu.wanandroid.Data.model.main.BannerData;
import com.rigerwu.wanandroid.Data.model.main.CommonUseNet;
import com.rigerwu.wanandroid.Data.model.main.HotkeyData;

/**
 * Created by RigerWu on 2018/5/28.
 */
@Database(entities = {
        BannerData.class,
        ArticleData.class,
        CommonUseNet.class,
        HotkeyData.class
}, version = 1, exportSchema = false)
@TypeConverters({RoomDataConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract HomeInfoDao getHomeInfoDao();

    public abstract ArticleDao getArticleDao();
}
