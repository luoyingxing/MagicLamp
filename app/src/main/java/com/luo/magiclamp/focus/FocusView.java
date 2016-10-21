package com.luo.magiclamp.focus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Focus;
import com.luo.magiclamp.entity.FocusDetails;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.pullableview.PullListView;
import com.luo.magiclamp.frame.ui.pullableview.PullToRefreshLayout;
import com.luo.magiclamp.frame.ui.view.NewsDetailImageView;
import com.luo.magiclamp.utils.TimeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FocusView
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class FocusView implements Serializable {
    private static final String TAG = FocusView.class.getSimpleName();
    private View mRootView;
    private Context mContext;
    private int mFocusId = 0;

    private int mPage = 1;

    private ListViewAdapter mListViewAdapter;

    private ViewHolder viewHolder;

    public FocusView() {
    }

    public FocusView(Context context, int id) {
        this.mContext = context;
        this.mFocusId = id;
        init();
    }

    public View getRootView() {
        return mRootView == null ? null : mRootView;
    }

    private void init() {
        initView();
        setAdapter();
        loadFocus();
    }

    private void initView() {
        if (mRootView == null) {
            mRootView = LayoutInflater.from(mContext).inflate(R.layout.view_focus_view, null);
            viewHolder = new ViewHolder();
            viewHolder.listView = (PullListView) mRootView.findViewById(R.id.lv_focus_details);
            viewHolder.pullToRefreshLayout = (PullToRefreshLayout) mRootView.findViewById(R.id.pl_focus_details);
            mRootView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) mRootView.getTag();
        }
    }

    private void setAdapter() {
        mListViewAdapter = new ListViewAdapter(mContext, new ArrayList<FocusDetails>());
        viewHolder.listView.setAdapter(mListViewAdapter);
        viewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, BaseActivity.class);
                intent.putExtra(Constant.ARGS_FRAGMENT_NAME, FocusDetailsFragment.class.getName());
                intent.putExtra(FocusDetailsFragment.PARAM, mListViewAdapter.getItem(position).getId());
                mContext.startActivity(intent);
            }
        });

        viewHolder.pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                Log.e(TAG, "onRefresh");
                mListViewAdapter.clear();
                mPage = 1;
                loadFocus();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                Log.e(TAG, "onLoadMore");
                loadFocus();
            }
        });
    }

    private class ListViewAdapter extends ArrayAdapter<FocusDetails> {

        public ListViewAdapter(Context context, List<FocusDetails> focusDetailsList) {
            super(context, 0, focusDetailsList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_focus_list, null);
                viewHolder = new ViewHolder();
                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.tv_item_focus_title);
                viewHolder.imageView = (NewsDetailImageView) convertView.findViewById(R.id.iv_item_focus_image);
                viewHolder.descriptionTV = (TextView) convertView.findViewById(R.id.tv_item_focus_description);
                viewHolder.sourceTV = (TextView) convertView.findViewById(R.id.tv_item_focus_source);
                viewHolder.timeTV = (TextView) convertView.findViewById(R.id.tv_item_focus_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            FocusDetails details = getItem(position);

            viewHolder.titleTV.setText(details.getTitle());

            if (details.getImg().equalsIgnoreCase("/top/default.jpg")) {
                viewHolder.imageView.setImageResource(R.mipmap.bg_image_defualt);
            } else {
                viewHolder.imageView.setHttpUri(Uri.parse(ApiURL.API_HEALTH_LIST_IMAGE + details.getImg()));
            }

            viewHolder.descriptionTV.setText(details.getDescription());
            viewHolder.sourceTV.setText(details.getFromname());
            viewHolder.timeTV.setText(TimeUtils.showTime(details.getTime()));

            return convertView;
        }

        class ViewHolder {
            TextView titleTV;
            NewsDetailImageView imageView;
            TextView descriptionTV;
            TextView sourceTV;
            TextView timeTV;
        }

    }

    private void loadFocus() {
        new ApiRequest<Focus>(ApiURL.API_FOCUS_LIST, true) {
            @Override
            protected void onSuccess(Focus result) {
                if (result.getTngou().size() == Constant.PAGE_SIZE_DEFAULT) {
                    mPage++;
                }
                mListViewAdapter.addAll(result.getTngou());
            }

            @Override
            protected void onFinish(int what) {
                viewHolder.pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                viewHolder.pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

        }.addParam("id", mFocusId)
                .addParam("page", mPage)
                .addParam("rows", Constant.PAGE_SIZE_DEFAULT)
                .get();

    }

    private class ViewHolder {
        PullListView listView;
        PullToRefreshLayout pullToRefreshLayout;
    }
}
