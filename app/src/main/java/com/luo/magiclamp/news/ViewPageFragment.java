package com.luo.magiclamp.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * ViewPageFragment
 * <p/>
 * create by luoyingxing on 2016/10/09
 */
public class ViewPageFragment extends BaseFragment {
    public static final String TABLE_NUM = "tableNum";
    private int mTableNum;

    private View mRootView;

    public ViewPageFragment() {
    }

    public static ViewPageFragment newInstance(int mTableNum) {
        ViewPageFragment fragment = new ViewPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TABLE_NUM, mTableNum);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTableNum = arguments.getInt(TABLE_NUM);
            mLog.e("mTableNum = " + mTableNum);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_news_details, container, false);
        init();
        return mRootView;
    }

    private void init() {

    }

}
