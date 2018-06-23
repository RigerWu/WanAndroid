package com.rigerwu.wanandroid.ui.navigation;

import android.content.Context;
import android.os.Bundle;

import com.rigerwu.wanandroid.data.model.tree.TreeData;
import com.rigerwu.wanandroid.ui.main.MainActivity;
import com.rigerwu.wanandroid.ui.webview.ArticleDetailFragment;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by RigerWu on 2018/6/10.
 */
public class NavigationController {

    private Context mContext;

    @Inject
    public NavigationController(Context context) {
        mContext = context;
    }

    public void showMain() {
        MainActivity.launch(mContext);
    }

    public void readArticleDetail(SupportFragment fromFragment, String articleUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(ArticleDetailFragment.URL_KEY, articleUrl);
        ((SupportFragment) fromFragment.getParentFragment()).start(ArticleDetailFragment.getInstance(bundle));
    }

    public void openTreeDetail(SupportFragment fromFragment, TreeData treeData) {

    }

}
