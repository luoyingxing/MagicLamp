package com.luo.magiclamp.health;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * HealthDetailsFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class HealthDetailsFragment extends BaseFragment {
    public static final String PARAM = "id";

    private View mRootView;

    private int mId = 0;

    public HealthDetailsFragment() {
    }

    public static HealthDetailsFragment newInstance(int id) {
        HealthDetailsFragment fragment = new HealthDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(PARAM);
            mLog.e("id = " + mId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_health_details, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setTitle("详情");
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
