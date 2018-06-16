package com.rigerwu.wanandroid.ui.main.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rigerwu.wanandroid.BR;
import com.rigerwu.wanandroid.R;
import com.rigerwu.wanandroid.data.model.main.BannerData;
import com.rigerwu.wanandroid.databinding.FragmentHomePageBinding;
import com.rigerwu.wanandroid.ui.base.BaseFragment;
import com.rigerwu.wanandroid.ui.base.status.ListStatus;
import com.rigerwu.wanandroid.ui.navigation.NavigationController;
import com.rigerwu.wanandroid.ui.widget.BannerImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by RigerWu on 2018/5/27.
 */
public class HomePageFragment extends BaseFragment<FragmentHomePageBinding, HomePageViewModel> {

    private HomePageViewModel mViewModel;
    @Inject
    ViewModelProvider.Factory mFactory;
    @Inject
    NavigationController mNavigationController;

    FragmentHomePageBinding mBinding;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private HomePageAdapter mHomePageAdapter;
    private Banner mBanner;

    public static HomePageFragment newInstance() {
        Bundle args = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    public int getContainerId() {
        return R.id.refreshLayout;
    }

    @Override
    public HomePageViewModel getViewModel() {
        LogUtils.i("HomePageFragment.getViewModel->:创建viewmodel=============");
        mViewModel = ViewModelProviders.of(this, mFactory).get(HomePageViewModel.class);
        return mViewModel;
    }


    @Override
    public void initDataAndEvent() {
        mViewModel.getCompositeDisposable().add(
                mViewModel.getLoadingStatus().subscribe(this::changeViewStatus));

        mViewModel.getCompositeDisposable().add(
                mViewModel.getRefreshState().subscribe(this::changeRefreshStatus)
        );

        mViewModel.getBannerListLiveData().observe(this, this::showBannerData);

        mViewModel.getArticleListLiveData().observe(this, articleDataList -> {

            if (articleDataList != null) {
                if (!mViewModel.isHasMore()) {
                    mRefreshLayout.setNoMoreData(true);
                }
                mViewModel.setObservableList(articleDataList);
            }
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mViewModel.refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.loadMore();
            }
        });

        mHomePageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String link = mHomePageAdapter.getItem(position).getLink();
                mNavigationController.readArticleDetail(HomePageFragment.this, link);
            }
        });
    }

    private void changeRefreshStatus(ListStatus status) {
        switch (status) {
            case REFRESHING:
                break;
            case LOADING_MORE:
                break;
            case REFRESH_ERROR:
                mRefreshLayout.finishRefresh(false);
                break;
            case REFRESH_FINISH:
                mRefreshLayout.finishRefresh();
                break;
            case LOAD_MORE_ERROR:
                mRefreshLayout.finishLoadMore(false);
                break;
            case LOAD_MORE_FINISH:
                mRefreshLayout.finishLoadMore();
                break;
        }
    }


    @Override
    protected void setUp() {
        mBinding = getViewDataBinding();
        mRefreshLayout = mBinding.refreshLayout;
        mRecyclerView = mBinding.recyclerView;
        mHomePageAdapter = new HomePageAdapter(R.layout.fragment_home_item, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setAdapter(mHomePageAdapter);

        //banner
        ViewGroup view = (ViewGroup) View.inflate(_mActivity, R.layout.banner_layout, null);
        mBanner = view.findViewById(R.id.banner);
        view.removeView(mBanner);
        mHomePageAdapter.addHeaderView(mBanner);

    }

    public void showBannerData(List<BannerData> bannerDataList) {
        List<String> bannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        List<String> bannerUrlList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            bannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            bannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(bannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                LogUtils.i("HomePageFragment.OnBannerClick->:" + position);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    protected void onRetryCall() {
        mViewModel.refresh();
    }
}
