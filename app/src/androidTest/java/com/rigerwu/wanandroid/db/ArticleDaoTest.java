package com.rigerwu.wanandroid.db;

import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.rigerwu.wanandroid.Data.model.main.ArticleData;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.functions.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by RigerWu on 2018/5/28.
 */
@RunWith(AndroidJUnit4.class)
public class ArticleDaoTest extends DbTest {

    @Test
    public void insertAndLoad() {
        ArticleData articleData = new Gson().fromJson("{\n" +
                "        \"apkLink\": \"\",\n" +
                "        \"author\": \"TonnyL\",\n" +
                "        \"chapterId\": 198,\n" +
                "        \"chapterName\": \"基础概念\",\n" +
                "        \"collect\": false,\n" +
                "        \"courseId\": 13,\n" +
                "        \"desc\": \"\",\n" +
                "        \"envelopePic\": \"\",\n" +
                "        \"fresh\": false,\n" +
                "        \"id\": 2928,\n" +
                "        \"link\": \"https://www.jianshu.com/p/b90a093b0de7\",\n" +
                "        \"niceDate\": \"2018-05-15\",\n" +
                "        \"origin\": \"\",\n" +
                "        \"projectLink\": \"\",\n" +
                "        \"publishTime\": 1526382939000,\n" +
                "        \"superChapterId\": 168,\n" +
                "        \"superChapterName\": \"基础知识\",\n" +
                "        \"tags\": [\n" +
                "          \n" +
                "        ],\n" +
                "        \"title\": \"Android Tools attributes: listItem 和 sample data 的用法\",\n" +
                "        \"type\": 0,\n" +
                "        \"userId\": -1,\n" +
                "        \"visible\": 1,\n" +
                "        \"zan\": 0\n" +
                "      }", ArticleData.class);
        db.getArticleDao().insertArticles(articleData);

        db.getArticleDao().loadAllArticles().subscribe(new Consumer<List<ArticleData>>() {
            @Override
            public void accept(List<ArticleData> articleDataList) throws Exception {
                assertThat(articleDataList.size(), is(1));
                assertThat(articleDataList.get(0).getSuperChapterName(), is("基础知识"));
            }
        });
    }
}
