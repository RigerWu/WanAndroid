package com.rigerwu.wanandroid.ui.base.status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.rigerwu.wanandroid.R;


/**
 * Created by RigerWu on 2018/5/30.
 */
public class StatusViewController implements IStateView {

    private int mEmptyViewRes = R.layout.layout_lottie_empty;
    private int mNetErrorViewRes = R.layout.layout_lottie_net_error;
    private int mErrorViewRes = R.layout.layout_lottie_error;
    private int mLoadingViewRes = R.layout.layout_lottie_loading;

    private View mEmptyView;
    private View mNetErrorView;
    private View mErrorView;
    private View mLoadingView;

    private Context mContext;
    private View mBindView;
    private ViewGroup.LayoutParams mLayoutParams;
    private ViewGroup mParentView;
    private int mCurrentIndex;
    private OnRetryClickListener mListener;

    public StatusViewController(Context context, View bindView) {
        mContext = context;
        mBindView = bindView;
        init();
    }

    public void setOnRetryClickListener(OnRetryClickListener listener) {
        mListener = listener;
    }

    public interface OnRetryClickListener {

        void onRetry(View view);

    }


    private void init() {
        mLayoutParams = mBindView.getLayoutParams();
        if (mBindView.getParent() != null) {
            mParentView = (ViewGroup) mBindView.getParent();
        } else {
            mParentView = mBindView.getRootView().findViewById(android.R.id.content);
        }
        int childCount = mParentView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (mParentView.getChildAt(i) == mBindView) {
                mCurrentIndex = i;
                break;
            }
        }
    }

    @Override
    public void showErrorView() {
        if (mErrorView != null) {
            showView(mErrorView);
        } else {
            mErrorView = LayoutInflater.from(mContext).inflate(mErrorViewRes, mParentView, false);
            if (mListener != null) {
                updateDefaultView(mErrorView, mListener);
            }
        }


    }

    @Override
    public void showNetErrorView() {
        if (mNetErrorView == null) {
            mNetErrorView = LayoutInflater.from(mContext).inflate(mNetErrorViewRes, mParentView, false);
            if (mListener != null) {
                updateDefaultView(mNetErrorView, mListener);
            }
        }
        showView(mNetErrorView);
    }

    @Override
    public void showEmptyView() {
        if (mEmptyView == null) {
            mEmptyView = LayoutInflater.from(mContext).inflate(mEmptyViewRes, mParentView, false);
            if (mListener != null) {
                updateDefaultView(mEmptyView, mListener);
            }
        }
        showView(mEmptyView);
    }

    @Override
    public void showLoadingView() {
        if (mLoadingView == null) {
            mLoadingView = LayoutInflater.from(mContext).inflate(mLoadingViewRes, mParentView, false);
        }
        showView(mLoadingView);
    }

    @Override
    public void dismissStatusView() {
        showView(mBindView);
    }

    private void showView(View statusView) {
        if (mParentView.getChildAt(mCurrentIndex) != statusView) {
            ViewParent parent = statusView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(statusView);
            }
            mParentView.removeViewAt(mCurrentIndex);
            mParentView.addView(statusView, mCurrentIndex, mLayoutParams);
        }
    }

    private void updateDefaultView(View updateView, OnRetryClickListener listener) {
        updateView.setOnClickListener(listener::onRetry);
    }
}
