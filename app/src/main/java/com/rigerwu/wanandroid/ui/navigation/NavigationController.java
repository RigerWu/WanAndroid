package com.rigerwu.wanandroid.ui.navigation;

import android.content.Context;

import com.rigerwu.wanandroid.ui.main.MainActivity;

import javax.inject.Inject;

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

}
