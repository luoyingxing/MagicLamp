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

import java.util.ArrayList;
import java.util.List;

/**
 * FocusFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class FocusFragment extends BaseFragment {
    private View mRootView;

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;

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
        showDialog();
        new ApiRequest<Focus>(ApiURL.API_FOCUS_CLASSIFY, true) {
            @Override
            protected void onSuccess(Focus result) {
                if (result.getTngou() != null) {
                    setAdapter(result);
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.get();
    }

    private void setAdapter(Focus result) {
        List<Integer> mId = new ArrayList<>();
        List<String> mTitles = new ArrayList<>();
        mId.add(0);
        mTitles.add("最新");

        for (int i = 0; i < result.getTngou().size(); i++) {
            mTitles.add(result.getTngou().get(i).getName().substring(0, 2));
            mId.add(result.getTngou().get(i).getId());
        }

        FocusViewPagerAdapter mAdapter = new FocusViewPagerAdapter(getContext(), mId);
        //设置Tab上的标题
        mIndicator.setTabItemTitles(mTitles);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);
    }
}