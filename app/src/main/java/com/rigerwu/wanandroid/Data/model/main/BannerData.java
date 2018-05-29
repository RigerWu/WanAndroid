package com.rigerwu.wanandroid.Data.model.main;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by RigerWu on 2018/5/28.
 */
@Entity(tableName = "banner_data")
public class BannerData {

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

    @PrimaryKey
    private int id;
    private String desc;
    @ColumnInfo(name = "image_path")
    private String imagePath;
    @ColumnInfo(name = "is_visible")
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
