package com.luo.magiclamp.Recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * RecommendFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class RecommendFragment extends BaseFragment {
    private View mRootView;

    public RecommendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recommend, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        findView();
        setAdapter();
    }

    private void findView() {

    }

    private void setAdapter() {

    }
}
