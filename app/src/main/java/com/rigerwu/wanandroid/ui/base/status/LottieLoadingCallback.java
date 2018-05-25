package com.rigerwu.wanandroid.ui.base.status;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.rigerwu.wanandroid.R;

public class LottieLoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_lottie_loading;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}