package com.luo.magiclamp;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.luo.magiclamp.Recommend.RecommendFragment;
import com.luo.magiclamp.focus.FocusFragment;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.health.HealthFragment;
import com.luo.magiclamp.news.NewsFragment;
import com.luo.magiclamp.recreation.RecreationFragment;
import com.luo.magiclamp.utils.WindowUtils;
import com.umeng.analytics.MobclickAgent;


public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {
    private static MainActivity mInstance;
    private FragmentTabHost mTabHost;
    private ImageView mImageView;

    private long mExitTime;
    private int[] mTabIcon = {R.drawable.tab_icon_selector_news,
            R.drawable.tab_icon_selector_recommend,
            R.drawable.tab_icon_selector_focus,
            R.drawable.tab_icon_selector_recreation,
            R.drawable.tab_icon_selector_health};
    private String[] mTabText = {"新闻", "精选", "热点", "娱乐", "健康"};
    private String mCurrentTabTag;

    public static MainActivity getInstance() {
        return mInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        mInstance = this;

        mImageView = (ImageView) findViewById(R.id.main_image_view);
        setTopImage(R.drawable.main_iamge_bg);
        setTopImageParams(WindowUtils.getStatusBarHeight(getApplicationContext()));

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_main);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[0]).setIndicator(getIndicatorView(0)),
                NewsFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[1]).setIndicator(getIndicatorView(1)),
                RecommendFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[2]).setIndicator(getIndicatorView(2)),
                FocusFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[3]).setIndicator(getIndicatorView(3)),
                RecreationFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[4]).setIndicator(getIndicatorView(4)),
                HealthFragment.class, null);
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.white);

        mCurrentTabTag = mTabText[0];
        mTabHost.setOnTabChangedListener(this);

    }

    public void setTopImage(int image) {
        mImageView.setImageResource(image);
    }

    public void setTopImageParams(int height) {
        mImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public TabHost getTabHost() {
        return mTabHost;
    }

    private View getIndicatorView(int index) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        imageView.setImageResource(mTabIcon[index]);
        textView.setText(mTabText[index]);
        return view;
    }

    public void showTab(boolean show) {
        mTabHost.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        if (!mTabText[1].equals(tabId)) {
            mCurrentTabTag = tabId;
        }

        switch (getTabHost().getCurrentTab()) {
            case 0:
                setTopImage(R.drawable.main_iamge_bg);
                setTopImageParams(WindowUtils.getStatusBarHeight(getApplicationContext()));
                mLog.e("新闻");
                break;
            case 1:
                setTopImage(R.mipmap.bg_main_top);
                setTopImageParams(100);
                mLog.e("精选");
                break;
            case 2:
                setTopImage(R.drawable.main_iamge_bg);
                setTopImageParams(WindowUtils.getStatusBarHeight(getApplicationContext()));
                mLog.e("热点");
                break;
            case 3:
                setTopImage(R.mipmap.bg_main_top);
                setTopImageParams(100);
                mLog.e("娱乐");
                break;
            case 4:
                setTopImage(R.drawable.main_iamge_bg);
                setTopImageParams(100);
                mLog.e("健康");
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}