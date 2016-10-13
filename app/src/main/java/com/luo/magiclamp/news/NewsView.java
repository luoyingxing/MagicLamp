package com.luo.magiclamp.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.luo.magiclamp.convenient.TestFragment;
import com.luo.magiclamp.entity.News;
import com.luo.magiclamp.entity.NewsDetails;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.network.ApiMsg;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.pullableview.PullListView;
import com.luo.magiclamp.frame.ui.pullableview.PullToRefreshLayout;
import com.luo.magiclamp.frame.ui.view.NewsImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * NewsView
 * <p/>
 * Created by luoyingxing on 16/10/13.
 */
public class NewsView implements Serializable {
    private static final String TAG = NewsView.class.getSimpleName();
    private View mRootView;
    private Context mContext;
    private int mTableNum;

    private int mPage = 1;

    private ListViewAdapter mListViewAdapter;

    private ViewHolder viewHolder;

    public NewsView() {
    }

    public NewsView(Context context, int position) {
        this.mContext = context;
        this.mTableNum = position + 1;
        init();
    }

    public View getRootView() {
        return mRootView == null ? null : mRootView;
    }

    private void init() {
        initView();
        setAdapter();
        loadNews();
    }

    private void initView() {
        if (mRootView == null) {
            mRootView = LayoutInflater.from(mContext).inflate(R.layout.view_news_view, null);
            viewHolder = new ViewHolder();
            viewHolder.listView = (PullListView) mRootView.findViewById(R.id.lv_news_details);
            viewHolder.pullToRefreshLayout = (PullToRefreshLayout) mRootView.findViewById(R.id.pl_news_details);
            mRootView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) mRootView.getTag();
        }
    }


    private void setAdapter() {
        mListViewAdapter = new ListViewAdapter(mContext, new ArrayList<NewsDetails>());
        viewHolder.listView.setAdapter(mListViewAdapter);
        viewHolder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, BaseActivity.class);
                intent.putExtra(Constant.ARGS_FRAGMENT_NAME, TestFragment.class.getName());
                mContext.startActivity(intent);
            }
        });


        viewHolder.pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                Log.e(TAG, "onRefresh");
                mListViewAdapter.clear();
                mPage = 1;
                loadNews();

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                Log.e(TAG, "onLoadMore");
                loadNews();
            }
        });
    }

    private class ListViewAdapter extends ArrayAdapter<NewsDetails> {

        public ListViewAdapter(Context context, List<NewsDetails> newsLists) {
            super(context, 0, newsLists);
        }

//        @Override
//        public int getCount() {
//            return super.getCount();
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            return ITEM_VIEW_TYPE;
//        }
//
//        @Override
//        public int getViewTypeCount() {
//            return ITEM_VIEW_TYPE;
//        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news_details, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_item_news_details_title);
                viewHolder.imageView = (NewsImageView) convertView.findViewById(R.id.iv_item_news_details_img);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            NewsDetails newsDetails = getItem(position);

            viewHolder.title.setText(newsDetails.getTitle());
            viewHolder.imageView.setHttpUri(Uri.parse(newsDetails.getTopImage()));

            return convertView;
        }

        class ViewHolder {
            TextView title;
            NewsImageView imageView;
        }
    }

    private void loadNews() {
        new ApiRequest<News>(ApiURL.API_NEWS_GET_NEWS) {
            @Override
            protected void onSuccess(News result) {
                if (result.getData().size() == Constant.PAGE_SIZE_DEFAULT) {
                    mPage++;
                }

                mListViewAdapter.addAll(result.getData());
                viewHolder.pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                viewHolder.pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            protected void onFinish(int what) {
                super.onFinish(what);
            }

            @Override
            protected void onError(ApiMsg apiMsg) {
                super.onError(apiMsg);
            }

        }.addParam("tableNum", mTableNum)
                .addParam("page", mPage)
                .addParam("pagesize", Constant.PAGE_SIZE_DEFAULT)
                .get();

    }

    private class ViewHolder {
        PullListView listView;
        PullToRefreshLayout pullToRefreshLayout;
    }
}
