package com.luo.magiclamp.recommend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.RecommendDetails;
import com.luo.magiclamp.frame.BaseFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * RecommendDetailsFragment
 * <p/>
 * Created by Administrator on 2016/10/19.
 */
public class RecommendDetailsFragment extends BaseFragment {
    public static final String PARAM = "RecommendDetails";
    private View mRootView;
    private ProgressBar mProgressBar;
    private WebView mWebView;
    private RecommendDetails mRecommendDetails;
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
            mRecommendDetails = (RecommendDetails) getArguments().getSerializable(PARAM);
            if (mRecommendDetails != null) {
                mUrl = mRecommendDetails.getUrl();
                mLog.e("mUrl = " + mUrl);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("详情");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
        mActivity.getRightImage().setImageResource(R.mipmap.icon_share_white);
        mActivity.setOnRightImageClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecommendDetails != null) {
                    share();
                }
            }
        });
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

    private void share() {
        new ShareAction(getActivity()).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.MORE)
                .withTitle(mRecommendDetails.getTitle())
                .withText(mRecommendDetails.getSource())
                .withMedia(new UMImage(getActivity(), mRecommendDetails.getFirstImg()))
                .withTargetUrl(mRecommendDetails.getUrl())
                .open();
    }
}