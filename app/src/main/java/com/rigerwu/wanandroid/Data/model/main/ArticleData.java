package com.rigerwu.wanandroid.Data.model.main;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "article_data")
public class ArticleData {
    /**
     * apkLink :
     * author : JessYanCoding
     * chapterId : 324
     * chapterName : 组件化
     * collect : false
     * courseId : 13
     * desc : 一键搭建 ArmsComponent 快速组件化方案的整体组件架构, 让新手也可以一秒开始组件化项目, 开发神器, 让您一秒起飞, 体验纯傻瓜式的组件化开发, 避免组件化从入门到放弃!

     * envelopePic : http://www.wanandroid.com/resources/image/pc/default_project_img.jpg
     * fresh : true
     * id : 2939
     * link : http://www.wanandroid.com/blog/show/2138
     * niceDate : 20小时前
     * origin :
     * projectLink : https://github.com/JessYanCoding/ArmsComponent-Template
     * publishTime : 1526883892000
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/list/1?cid=324"}]
     * title : 一键搭建 ArmsComponent 快速组件化方案的整体组件架构 ArmsComponent-Template
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */
    @ColumnInfo(name = "apk_link")
    private String apkLink;
    private String author;
    @ColumnInfo(name = "chapter_id")
    private int chapterId;
    @ColumnInfo(name = "chapter_name")
    private String chapterName;
    private boolean collect;
    @ColumnInfo(name = "course_id")
    private int courseId;
    private String desc;
    @ColumnInfo(name = "envelope_pic")
    private String envelopePic;
    private boolean fresh;
    @PrimaryKey
    private int id;
    private String link;
    @ColumnInfo(name = "nice_date")
    private String niceDate;
    private String origin;
    @ColumnInfo(name = "project_link")
    private String projectLink;
    @ColumnInfo(name = "publish_time")
    private long publishTime;
    @ColumnInfo(name = "super_chapter_id")
    private int superChapterId;
    @ColumnInfo(name = "super_chapter_name")
    private String superChapterName;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;
    private List<TagsBean> tags;

    public static class TagsBean {
        /**
         * name : 项目
         * url : /project/list/1?cid=324
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }
}