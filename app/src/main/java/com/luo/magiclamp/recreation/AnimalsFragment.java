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
import com.luo.magiclamp.entity.Animals;
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
 * AnimalsFragment
 * <p/>
 * Created by Administrator on 2016/10/17.
 */
public class AnimalsFragment extends BaseFragment implements View.OnTouchListener {
    private View mRootView;

    private ScrollGridView mGridView;
    private GridViewAdapter mGridViewAdapter;

    private TextView mStartTV;
    private TextView mTitleTV;
    private TextView mContentOneTV;
    private TextView mContentTwoTV;

    private Map<Integer, Boolean> mCheckMap = new HashMap<>();

    public AnimalsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_animals, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("生肖配对");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
        initMap();
        setAdapter();
        initData();
        loadData("鼠", "鼠");
    }

    private void findView() {
        mGridView = (ScrollGridView) mRootView.findViewById(R.id.gv_animals_check);
        mStartTV = (TextView) mRootView.findViewById(R.id.tv_animals_start);
        mTitleTV = (TextView) mRootView.findViewById(R.id.tv_animals_title);
        mContentOneTV = (TextView) mRootView.findViewById(R.id.tv_animals_content_one);
        mContentTwoTV = (TextView) mRootView.findViewById(R.id.tv_animals_content_two);

        mStartTV.setOnTouchListener(AnimalsFragment.this);
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
                    showToast("请选择1到2个生肖进行配对");
                }
            }
        });
    }

    private void initData() {
        // 鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
        List<Check> checks = new ArrayList<>();
        checks.add(new Check("鼠", R.mipmap.icon_conjugate_rat));
        checks.add(new Check("牛", R.mipmap.icon_conjugate_cow));
        checks.add(new Check("虎", R.mipmap.icon_conjugate_tiger));
        checks.add(new Check("兔", R.mipmap.icon_conjugate_rabbit));
        checks.add(new Check("龙", R.mipmap.icon_conjugate_dragon));
        checks.add(new Check("蛇", R.mipmap.icon_conjugate_snake));
        checks.add(new Check("马", R.mipmap.icon_conjugate_horse));
        checks.add(new Check("羊", R.mipmap.icon_conjugate_sheep));
        checks.add(new Check("猴", R.mipmap.icon_conjugate_monkey));
        checks.add(new Check("鸡", R.mipmap.icon_conjugate_chicken));
        checks.add(new Check("狗", R.mipmap.icon_conjugate_dog));
        checks.add(new Check("猪", R.mipmap.icon_conjugate_pig));
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
        // 鼠、牛、虎、兔、龙、蛇、马、羊、猴、鸡、狗、猪
        String name = "鼠";
        switch (pos) {
            case 0:
                name = "鼠";
                break;
            case 1:
                name = "牛";
                break;
            case 2:
                name = "虎";
                break;
            case 3:
                name = "兔";
                break;
            case 4:
                name = "龙";
                break;
            case 5:
                name = "蛇";
                break;
            case 6:
                name = "马";
                break;
            case 7:
                name = "羊";
                break;
            case 8:
                name = "猴";
                break;
            case 9:
                name = "鸡";
                break;
            case 10:
                name = "狗";
                break;
            case 11:
                name = "猪";
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

        new ApiRequest<Animals>(ApiURL.API_CHAT_ANIMALS) {

            @Override
            protected void onSuccess(Animals result) {
                if (result.getResult() != null) {
                    mTitleTV.setText(String.format("%s 和 %s", result.getResult().getShengxiao1(), result.getResult().getShengxiao2()));
                    mContentOneTV.setText(result.getResult().getContent1());
                    mContentTwoTV.setText(result.getResult().getContent2());
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.addParam("key", Constant.API_KEY_ANIMALS)
                .addParam("shengxiao1", one)
                .addParam("shengxiao2", two)
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
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_animals_check, null);
                viewHolder = new ViewHolder();
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_item_animals);
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
