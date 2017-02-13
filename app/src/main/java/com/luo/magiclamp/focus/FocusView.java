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
import android.widget.Toast;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Focus;
import com.luo.magiclamp.entity.FocusDetails;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.pullableview.PullListView;
import com.luo.magiclamp.frame.ui.pullableview.PullToRefreshLayout;
import com.luo.magiclamp.frame.ui.view.NewsImageView;

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
    private String mTitle;

    private ListViewAdapter mListViewAdapter;

    private ViewHolder viewHolder;

    public FocusView() {
    }

    public FocusView(Context context, String title) {
        this.mContext = context;
        this.mTitle = title;
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
                intent.putExtra(FocusDetailsFragment.PARAM, mListViewAdapter.getItem(position).getUrl());
                mContext.startActivity(intent);
            }
        });

        viewHolder.pullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mListViewAdapter.clear();
                loadFocus();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                Toast.makeText(mContext, "没有更多了", Toast.LENGTH_SHORT).show();
                viewHolder.pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        });
    }

    private class ListViewAdapter extends ArrayAdapter<FocusDetails> {

        public ListViewAdapter(Context context, List<FocusDetails> focusDetailsList) {
            super(context, 0, focusDetailsList);
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderOne viewHolderOne;
            ViewHolderTwo viewHolderTwo;

            int type = getItemViewType(position);
            if (convertView == null) {

                switch (type) {
                    case 0:
                        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_focus_details_one, null);
                        viewHolderOne = new ViewHolderOne();
                        viewHolderOne.titleTV = (TextView) convertView.findViewById(R.id.tv_item_focus_details_title);
                        viewHolderOne.imageView = (NewsImageView) convertView.findViewById(R.id.iv_item_focus_details_img);
                        viewHolderOne.sourceTV = (TextView) convertView.findViewById(R.id.tv_item_focus_details_source_one);
                        viewHolderOne.dataTV = (TextView) convertView.findViewById(R.id.tv_item_focus_details_data_one);
                        convertView.setTag(viewHolderOne);

                        FocusDetails focusOne = getItem(position);

                        viewHolderOne.titleTV.setText(focusOne.getTitle());

                        if (TextUtils.isEmpty(focusOne.getThumbnail_pic_s())) {
                            viewHolderOne.imageView.setImageResource(R.mipmap.bg_image_defualt);
                        } else {
                            viewHolderOne.imageView.setHttpUri(Uri.parse(focusOne.getThumbnail_pic_s()));
                        }

                        viewHolderOne.sourceTV.setText(focusOne.getAuthor_name());
                        viewHolderOne.dataTV.setText(focusOne.getDate());

                        break;
                    case 1:
                        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_focus_details_two, null);
                        viewHolderTwo = new ViewHolderTwo();
                        viewHolderTwo.titleTV = (TextView) convertView.findViewById(R.id.tv_item_focus_details_title_two);
                        viewHolderTwo.imageViewOne = (NewsImageView) convertView.findViewById(R.id.iv_item_focus_details_img_one);
                        viewHolderTwo.imageViewTwo = (NewsImageView) convertView.findViewById(R.id.iv_item_focus_details_img_two);
                        viewHolderTwo.imageViewThree = (NewsImageView) convertView.findViewById(R.id.iv_item_focus_details_img_three);
                        viewHolderTwo.sourceTV = (TextView) convertView.findViewById(R.id.tv_item_focus_details_source_two);
                        viewHolderTwo.dataTV = (TextView) convertView.findViewById(R.id.tv_item_focus_details_data_two);
                        convertView.setTag(viewHolderTwo);

                        FocusDetails focusTwo = getItem(position);

                        viewHolderTwo.titleTV.setText(focusTwo.getTitle());
                        viewHolderTwo.sourceTV.setText(focusTwo.getAuthor_name());
                        viewHolderTwo.dataTV.setText(focusTwo.getDate());

                        if (!TextUtils.isEmpty(focusTwo.getThumbnail_pic_s())) {
                            viewHolderTwo.imageViewOne.setHttpUri(Uri.parse(focusTwo.getThumbnail_pic_s()));
                        }

                        if (!TextUtils.isEmpty(focusTwo.getThumbnail_pic_s02())) {
                            viewHolderTwo.imageViewTwo.setHttpUri(Uri.parse(focusTwo.getThumbnail_pic_s02()));
                        }

                        if (!TextUtils.isEmpty(focusTwo.getThumbnail_pic_s03())) {
                            viewHolderTwo.imageViewThree.setHttpUri(Uri.parse(focusTwo.getThumbnail_pic_s03()));
                        }

                        break;
                }
            } else {
                switch (type) {
                    case 0:
                        viewHolderOne = (ViewHolderOne) convertView.getTag();

                        FocusDetails focusOne = getItem(position);

                        viewHolderOne.titleTV.setText(focusOne.getTitle());

                        if (!TextUtils.isEmpty(focusOne.getThumbnail_pic_s())) {
                            viewHolderOne.imageView.setHttpUri(Uri.parse(focusOne.getThumbnail_pic_s()));
                        }

                        break;
                    case 1:
                        viewHolderTwo = (ViewHolderTwo) convertView.getTag();

                        FocusDetails newsTwo = getItem(position);

                        viewHolderTwo.titleTV.setText(newsTwo.getTitle());
                        viewHolderTwo.sourceTV.setText(newsTwo.getAuthor_name());
                        viewHolderTwo.dataTV.setText(newsTwo.getDate());

                        if (!TextUtils.isEmpty(newsTwo.getThumbnail_pic_s())) {
                            viewHolderTwo.imageViewOne.setHttpUri(Uri.parse(newsTwo.getThumbnail_pic_s()));
                        }

                        if (!TextUtils.isEmpty(newsTwo.getThumbnail_pic_s02())) {
                            viewHolderTwo.imageViewTwo.setHttpUri(Uri.parse(newsTwo.getThumbnail_pic_s02()));
                        }

                        if (!TextUtils.isEmpty(newsTwo.getThumbnail_pic_s03())) {
                            viewHolderTwo.imageViewThree.setHttpUri(Uri.parse(newsTwo.getThumbnail_pic_s03()));
                        }

                        break;
                }
            }


            return convertView;
        }

        class ViewHolderOne {
            TextView titleTV;
            TextView sourceTV;
            TextView dataTV;
            NewsImageView imageView;
        }

        class ViewHolderTwo {
            TextView titleTV;
            TextView sourceTV;
            TextView dataTV;
            NewsImageView imageViewOne;
            NewsImageView imageViewTwo;
            NewsImageView imageViewThree;
        }

    }

    private void loadFocus() {
        new ApiRequest<Focus>(ApiURL.API_FOCUS_NEW) {
            @Override
            protected void onSuccess(Focus result) {
                mListViewAdapter.addAll(result.getResult().getData());
            }

            @Override
            protected void onFinish(int what) {
                viewHolder.pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                viewHolder.pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

        }.addParam("type", mTitle)
                .addParam("key", Constant.API_FOCUS_KEY)
                .get();

    }

    private class ViewHolder {
        PullListView listView;
        PullToRefreshLayout pullToRefreshLayout;
    }
}
