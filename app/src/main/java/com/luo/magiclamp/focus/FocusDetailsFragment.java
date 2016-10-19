package com.luo.magiclamp.focus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.FocusDetails;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;

/**
 * FocusDetailsFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class FocusDetailsFragment extends BaseFragment {
    public static final String PARAM = "id";
    private View mRootView;
    private int mId = 0;
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
            mId = getArguments().getInt(PARAM);
            mLog.e("mId = " + mId);
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
        loadFocus();
    }

    private int mJumpCount = 0;  //记录跳转的次数，标记页面

    private void findView() {
        mWebView = (WebView) mRootView.findViewById(R.id.wv_focus_details);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void loadFocus() {
        new ApiRequest<FocusDetails>(ApiURL.API_FOCUS_DETAILS, true) {
            @Override
            protected void onSuccess(FocusDetails result) {
                mWebView.loadUrl(result.getFromurl());
            }

        }.addParam("id", mId)
                .get();

    }
}