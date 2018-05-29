package com.rigerwu.wanandroid.db;

import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.rigerwu.wanandroid.Data.model.main.BannerData;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by RigerWu on 2018/5/28.
 */
@RunWith(AndroidJUnit4.class)
public class BannerDaoTest extends DbTest {

    /**
     * desc : 最新项目上线啦~
     * id : 13
     * imagePath : http://www.wanandroid.com/blogimgs/5ae04af4-72b9-4696-81cb-1644cdcd2d29.jpg
     * isVisible : 1
     * order : 0
     * title : 最新项目上线啦~
     * type : 0
     * url : http://www.wanandroid.com/pindex
     */

    @Test
    public void insertAndLoad() {
        BannerData bannerData = new Gson().fromJson("{\n" +
                "      \"desc\": \"最新项目上线啦~\",\n" +
                "      \"id\": 13,\n" +
                "      \"imagePath\": \"http://www.wanandroid.com/blogimgs/5ae04af4-72b9-4696-81cb-1644cdcd2d29.jpg\",\n" +
                "      \"isVisible\": 1,\n" +
                "      \"order\": 0,\n" +
                "      \"title\": \"最新项目上线啦~\",\n" +
                "      \"type\": 0,\n" +
                "      \"url\": \"http://www.wanandroid.com/pindex\"\n" +
                "    }", BannerData.class);
        db.getHomeInfoDao().insertBanners(bannerData);
        Flowable<List<BannerData>> all = db.getHomeInfoDao().loadBanners();
        all.subscribe(bannerData12 -> {
            BannerData bannerData1 = bannerData12.get(0);
            Assert.assertThat(bannerData1.getId(), Is.is(13));
            Assert.assertThat(bannerData1.getTitle(), Is.is("最新项目上线啦~"));
        });
    }

    @Test
    public void delete() {
        BannerData bannerData = new BannerData();
        bannerData.setId(13);
        db.getHomeInfoDao().deleteBanner(bannerData);
        db.getHomeInfoDao().loadBanners().subscribe(bannerData1 -> Assert.assertThat(bannerData1.size(), Is.is(0)));
    }

}
