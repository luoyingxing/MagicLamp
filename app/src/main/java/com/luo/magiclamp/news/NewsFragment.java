package com.luo.magiclamp.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.ui.view.ViewPageFragment;
import com.luo.magiclamp.frame.ui.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NewsFragment
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class NewsFragment extends BaseFragment {
    private View mRootView;

    private List<Fragment> mTabContents = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("社会", "体育", "探索", "数码",
            "游戏", "财经", "股票", "热点", "民生");

    private ViewPagerIndicator mIndicator;

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_news, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void init() {
        findView();
        initData();
        //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);

    }

    private void findView() {
        mViewPager = (ViewPager) mRootView.findViewById(R.id.news_view_page);
        mIndicator = (ViewPagerIndicator) mRootView.findViewById(R.id.news_indicator);
    }

    private void initData() {
        mTabContents.clear();
        for (String data : mDatas) {
            ViewPageFragment fragment = ViewPageFragment.newInstance(data);
            mTabContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(mActivity.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }


}