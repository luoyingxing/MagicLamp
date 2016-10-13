package com.luo.magiclamp.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.NewsDetails;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * NewsDetailFragment
 * <p/>
 * Created by luoyingxing on 16/10/13.
 */
public class NewsDetailFragment extends BaseFragment {
    public static final String PARAM = "newsDetails";
    private View mRootView;

    private NewsDetails mNewsDetails;

    public NewsDetailFragment() {
    }

    public static NewsDetailFragment newInstance(NewsDetails newsDetails) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM, newsDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsDetails = (NewsDetails) getArguments().getSerializable(PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_news_detail, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setTitle(mNewsDetails.getTitle());
    }

    private void init() {
    }
}
