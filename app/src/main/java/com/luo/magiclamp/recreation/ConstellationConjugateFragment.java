package com.luo.magiclamp.recreation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * ConstellationConjugateFragment
 * <p/>
 * Created by luoyingxing on 16/10/17.
 */
public class ConstellationConjugateFragment extends BaseFragment {
    private View mRootView;

    public ConstellationConjugateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_constellation_conjugate, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setTitle("星座配对");
    }

    private void init() {
    }
}
