package com.rigerwu.wanandroid.ui.main.tree;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.blankj.utilcode.util.LogUtils;
import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.data.model.tree.TreeData;
import com.rigerwu.wanandroid.ui.base.BaseFragment;
import com.rigerwu.wanandroid.ui.base.BaseViewModel;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/6/16.
 */
public class TreePageViewModel extends BaseViewModel {

    private MutableLiveData<List<TreeData>> mTreeListLiveData;

    public final ObservableList<TreeData> mTreeDataObservableList = new ObservableArrayList<>();

    @Inject
    public TreePageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        if (mTreeListLiveData == null) {
            mTreeListLiveData = new MutableLiveData<>();
        }

        initData();
    }

    private void initData() {
        getLoadingStatus().onNext(BaseFragment.STATUS_LOADING);

        getCompositeDisposable().add(getDataManager()
                .loadTreeDatas()
                .take(1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(treeData -> {
                    if (treeData == null || treeData.size() == 0) {
                        fetchTreeData();
                    } else {
                        LogUtils.i("TreePageViewModel.initData->:数据库设置");
                        mTreeListLiveData.setValue(treeData);
                    }
                }));
    }

    private void fetchTreeData() {
        getCompositeDisposable().add(getDataManager()
                .getTreeData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(listBaseResponse -> {
                    if (listBaseResponse != null && listBaseResponse.isSuccess()) {
                        List<TreeData> data = listBaseResponse.getData();
                        LogUtils.i("TreePageViewModel.fetchTreeData->:网络设置");
                        mTreeListLiveData.setValue(data);
                        getLoadingStatus().onNext(BaseFragment.STATUS_NOMAL);
                    } else {
                        getLoadingStatus().onNext(BaseFragment.STATUS_ERROR);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    getLoadingStatus().onNext(BaseFragment.STATUS_NET_ERROR);
                }));

    }

    public void refresh() {
        getLoadingStatus().onNext(BaseFragment.STATUS_LOADING);
        fetchTreeData();
    }

    public MutableLiveData<List<TreeData>> getTreeListLiveData() {
        return mTreeListLiveData;
    }

    public void setObservableTreeDataList(List<TreeData> treeDataList) {
        mTreeDataObservableList.clear();
        mTreeDataObservableList.addAll(treeDataList);
    }

}
