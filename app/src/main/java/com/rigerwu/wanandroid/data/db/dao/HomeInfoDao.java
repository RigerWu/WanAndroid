package com.rigerwu.wanandroid.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.rigerwu.wanandroid.data.model.main.BannerData;
import com.rigerwu.wanandroid.data.model.main.CommonUseNet;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/28.
 */
@Dao
public interface HomeInfoDao {

    // banners
    @Query("select * from banner_data")
    Flowable<List<BannerData>> loadBanners();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBanners(List<BannerData> bannerDatas);

    @Query("delete from banner_data")
    void deleteAllBanner();


    // common use net
    @Query("select * from common_use_net")
    Flowable<List<CommonUseNet>> loadCommonUseNets();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommonUseNets(List<CommonUseNet> commonUseNets);

    @Query("delete from common_use_net")
    void deleteAllCommonUseNets();


}
