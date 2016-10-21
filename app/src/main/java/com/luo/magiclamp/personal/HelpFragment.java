package com.luo.magiclamp.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.ui.scroll.ScrollWebView;

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

        String content = "<h3>“神灯”是一款集新闻、精选、热点、娱乐、健康和工具于一体的APP。[PS]您可将您的需求或建议发送至开发者邮箱：luoyingxing@126.com</h3>\n" +
                "<br>\n" +
                "<font color=#ea8010>[新闻]可以看到当前滚动的新闻时事。</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#9d55b8>[精选]可以浏览一些精选的视频或段子，愉悦你的心情。</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#f4c600>[热点]可以查看到当前热点的新闻时事。</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#56abe4>[娱乐]这里有你喜欢的文本笑话和图文笑话，让你爆笑一刻。</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#11cd6e>[健康]在这里，你可以有针对的了解相应的健康知识。</font>\n" +
                "</br>\n" +
                "<br>\n" +
                "<font color=#db639b>[个人]里面有个工具箱，你可以通过反馈或者联系开发者进行增加您想要的工具或插件，满足您的需求。</font>\n" +
                "</br>";

        String strUrl = "<html> \n<body>" +
                EncodingUtils.getString(content.getBytes(), "UTF-8") +
                "</body>\n</html>";

        mWebView.loadDataWithBaseURL(null, strUrl, "text/html; charset=UTF-8", null, null);
    }

}
