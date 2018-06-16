package com.rigerwu.wanandroid.ui.main.tree;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.data.model.tree.TreeData;

import java.util.List;

/**
 * Created by RigerWu on 2018/6/16.
 */
public class TreePageAdapter extends BaseQuickAdapter<TreeData, TreePageAdapter.TreeViewHolder> {

    public TreePageAdapter(int layoutResId, @Nullable List<TreeData> data) {
        super(layoutResId, data);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View root = binding.getRoot();
        root.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return root;
    }

    @Override
    protected void convert(TreeViewHolder helper, TreeData item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.treeData, item);
        binding.executePendingBindings();

    }

    public static class TreeViewHolder extends BaseViewHolder {

        public TreeViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}
