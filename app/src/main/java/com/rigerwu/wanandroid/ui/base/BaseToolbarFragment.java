package com.rigerwu.wanandroid.ui.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rigerwu.wanandroid.R;

/**
 * Fragment with toolbar
 * Created by RigerWu on 2018/5/26.
 */
public abstract class BaseToolbarFragment<T extends ViewDataBinding, V extends BaseViewModel>
        extends BaseFragment<T, V> {

    protected Toolbar mToolbar;

    @Override
    protected void initView(View rootView) {
        mToolbar = rootView.findViewById(R.id.toolbar);
        setUpToolBar();
    }

    protected abstract void setUpToolBar();

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> pop());
    }
}
