package com.rigerwu.wanandroid.ui.base.status;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.rigerwu.wanandroid.R;

/**
 * Created by RigerWu on 2018/5/25.
 */
public class LottieErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_lottie_error;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
