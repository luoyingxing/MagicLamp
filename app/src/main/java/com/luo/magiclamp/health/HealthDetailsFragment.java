package com.luo.magiclamp.health;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.HealthDetails;
import com.luo.magiclamp.entity.JokeList;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.scroll.ScrollWebView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import cz.msebera.android.httpclient.util.EncodingUtils;

/**
 * HealthDetailsFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class HealthDetailsFragment extends BaseFragment {
    public static final String PARAM = "id";

    private View mRootView;
    private ScrollWebView mWebView;
    private TextView mTitleTV;
    private TextView mVisitTV;
    private TextView mCollectTV;
    private TextView mDiscussTV;

    private int mId = 0;

    public HealthDetailsFragment() {
    }

    public static HealthDetailsFragment newInstance(int id) {
        HealthDetailsFragment fragment = new HealthDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(PARAM);
            mLog.e("id = " + mId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_health_details, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setTitle("详情");
        mActivity.getRightImage().setImageResource(R.mipmap.icon_share_white);
        mActivity.setOnRightImageClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mHealthDetails != null) {
                    share();
                }
            }
        });
    }

    private void init() {
        findView();
        loadDetails();
    }

    private void findView() {
        mWebView = (ScrollWebView) mRootView.findViewById(R.id.wv_health_details);
        mTitleTV = (TextView) mRootView.findViewById(R.id.tv_health_details_title);
        mVisitTV = (TextView) mRootView.findViewById(R.id.tv_health_details_count);
        mCollectTV = (TextView) mRootView.findViewById(R.id.tv_health_details_collect);
        mDiscussTV = (TextView) mRootView.findViewById(R.id.tv_health_details_discuss);
    }

    private HealthDetails mHealthDetails;

    private void loadDetails() {
        showDialog();
        new ApiRequest<HealthDetails>(ApiURL.API_HEALTH_DETAILS, true) {
            @Override
            protected void onSuccess(HealthDetails result) {
                if (result != null) {
                    mHealthDetails = result;
                    show(result.getMessage());
                    mTitleTV.setText(result.getTitle());
                    mVisitTV.setText("" + result.getCount());
                    mCollectTV.setText("" + result.getFcount());
                    mDiscussTV.setText("" + result.getRcount());
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.addParam("id", mId)
                .get();
    }

    private void show(String content) {
        WebSettings settings = mWebView.getSettings();
        settings.setDefaultFontSize(17);
        settings.setJavaScriptEnabled(true);

        String strUrl = "<html> \n" +
                "<head> \n" +
                "<style type=\"text/css\"> \n" +
                "body {text-align:justify; font-size: " + 17 + "px; line-height: " + 26 + "px; color:" + "#333333" + "}\n" +
                "</style> \n" +
                "</head> \n" +
                "<body>" + EncodingUtils.getString(content.getBytes(), "UTF-8") + "</body> \n </html>";
        mWebView.loadDataWithBaseURL(null, strUrl, "text/html; charset=UTF-8", null, null);
    }

    private void share() {
        new ShareAction(getActivity()).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.MORE)
                .withTitle(mHealthDetails.getTitle())
                .withText(mHealthDetails.getTitle())
                .withMedia(new UMImage(getActivity(), mHealthDetails.getImg()))
                .withTargetUrl(mHealthDetails.getUrl())
                .open();
    }

}