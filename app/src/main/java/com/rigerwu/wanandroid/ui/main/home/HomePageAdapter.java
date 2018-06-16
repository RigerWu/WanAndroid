package com.rigerwu.wanandroid.ui.main.home;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.data.model.main.ArticleData;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

/**
 * Created by RigerWu on 2018/5/29.
 */
public class HomePageAdapter extends BaseQuickAdapter<ArticleData, HomePageAdapter.HomeViewHolder> {

    public HomePageAdapter(int layoutResId, @Nullable List<ArticleData> data) {
        super(layoutResId, data);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    @Override
    protected void convert(HomeViewHolder helper, ArticleData item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.article, item);
        binding.executePendingBindings();
        ((ShineButton) helper.getView(R.id.shine_button_collect)).setChecked(item.isCollect());

    }

    public static class HomeViewHolder extends BaseViewHolder {
        public HomeViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }

}
