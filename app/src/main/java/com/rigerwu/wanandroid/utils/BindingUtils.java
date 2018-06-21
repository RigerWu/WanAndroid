package com.rigerwu.wanandroid.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.data.model.tree.TreeData;
import com.rigerwu.wanandroid.ui.main.home.HomePageAdapter;
import com.rigerwu.wanandroid.ui.main.tree.TreePageAdapter;

import java.util.List;

/**
 * Created by RigerWu on 2018/6/13.
 */
public final class BindingUtils {

    private BindingUtils() {
    }

    @BindingAdapter({"adapter"})
    public static void addArticles(RecyclerView recyclerView, List<ArticleData> articles) {
        HomePageAdapter adapter = (HomePageAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData(articles);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addTreeDatas(RecyclerView recyclerView, List<TreeData> treeDataList) {
        TreePageAdapter adapter = (TreePageAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setNewData(treeDataList);
        }
    }
}
