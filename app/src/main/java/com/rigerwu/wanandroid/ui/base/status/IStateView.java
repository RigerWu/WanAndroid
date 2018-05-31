package com.rigerwu.wanandroid.ui.base.status;

/**
 * Created by RigerWu on 2018/5/30.
 */
public interface IStateView {

    void showErrorView();

    void showNetErrorView();

    void showEmptyView();

    void showLoadingView();

    void dismissStatusView();
}
