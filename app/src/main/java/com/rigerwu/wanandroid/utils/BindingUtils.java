package com.rigerwu.wanandroid.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.rigerwu.wanandroid.ui.home.HomePageAdapter;

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
}
