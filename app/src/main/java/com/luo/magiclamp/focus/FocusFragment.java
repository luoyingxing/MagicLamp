package com.luo.magiclamp.focus;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Focus;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.view.ViewPagerIndicator;

import java.util.Arrays;

/**
 * FocusFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class FocusFragment extends BaseFragment {
    private View mRootView;

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;

    /**
     * top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),
     * tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    private String[] mTitle = new String[]{"头条", "社会", "国内", "国际", "娱乐", "体育",
            "军事", "科技", "财经", "时尚",};
    private String[] mTitleKey = new String[]{"top", "shehui", "guonei", "guoji", "yule", "tiyu",
            "junshi", "keji", "caijing", "shishang",};

    public FocusFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_focus, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        findView();
        loadTitleList();
    }

    private void findView() {
        mViewPager = (ViewPager) mRootView.findViewById(R.id.focus_view_page);
        mIndicator = (ViewPagerIndicator) mRootView.findViewById(R.id.focus_indicator);
    }

    private void loadTitleList() {
        FocusViewPagerAdapter mAdapter = new FocusViewPagerAdapter(getContext(), Arrays.asList(mTitleKey));
        //设置Tab上的标题
        mIndicator.setTabItemTitles(Arrays.asList(mTitle));
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);
    }
}