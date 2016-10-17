package com.luo.magiclamp.recreation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.ConstellationChart;
import com.luo.magiclamp.entity.ConstellationChartDetail;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.scroll.ScrollGridView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * ConstellationChartFragment
 * <p/>
 * Created by Administrator on 2016/10/16.
 */
public class ConstellationChartFragment extends BaseFragment implements View.OnTouchListener {
    private View mRootView;
    private ScrollGridView mGridView;

    private ImageView mPhotoIV;
    private TextView mNameTV;
    private TextView mTitleTV;
    private TextView mAllTV;
    private TextView mHealthTV;
    private TextView mLoveTV;
    private TextView mMoneyTV;
    private TextView mWorkTV;
    private TextView mFriendTV;
    private TextView mContentTV;
    private ProgressBar mAllBar;
    private ProgressBar mHealthBar;
    private ProgressBar mLoveBar;
    private ProgressBar mMoneyBar;
    private ProgressBar mWorkBar;

    private GridViewAdapter mGridViewAdapter;
    private List<Title> mTitleList = new ArrayList<>();

    public ConstellationChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_constellation_chart, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("星座运势");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
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

    private void init() {
        findView();
        setAdapter();
        loadGridViewData();
        loadChart("白羊座");
    }

    private void findView() {
        mGridView = (ScrollGridView) mRootView.findViewById(R.id.gv_constellation_chart_top);
        mPhotoIV = (ImageView) mRootView.findViewById(R.id.iv_constellation_chart_photo);
        mNameTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_name);
        mTitleTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_title);
        mAllTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_all);
        mHealthTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_health);
        mLoveTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_love);
        mMoneyTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_money);
        mWorkTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_work);
        mFriendTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_friend);
        mContentTV = (TextView) mRootView.findViewById(R.id.tv_constellation_chart_content);
        mAllBar = (ProgressBar) mRootView.findViewById(R.id.pb_constellation_chart_all);
        mHealthBar = (ProgressBar) mRootView.findViewById(R.id.pb_constellation_chart_health);
        mLoveBar = (ProgressBar) mRootView.findViewById(R.id.pb_constellation_chart_love);
        mMoneyBar = (ProgressBar) mRootView.findViewById(R.id.pb_constellation_chart_money);
        mWorkBar = (ProgressBar) mRootView.findViewById(R.id.pb_constellation_chart_work);
    }

    private void setAdapter() {
        mGridViewAdapter = new GridViewAdapter(getActivity());
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(new ItemClickListener());
    }

    private void loadGridViewData() {
        mTitleList.clear();
        mTitleList.add(new Title("白羊座", R.mipmap.icon_constellation_chart_aries));
        mTitleList.add(new Title("金牛座", R.mipmap.icon_constellation_chart_taurus));
        mTitleList.add(new Title("双子座", R.mipmap.icon_constellation_chart_gemini));
        mTitleList.add(new Title("巨蟹座", R.mipmap.icon_constellation_chart_cancer));
        mTitleList.add(new Title("狮子座", R.mipmap.icon_constellation_chart_leo));
        mTitleList.add(new Title("处女座", R.mipmap.icon_constellation_chart_virgo));
        mTitleList.add(new Title("天秤座", R.mipmap.icon_constellation_chart_libra));
        mTitleList.add(new Title("天蝎座", R.mipmap.icon_constellation_chart_scorpio));
        mTitleList.add(new Title("射手座", R.mipmap.icon_constellation_chart_sagittarius));
        mTitleList.add(new Title("摩羯座", R.mipmap.icon_constellation_chart_capricornus));
        mTitleList.add(new Title("水瓶座", R.mipmap.icon_constellation_chart_aquarius));
        mTitleList.add(new Title("双鱼座", R.mipmap.icon_constellation_chart_pisces));
        mGridViewAdapter.addAll(mTitleList);
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String name = "白羊座";
            switch (position) {
                case 0:
                    name = "白羊座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_aries);
                    break;
                case 1:
                    name = "金牛座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_taurus);
                    break;
                case 2:
                    name = "双子座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_gemini);
                    break;
                case 3:
                    name = "巨蟹座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_cancer);
                    break;
                case 4:
                    name = "狮子座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_leo);
                    break;
                case 5:
                    name = "处女座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_virgo);
                    break;
                case 6:
                    name = "天秤座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_libra);
                    break;
                case 7:
                    name = "天蝎座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_scorpio);
                    break;
                case 8:
                    name = "射手座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_sagittarius);
                    break;
                case 9:
                    name = "摩羯座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_capricornus);
                    break;
                case 10:
                    name = "水瓶座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_aquarius);
                    break;
                case 11:
                    name = "双鱼座";
                    mPhotoIV.setImageResource(R.mipmap.icon_constellation_chart_pisces);
                    break;
            }
            mNameTV.setText(name);
            loadChart(name);
        }
    }

    private void loadChart(String consName) {
        showDialog();
        String consNameString = null;
        try {
            consNameString = URLEncoder.encode(consName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        new ApiRequest<ConstellationChart>(ApiURL.API_CHAT_CONSTELLATION_CHART) {
            @Override
            protected void onSuccess(ConstellationChart result) {
                ConstellationChartDetail detail = result.getResult1();
                mTitleTV.setText(String.format("幸运数字  %s  幸运颜色  %s", detail.getNumber(), detail.getColor()));
                mAllTV.setText("综合指数（" + detail.getAll() + "）");
                mHealthTV.setText("健康指数（" + detail.getHealth() + "）");
                mLoveTV.setText("爱情指数（" + detail.getLove() + "）");
                mMoneyTV.setText("财运指数（" + detail.getMoney() + "）");
                mWorkTV.setText("工作指数（" + detail.getWork() + "）");
                mFriendTV.setText("最佳配对星座：" + detail.getQFriend());
                mContentTV.setText(detail.getSummary());

                mAllBar.setProgress(Integer.valueOf(detail.getAll().replace("%", "").trim()));
                mHealthBar.setProgress(Integer.valueOf(detail.getHealth().replace("%", "").trim()));
                mLoveBar.setProgress(Integer.valueOf(detail.getLove().replace("%", "").trim()));
                mMoneyBar.setProgress(Integer.valueOf(detail.getMoney().replace("%", "").trim()));
                mWorkBar.setProgress(Integer.valueOf(detail.getWork().replace("%", "").trim()));
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.addParam("key", Constant.API_KEY_CONSTELLATON_CHART)
                .addParam("consName", consNameString)
                .addParam("type", "today")
                .get();
    }

    private class GridViewAdapter extends ArrayAdapter<Title> {

        public GridViewAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_recreation, null);
                viewHolder = new ViewHolder();
                viewHolder.imageIV = (ImageView) convertView.findViewById(R.id.iv_item_recreation_image);
                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.tv_item_recreation_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Title title = getItem(position);
            viewHolder.imageIV.setImageDrawable(getResources().getDrawable(title.getImageId()));
            viewHolder.imageIV.setOnTouchListener(ConstellationChartFragment.this);
            viewHolder.titleTV.setText(title.getTitle());

            return convertView;
        }

        class ViewHolder {
            ImageView imageIV;
            TextView titleTV;
        }
    }

    class Title {
        private int imageId;
        private String title;

        public Title(String title, int imageId) {
            this.title = title;
            this.imageId = imageId;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
