package com.luo.magiclamp.convenient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * TestFragment
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class TestFragment extends BaseFragment {
    private View mRootView;

    public TestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_health, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setTitle("测试测试");
    }

    private void init() {
    }
}
