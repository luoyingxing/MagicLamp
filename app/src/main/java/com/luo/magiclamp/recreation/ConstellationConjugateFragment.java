package com.luo.magiclamp.recreation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.ConstellationConjugate;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.scroll.ScrollGridView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ConstellationConjugateFragment
 * <p/>
 * Created by luoyingxing on 16/10/17.
 */
public class ConstellationConjugateFragment extends BaseFragment implements View.OnTouchListener {
    private View mRootView;

    private ScrollGridView mGridView;
    private GridViewAdapter mGridViewAdapter;

    private TextView mStartTV;
    private TextView mTitleTV;
    private TextView mContentOneTV;
    private TextView mContentTwoTV;

    private Map<Integer, Boolean> mCheckMap = new HashMap<>();

    public ConstellationConjugateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_constellation_conjugate, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("星座配对");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
        initMap();
        setAdapter();
        initData();
        loadData("白羊座", "金牛座");
    }

    private void findView() {
        mGridView = (ScrollGridView) mRootView.findViewById(R.id.gv_constellation_conjugate_check);
        mStartTV = (TextView) mRootView.findViewById(R.id.tv_constellation_conjugate_start);
        mTitleTV = (TextView) mRootView.findViewById(R.id.tv_constellation_conjugate_title);
        mContentOneTV = (TextView) mRootView.findViewById(R.id.tv_constellation_conjugate_content_one);
        mContentTwoTV = (TextView) mRootView.findViewById(R.id.tv_constellation_conjugate_content_two);

        mStartTV.setOnTouchListener(ConstellationConjugateFragment.this);
        mStartTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkMap()) {
                    if (mRequestContent.size() == 1) {
                        loadData(getNameFromPosition(mRequestContent.get(0)), getNameFromPosition(mRequestContent.get(0)));
                    } else {
                        loadData(getNameFromPosition(mRequestContent.get(0)), getNameFromPosition(mRequestContent.get(1)));
                    }
                } else {
                    showToast("请选择1到2个星座进行匹配");
                }
            }
        });
    }

    private void initData() {
        // 白羊座、金牛座、双子座、巨蟹座、狮子座、处女座、天秤座、天蝎座、射手座、摩羯座、水瓶座、双鱼座。
        List<Check> checks = new ArrayList<>();
        checks.add(new Check("白羊座", R.mipmap.icon_aries));
        checks.add(new Check("金牛座", R.mipmap.icon_taurus));
        checks.add(new Check("双子座", R.mipmap.icon_gemini));
        checks.add(new Check("巨蟹座", R.mipmap.icon_cancer));
        checks.add(new Check("狮子座", R.mipmap.icon_leo));
        checks.add(new Check("处女座", R.mipmap.icon_virgo));
        checks.add(new Check("天秤座", R.mipmap.icon_libra));
        checks.add(new Check("天蝎座", R.mipmap.icon_scorpio));
        checks.add(new Check("射手座", R.mipmap.icon_sagittarius));
        checks.add(new Check("摩羯座", R.mipmap.icon_capricornus));
        checks.add(new Check("水瓶座", R.mipmap.icon_aquarius));
        checks.add(new Check("双鱼座", R.mipmap.icon_pisces));
        mGridViewAdapter.addAll(checks);
    }

    private void initMap() {
        for (int i = 0; i < 12; i++) {
            mCheckMap.put(i, false);
        }
    }

    private List<Integer> mRequestContent = new ArrayList<>();

    private boolean checkMap() {
        mRequestContent.clear();
        int temp = 0;
        for (int i = 0; i < mCheckMap.size(); i++) {
            if (mCheckMap.get(i)) {
                mRequestContent.add(i);
                temp++;
            }
        }
        return temp <= 2 && temp > 0;
    }

    private String getNameFromPosition(int pos) {
        // 白羊座、金牛座、双子座、巨蟹座、狮子座、处女座、天秤座、天蝎座、射手座、摩羯座、水瓶座、双鱼座。
        String name = "白羊座";
        switch (pos) {
            case 0:
                name = "白羊座";
                break;
            case 1:
                name = "金牛座";
                break;
            case 2:
                name = "双子座";
                break;
            case 3:
                name = "巨蟹座";
                break;
            case 4:
                name = "狮子座";
                break;
            case 5:
                name = "处女座";
                break;
            case 6:
                name = "天秤座";
                break;
            case 7:
                name = "天蝎座";
                break;
            case 8:
                name = "射手座";
                break;
            case 9:
                name = "摩羯座";
                break;
            case 10:
                name = "水瓶座";
                break;
            case 11:
                name = "双鱼座";
                break;
        }
        return name;
    }

    private void setAdapter() {
        mGridViewAdapter = new GridViewAdapter(getActivity());
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(new ItemClickListener());
    }

    private void loadData(String nameOne, String nameTwo) {
        showDialog();

        String one = null;
        String two = null;
        try {
            one = URLEncoder.encode(nameOne, "utf-8");
            two = URLEncoder.encode(nameTwo, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        new ApiRequest<ConstellationConjugate>(ApiURL.API_CHAT_CONSTELLATION_CONJUGATE) {

            @Override
            protected void onSuccess(ConstellationConjugate result) {
                if (result.getResult() != null) {
                    mTitleTV.setText(result.getResult().getXingzuo1() + " 和 " + result.getResult().getXingzuo2());
                    mContentOneTV.setText(result.getResult().getContent1());
                    mContentTwoTV.setText(result.getResult().getContent2());
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.addParam("key", Constant.API_KEY_CONSTELLATON_CONJUGATE)
                .addParam("xingzuo1", one)
                .addParam("xingzuo2", two)
                .get();
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

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            showToast(mGridViewAdapter.getItem(position).getName());
        }
    }

    private class GridViewAdapter extends ArrayAdapter<Check> {

        public GridViewAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_constellation_conjugate_check, null);
                viewHolder = new ViewHolder();
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_item_constellation_conjugate);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final Check check = getItem(position);
            viewHolder.checkBox.setText(check.getName());
            Drawable drawable = getResources().getDrawable(check.getIcon());
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            viewHolder.checkBox.setCompoundDrawables(drawable, null, null, null);
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton checkBox, boolean isChecked) {
                    mCheckMap.put(position, checkBox.isChecked());
                }
            });

            return convertView;
        }

        class ViewHolder {
            CheckBox checkBox;
        }
    }

    class Check {
        private String name;
        private int icon;

        public Check() {
        }

        public Check(String name, int icon) {
            this.name = name;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }
    }

}
