package com.rigerwu.wanandroid.ui.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.rigerwu.wanandroid.di.Injectable;
import com.rigerwu.wanandroid.utils.CommonUtils;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by RigerWu on 2018/5/21.
 */
public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends SupportActivity implements Injectable {

    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        performDataBinding();
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput(this);
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isConnected();
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


}
