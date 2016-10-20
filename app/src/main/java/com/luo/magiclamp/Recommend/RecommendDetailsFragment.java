package com.luo.magiclamp.Recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * RecommendDetailsFragment
 * <p/>
 * Created by Administrator on 2016/10/19.
 */
public class RecommendDetailsFragment extends BaseFragment {
    public static final String PARAM = "url";
    private View mRootView;
    private ProgressBar mProgressBar;
    private WebView mWebView;
    private String mUrl = null;

    public RecommendDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recommend_details, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(PARAM);
            mLog.e("mUrl = " + mUrl);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("详情");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
        initData();
    }

    private int mJumpCount = 0;  //记录跳转的次数，标记页面

    private void findView() {
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.pb_recommend_details);
        mWebView = (WebView) mRootView.findViewById(R.id.wv_recommend_details);

        mWebView.requestFocusFromTouch();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                mJumpCount++;
                mLog.e("mJumpCount =  " + mJumpCount);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }

        });
    }

    private void initData() {
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebView.reload();
    }

    @Override
    public boolean onBackPressed() {
        if (mJumpCount > 1) {
            mWebView.goBack();
            mJumpCount--;
        } else {
            mActivity.goBack();
        }
        return true;
    }
}