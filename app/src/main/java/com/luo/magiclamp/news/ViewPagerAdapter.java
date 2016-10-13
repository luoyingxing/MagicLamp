package com.luo.magiclamp.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.magiclamp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPagerAdapter
 * <p/>
 * Created by luoyingxing on 16/10/13.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private static final String TAG = ViewPagerAdapter.class.getSimpleName();
    private Context mContext;

    private int mViesCount;
    private List<String> mTitleString = new ArrayList<>();

    public ViewPagerAdapter(Context context, List<String> title) {
        this.mContext = context;
        this.mTitleString = title;
        init();
    }

    private void init() {
        mViesCount = mTitleString.size();
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

        NewsView newsView = new NewsView(mContext, position);
        views = newsView.getRootView();

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
