package com.rigerwu.wanandroid.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.rigerwu.wanandroid.ui.base.status.LottieEmptyCallback;
import com.rigerwu.wanandroid.ui.base.status.LottieErrorCallback;
import com.rigerwu.wanandroid.ui.base.status.LottieLoadingCallback;
import com.rigerwu.wanandroid.ui.base.status.LottieNetErrorCallback;

import javax.annotation.Resource;

import dagger.android.support.AndroidSupportInjection;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by RigerWu on 2018/5/21.
 */
public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel>
        extends SupportFragment {

    private View mRootView;
    private T mViewDataBinding;
    private V mViewModel;

    protected LoadService mBaseLoadService;


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
     * @return container id
     */
    public abstract @Resource
    int getContainerId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * init data and event
     */
    public abstract void initDataAndEvent();

    /**
     * called when retry
     *
     * @param v
     */
    public abstract void onNetReload(View v);

    /**
     * setup views
     */
    protected abstract void setUp();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        initView(mRootView);
        return mRootView;
    }

    // if need other init
    protected void initView(View rootView) {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
        View container = view.findViewById(getContainerId());
        mBaseLoadService = LoadSir.getDefault().register(container, (Callback.OnReloadListener) this::onNetReload);
        setUp();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initDataAndEvent();
    }


    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void showLoading() {
        mBaseLoadService.showCallback(LottieLoadingCallback.class);
    }

    public void showError() {
        mBaseLoadService.showCallback(LottieErrorCallback.class);
    }

    public void showNetError() {
        mBaseLoadService.showCallback(LottieNetErrorCallback.class);
    }

    public void showEmpty() {
        mBaseLoadService.showCallback(LottieEmptyCallback.class);
    }

}
