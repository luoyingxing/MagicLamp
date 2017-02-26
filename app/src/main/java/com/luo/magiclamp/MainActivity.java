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

import com.iflytek.autoupdate.IFlytekUpdate;
import com.iflytek.autoupdate.IFlytekUpdateListener;
import com.iflytek.autoupdate.UpdateConstants;
import com.iflytek.autoupdate.UpdateErrorCode;
import com.iflytek.autoupdate.UpdateInfo;
import com.luo.magiclamp.cartoon.CartoonFragment;
import com.luo.magiclamp.recommend.RecommendFragment;
import com.luo.magiclamp.focus.FocusFragment;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.health.HealthFragment;
import com.luo.magiclamp.personal.PersonalFragment;
import com.luo.magiclamp.recreation.RecreationFragment;
import com.luo.magiclamp.utils.WindowUtils;
import com.umeng.analytics.MobclickAgent;


public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener {
    private static MainActivity mInstance;
    private FragmentTabHost mTabHost;
    private ImageView mImageView;

    private long mExitTime;
    private int[] mTabIcon = {R.drawable.tab_icon_selector_focus,
            R.drawable.tab_icon_selector_recommend,
            R.drawable.tab_icon_selector_news,
            R.drawable.tab_icon_selector_recreation,
            R.drawable.tab_icon_selector_health,
            R.drawable.tab_icon_selector_personal};
    private String[] mTabText = {"热点", "精选", "内涵", "娱乐", "健康", "个人"};
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
                FocusFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[1]).setIndicator(getIndicatorView(1)),
                RecommendFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[2]).setIndicator(getIndicatorView(2)),
                CartoonFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[3]).setIndicator(getIndicatorView(3)),
                RecreationFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[4]).setIndicator(getIndicatorView(4)),
                HealthFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(mTabText[5]).setIndicator(getIndicatorView(5)),
                PersonalFragment.class, null);
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.white);

        mCurrentTabTag = mTabText[0];
        mTabHost.setOnTabChangedListener(this);

        updateApp();
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
                mLog.e("热点");
                break;
            case 1:
                setTopImage(R.mipmap.bg_image_defualt);
                setTopImageParams(180);
                mLog.e("精选");
                break;
            case 2:
                setTopImage(R.drawable.main_iamge_bg);
                setTopImageParams(100);
                mLog.e("内涵");
                break;
            case 3:
                setTopImage(R.mipmap.bg_image_defualt);
                setTopImageParams(100);
                mLog.e("娱乐");
                break;
            case 4:
                setTopImage(R.drawable.main_iamge_bg);
                setTopImageParams(100);
                mLog.e("健康");
                break;
            case 5:
                setTopImage(R.drawable.main_iamge_bg);
                setTopImageParams(100);
                mLog.e("个人");
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void updateApp() {
        //初始化自动更新对象
        final IFlytekUpdate updManager = IFlytekUpdate.getInstance(mInstance);
        //开启调试模式,默认不开启
        updManager.setDebugMode(true);
        //开启wifi环境下检测更新,仅对自动更新有效,强制更新则生效
        updManager.setParameter(UpdateConstants.EXTRA_WIFIONLY, "true");
        //设置通知栏使用应用icon
        updManager.setParameter(UpdateConstants.EXTRA_NOTI_ICON, "false");
        //设置更新提示类型,默认为通知栏提示
        updManager.setParameter(UpdateConstants.EXTRA_STYLE, UpdateConstants.UPDATE_UI_DIALOG);
        // 启动自动更新
        updManager.autoUpdate(mInstance, new IFlytekUpdateListener() {
            @Override
            public void onResult(int code, UpdateInfo result) {
                if (code == UpdateErrorCode.OK && result != null) {
//                    if (result.getUpdateType() == UpdateType.NoNeed) {
//                        showToast("已经是最新版本！");
//                        return;
//                    }
                    updManager.showUpdateInfo(mInstance, result);
                } else {
//                    showToast("请求更新失败！");
                }
            }
        });
    }

}