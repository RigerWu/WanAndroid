package com.rigerwu.wanandroid.data.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.data.model.tree.TreeData;

import java.util.Date;
import java.util.List;

/**
 * Created by RigerWu on 2018/5/28.
 */
public class RoomDataConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String toTagLitJson(List<ArticleData.TagsBean> tags) {
        return new Gson().toJson(tags);
    }

    @TypeConverter
    public static List<ArticleData.TagsBean> toTagList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<ArticleData.TagsBean>>() {
        }.getType());
    }

    @TypeConverter
    public static String toTreeListJson(List<TreeData> treeDatas) {
        return new Gson().toJson(treeDatas);
    }

    @TypeConverter
    public static List<TreeData> toTreeList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<TreeData>>() {
        }.getType());
    }
}
