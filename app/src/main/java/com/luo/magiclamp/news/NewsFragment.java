package com.luo.magiclamp.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.convenient.TestFragment;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * NewsFragment
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class NewsFragment extends BaseFragment {
    private View mRootView;

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

    private void init() {
        mRootView.findViewById(R.id.tv_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), BaseActivity.class);
//                intent.putExtra(Constant.ARGS_FRAGMENT_NAME, TestFragment.class.getName());
//                startActivity(intent);
            }
        });

    }

}