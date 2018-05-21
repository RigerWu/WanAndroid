package com.rigerwu.wanandroid.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by RigerWu on 2018/5/21.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);
}
