package com.rigerwu.wanandroid.Data.model.main;

import java.util.List;

/**
 * Created by RigerWu on 2018/5/22.
 */
public class ArticleListData {


    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"JessYanCoding","chapterId":324,"chapterName":"组件化","collect":false,"courseId":13,"desc":"一键搭建 ArmsComponent 快速组件化方案的整体组件架构, 让新手也可以一秒开始组件化项目, 开发神器, 让您一秒起飞, 体验纯傻瓜式的组件化开发, 避免组件化从入门到放弃!\r\n","envelopePic":"http://www.wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":true,"id":2939,"link":"http://www.wanandroid.com/blog/show/2138","niceDate":"20小时前","origin":"","projectLink":"https://github.com/JessYanCoding/ArmsComponent-Template","publishTime":1526883892000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=324"}],"title":"一键搭建 ArmsComponent 快速组件化方案的整体组件架构 ArmsComponent-Template ","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Carson_Ho","chapterId":93,"chapterName":"基础知识","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":2937,"link":"https://www.jianshu.com/p/ca118d704b5e","niceDate":"1天前","origin":"","projectLink":"","publishTime":1526805757000,"superChapterId":134,"superChapterName":"自定义控件","tags":[],"title":"为什么你的自定义View wrap_content不起作用？","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 65
     * size : 20
     * total : 1296
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<ArticleData> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleData> datas) {
        this.datas = datas;
    }
}
