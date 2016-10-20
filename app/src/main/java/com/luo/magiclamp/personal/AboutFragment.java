package com.luo.magiclamp.personal;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;

/**
 * AboutFragment
 * <p/>
 * Created by luoyingxing on 16/10/20.
 */
public class AboutFragment extends BaseFragment implements View.OnClickListener {
    private View mRootView;

    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_about, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("关于软件");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
    }

    public void findView() {
        TextView tvVersion = (TextView) mRootView.findViewById(R.id.tv_about_us_0);
        tvVersion.setText(getVersion());
        mRootView.findViewById(R.id.item_fragment_about_us_1).setOnClickListener(this);
        mRootView.findViewById(R.id.item_fragment_about_us_3).setOnClickListener(this);
        mRootView.findViewById(R.id.item_fragment_about_us_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_fragment_about_us_1:
                copyString(getString(R.string.about_us_string_1),
                        getString(R.string.about_us_string_5));
                break;
            case R.id.item_fragment_about_us_3:
                copyString(getString(R.string.about_us_string_3),
                        getString(R.string.about_us_string_7));
                break;
            case R.id.item_fragment_about_us_4:
                copyString(getString(R.string.about_us_string_4),
                        getString(R.string.about_us_string_8));
                break;
            default:
                break;
        }
    }

    public String getVersion() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String version = info.versionName;
            return this.getString(R.string.about_us_string_12) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return this.getString(R.string.app_name) + "no version!";
        }
    }

    private void copyString(String key, String text) {
        ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(null, text.trim()));
        showToast(key + "已经复制到剪贴板");
    }
}
