package com.rigerwu.wanandroid.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.blankj.utilcode.util.LogUtils;
import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by RigerWu on 2018/5/21.
 */
public abstract class BaseViewModel extends ViewModel {

    private final DataManager mDataManager;

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private PublishSubject<Integer> mLoadingStatus = PublishSubject.create();

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        mDataManager = dataManager;
        mSchedulerProvider = schedulerProvider;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public PublishSubject<Integer> getLoadingStatus() {
        LogUtils.i("BaseViewModel.getLoadingStatus->:" + this.getClass().getSimpleName());
        return mLoadingStatus;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }


}
