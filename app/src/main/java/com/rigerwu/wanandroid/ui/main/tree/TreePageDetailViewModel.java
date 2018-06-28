package com.rigerwu.wanandroid.ui.main.tree;

import android.databinding.ObservableField;

import com.rigerwu.wanandroid.data.DataManager;
import com.rigerwu.wanandroid.data.model.tree.TreeData;
import com.rigerwu.wanandroid.ui.base.BaseViewModel;
import com.rigerwu.wanandroid.utils.rx.SchedulerProvider;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/6/23.
 */
public class TreePageDetailViewModel extends BaseViewModel {

    public final ObservableField<String> mTitle = new ObservableField<>();

    private TreeData mTreeData;

    @Inject
    public TreePageDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void setTreeData(TreeData treeData) {
        mTreeData = treeData;
    }
}
