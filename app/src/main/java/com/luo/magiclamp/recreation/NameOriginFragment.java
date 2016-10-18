package com.luo.magiclamp.recreation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.NameOrigin;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;

/**
 * NameOriginFragment
 * <p/>
 * Created by Administrator on 2016/10/17.
 */
public class NameOriginFragment extends BaseFragment implements View.OnTouchListener {
    private View mRootView;
    private EditText mInputEd;
    private TextView mTitleTV;
    private TextView mContentTV;

    public NameOriginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_name_origin, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("姓氏起源");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
        loadNameOrigin("诸葛");
    }

    private void findView() {
        mInputEd = (EditText) mRootView.findViewById(R.id.ed_name_origin_input);
        TextView mSearchTV = (TextView) mRootView.findViewById(R.id.tv_name_origin_search);
        mTitleTV = (TextView) mRootView.findViewById(R.id.tv_name_origin_title);
        mContentTV = (TextView) mRootView.findViewById(R.id.tv_name_origin_content);

        mSearchTV.setOnTouchListener(NameOriginFragment.this);
        mSearchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mInputEd.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    showToast("请输入姓氏");
                } else {
                    mInputEd.setText("");
                    loadNameOrigin(name);
                }
            }
        });
    }

    private void loadNameOrigin(String name) {
        showDialog();
        new ApiRequest<NameOrigin>(ApiURL.API_NAME_ORIGIN, true) {
            @Override
            protected void onSuccess(NameOrigin result) {
                if (result.getResult() != null) {
                    mTitleTV.setText(result.getResult().getXing());
                    mContentTV.setText(Html.fromHtml(result.getResult().getIntro()));
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.addParam("key", Constant.API_KEY_NAME_ORIGIN)
                .addParam("xingshi", name)
                .post();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.90f, 1.0f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.90f, 1.0f);
                ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY).setDuration(200).start();
                break;
        }
        return false;
    }
}
