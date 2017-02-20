package com.luo.magiclamp.focus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.FocusDetails;
import com.luo.magiclamp.frame.BaseFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * FocusDetailsFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class FocusDetailsFragment extends BaseFragment {
    public static final String PARAM = "url";
    private View mRootView;
    private FocusDetails mFocusDetails;
    private String mUrl;
    private ProgressBar mProgressBar;
    private WebView mWebView;

    public FocusDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_focus_details, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFocusDetails = (FocusDetails) getArguments().getSerializable(PARAM);
            mUrl = mFocusDetails != null ? mFocusDetails.getUrl() : null;
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
        mActivity.getRightImage().setImageResource(R.mipmap.icon_share_white);
        mActivity.setOnRightImageClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFocusDetails != null) {
                    share();
                }
            }
        });
    }

    private void init() {
        findView();
        loadFocus();
    }

    private int mJumpCount = 0;  //记录跳转的次数，标记页面

    private void findView() {
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.pb_focus_details);
        mWebView = (WebView) mRootView.findViewById(R.id.wv_focus_details);

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

    private void loadFocus() {
        mWebView.loadUrl(mUrl);
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

    @Override
    public void onPause() {
        super.onPause();
        mWebView.reload();
    }

    private void share() {
        new ShareAction(getActivity()).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.MORE)
                .withTitle(mFocusDetails.getTitle())
                .withText(mFocusDetails.getCategory() + "  " + mFocusDetails.getAuthor_name())
                .withMedia(new UMImage(getActivity(), mFocusDetails.getThumbnail_pic_s()))
                .withTargetUrl(mFocusDetails.getUrl())
                .open();
    }
}