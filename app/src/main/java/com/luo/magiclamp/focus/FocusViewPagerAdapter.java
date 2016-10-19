package com.luo.magiclamp.focus;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FocusViewPagerAdapter
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class FocusViewPagerAdapter extends PagerAdapter {
    private static final String TAG = FocusViewPagerAdapter.class.getSimpleName();
    private Context mContext;

    private int mViesCount;
    private List<Integer> mIdList = new ArrayList<>();

    public FocusViewPagerAdapter(Context context, List<Integer> ids) {
        this.mContext = context;
        this.mIdList = ids;
        init();
    }

    private void init() {
        mViesCount = mIdList.size();
    }

    @Override
    public int getCount() {
        return mViesCount;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View views = null;
        View mPagerView = LayoutInflater.from(mContext).inflate(R.layout.item_views_adapter, null, false);
        ViewGroup viewGroup = (ViewGroup) mPagerView.findViewById(R.id.rl_fragment_news_rootView);

        FocusView focusView = new FocusView(mContext, mIdList.get(position));
        views = focusView.getRootView();

        if (views != null) {
            if (views.getParent() != null) {
                ((ViewGroup) views.getParent()).removeView(views);
            }
            viewGroup.addView(views);
        }
        container.addView(mPagerView);
        return mPagerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
