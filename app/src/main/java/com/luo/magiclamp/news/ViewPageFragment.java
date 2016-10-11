package com.luo.magiclamp.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.NewsDetails;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.ui.scroll.ScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPageFragment
 * <p/>
 * create by luoyingxing on 2016/10/09
 */
public class ViewPageFragment extends BaseFragment {
    public static final String TABLE_NUM = "tableNum";
    public static final int ITEM_VIEW_TYPE = 2;
    private View mRootView;

    private ScrollListView mListView;
    private ListViewAdapter mListViewAdapter;

    private int mTableNum;

    public ViewPageFragment() {
    }

    public static ViewPageFragment newInstance(int mTableNum) {
        ViewPageFragment fragment = new ViewPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TABLE_NUM, mTableNum);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTableNum = arguments.getInt(TABLE_NUM);
            mLog.e("mTableNum = " + mTableNum);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_news_details, container, false);
        init();
        return mRootView;
    }

    private void init() {
        setAdapter();
    }

    private void setAdapter() {
        mListView = (ScrollListView) mRootView.findViewById(R.id.lv_news_details);
        mListViewAdapter = new ListViewAdapter(getContext(), new ArrayList<NewsDetails>());
        mListView.setAdapter(mListViewAdapter);
    }

    private class ListViewAdapter extends ArrayAdapter<NewsDetails> {

        public ListViewAdapter(Context context, List<NewsDetails> newsLists) {
            super(context, 0, newsLists);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public int getItemViewType(int position) {
            return ITEM_VIEW_TYPE;
        }

        @Override
        public int getViewTypeCount() {
            return ITEM_VIEW_TYPE;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_news_details, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_item_news_details_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            NewsDetails newsDetails = getItem(position);

            viewHolder.title.setText(newsDetails.getTitle());

            return convertView;
        }

        class ViewHolder {
            TextView title;
        }
    }

}
