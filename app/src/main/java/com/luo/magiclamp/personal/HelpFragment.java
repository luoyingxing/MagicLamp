package com.luo.magiclamp.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

import cz.msebera.android.httpclient.util.EncodingUtils;

/**
 * HelpFragment
 * <p/>
 * Created by luoyingxing on 16/10/20.
 */
public class HelpFragment extends BaseFragment {
    private View mRootView;

    public HelpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_help, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("帮助");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
    }

    private void findView() {
        WebView mWebView = (WebView) mRootView.findViewById(R.id.wv_help);

        String content = "<h3>" + getResources().getString(R.string.personal_help_title) + "</h3>\n" +
                "<br>\n" +
                "<font color=#ea8010>" + getResources().getString(R.string.personal_help_news) + "</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#9d55b8>" + getResources().getString(R.string.personal_help_recommend) + "</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#f4c600>" + getResources().getString(R.string.personal_help_focus) + "</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#56abe4>" + getResources().getString(R.string.personal_help_recreation) + "</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#11cd6e>" + getResources().getString(R.string.personal_help_health) + "</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#db639b>" + getResources().getString(R.string.personal_help_personal) + "</font>\n" +
                "</br>";

        String strUrl = "<html> \n<body>" +
                EncodingUtils.getString(content.getBytes(), "UTF-8") +
                "</body>\n</html>";

        showDialog();
        mWebView.loadDataWithBaseURL(null, strUrl, "text/html; charset=UTF-8", null, null);

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    hideDialog();
                }
            }

        });
    }

}
