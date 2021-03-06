package com.luo.magiclamp.news;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.NewsDetails;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.ui.scroll.ScrollWebView;
import com.luo.magiclamp.frame.ui.view.NewsDetailImageView;
import com.luo.magiclamp.utils.TimeUtils;

import cz.msebera.android.httpclient.util.EncodingUtils;

/**
 * NewsDetailFragment
 * <p/>
 * Created by luoyingxing on 16/10/13.
 */
public class NewsDetailFragment extends BaseFragment {
    public static final String PARAM = "newsDetails";
    private View mRootView;

    private TextView mTitleTV;
    private TextView mSourceTV;
    private TextView mDigestTV;
    private NewsDetailImageView mImageOne;
    private NewsDetailImageView mImageTwo;
    private NewsDetailImageView mImageThree;
    private ScrollWebView mWebView;

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
        mActivity.setTitle("详情");
    }

    private void init() {
        findView();
        initData();
    }

    private void findView() {
        mTitleTV = (TextView) mRootView.findViewById(R.id.tv_news_details_title);
        mSourceTV = (TextView) mRootView.findViewById(R.id.tv_news_details_source);
        mDigestTV = (TextView) mRootView.findViewById(R.id.tv_news_details_digest);
        mImageOne = (NewsDetailImageView) mRootView.findViewById(R.id.iv_news_details_image_one);
        mImageTwo = (NewsDetailImageView) mRootView.findViewById(R.id.iv_news_details_image_two);
        mImageThree = (NewsDetailImageView) mRootView.findViewById(R.id.iv_news_details_image_three);
        mWebView = (ScrollWebView) mRootView.findViewById(R.id.wv_news_details_content);
    }

    private void initData() {
        mTitleTV.setText(mNewsDetails.getTitle());
        mSourceTV.setText(String.format("%s  %s", mNewsDetails.getSource(),
                TimeUtils.longToMonthDay(System.currentTimeMillis())));
        mDigestTV.setText("【摘要】" + mNewsDetails.getDigest() + "......");

        WebSettings settings = mWebView.getSettings();
        settings.setDefaultFontSize(17);

        String strUrl = "<html> \n" +
                "<head> \n" +
                "<style type=\"text/css\"> \n" +
                "body {text-align:justify; font-size: " + 17 + "px; line-height: " + 26 + "px; color:" + "#333333" + "}\n" +
                "</style> \n" +
                "</head> \n" +
                "<body>" + EncodingUtils.getString(mNewsDetails.getContent().getBytes(), "UTF-8") + "</body> \n </html>";
        mWebView.loadDataWithBaseURL(null, strUrl, "text/html; charset=UTF-8", null, null);

        if (TextUtils.isEmpty(mNewsDetails.getTopImage())) {
            mImageOne.setVisibility(View.GONE);
        } else {
            mImageOne.setHttpUri(Uri.parse(mNewsDetails.getTopImage()));
        }

        if (TextUtils.isEmpty(mNewsDetails.getTextImage0())) {
            mImageTwo.setVisibility(View.GONE);
        } else {
            mImageTwo.setHttpUri(Uri.parse(mNewsDetails.getTextImage0()));
        }

        if (TextUtils.isEmpty(mNewsDetails.getTextImage1())) {
            mImageThree.setVisibility(View.GONE);
        } else {
            mImageThree.setHttpUri(Uri.parse(mNewsDetails.getTextImage1()));
        }
    }

}