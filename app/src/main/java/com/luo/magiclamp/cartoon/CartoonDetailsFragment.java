package com.luo.magiclamp.cartoon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Cartoon;
import com.luo.magiclamp.frame.BaseFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * CartoonDetailsFragment
 * <p/>
 * Created by luoyingxing on 2017/2/26.
 */
public class CartoonDetailsFragment extends BaseFragment {
    public static final String PARAM = "param";
    private View mRootView;
    private Cartoon mCartoon;
    private ProgressBar mProgressBar;
    private WebView mWebView;

    public CartoonDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_cartoon_details, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCartoon = (Cartoon) getArguments().getSerializable(PARAM);
            mLog.e("mCartoon = " + mCartoon.toString());
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle(mCartoon == null ? "详情" : mCartoon.getTitle());
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
        mActivity.getRightImage().setImageResource(R.mipmap.icon_share_white);
        mActivity.setOnRightImageClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCartoon != null) {
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
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.pb_cartoon_details);
        mWebView = (WebView) mRootView.findViewById(R.id.wv_cartoon_details);

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
        mWebView.loadUrl(mCartoon.getLink());
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
                .withTitle(mCartoon.getTitle())
                .withText(mCartoon.getTitle() + "  ————来自神灯APP内涵漫画")
                .withMedia(new UMImage(getActivity(), ApiURL.APP_WEB_ADDRESS_IMAGE))
                .withTargetUrl(mCartoon.getLink())
                .open();
    }
}