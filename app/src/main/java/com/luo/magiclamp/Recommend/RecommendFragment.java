package com.luo.magiclamp.Recommend;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Recommend;
import com.luo.magiclamp.entity.RecommendDetails;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.pullableview.PullListView;
import com.luo.magiclamp.frame.ui.pullableview.PullToRefreshLayout;
import com.luo.magiclamp.frame.ui.view.NewsDetailImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecommendFragment
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class RecommendFragment extends BaseFragment {
    private View mRootView;
    private ListViewAdapter mListViewAdapter;

    private PullListView mListView;
    private PullToRefreshLayout mPullToRefreshLayout;

    private int mPage = 1;

    public RecommendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recommend, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        findView();
        setAdapter();
        loadHealthData();
    }

    private void findView() {
        mListView = (PullListView) mRootView.findViewById(R.id.lv_recommend_list);
        mPullToRefreshLayout = (PullToRefreshLayout) mRootView.findViewById(R.id.pl_recommend_list);

        mPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mLog.e("onRefresh");
                mPage = 1;
                mListViewAdapter.clear();
                loadHealthData();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                mLog.e("onLoadMore");
                loadHealthData();
            }
        });
    }

    private void setAdapter() {
        mListViewAdapter = new ListViewAdapter(getActivity(), new ArrayList<RecommendDetails>());
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), BaseActivity.class);
                intent.putExtra(Constant.ARGS_FRAGMENT_NAME, RecommendDetailsFragment.class.getName());
                intent.putExtra(RecommendDetailsFragment.PARAM, mListViewAdapter.getItem(position).getUrl());
                startActivity(intent);
            }
        });
    }

    private void loadHealthData() {
        showDialog();
        new ApiRequest<Recommend>(ApiURL.API_RECOMMEND) {
            @Override
            protected void onSuccess(Recommend recommend) {
                if (recommend.getResult() != null) {
                    if (recommend.getResult().getList().size() < Constant.PAGE_SIZE_DEFAULT) {
                        showToast("全部已加载完");
                    } else {
                        mPage++;
                        mListViewAdapter.addAll(recommend.getResult().getList());
                    }
                }

            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
                mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                mPullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

        }.addParam("key", Constant.API_KEY_RECOMMEND)
                .addParam("pno", mPage)
                .addParam("ps", Constant.PAGE_SIZE_DEFAULT)
                .post();
    }


    private class ListViewAdapter extends ArrayAdapter<RecommendDetails> {

        public ListViewAdapter(Context context, List<RecommendDetails> detailsList) {
            super(context, 0, detailsList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_recommend_list, null);
                viewHolder = new ViewHolder();
                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.tv_item_recommend_title);
                viewHolder.imageView = (NewsDetailImageView) convertView.findViewById(R.id.iv_item_recommend_image);
                viewHolder.sourceTV = (TextView) convertView.findViewById(R.id.tv_item_recommend_source);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            RecommendDetails details = getItem(position);

            viewHolder.titleTV.setText(details.getTitle());
            viewHolder.imageView.setHttpUri(Uri.parse(details.getFirstImg()));
            viewHolder.sourceTV.setText(details.getSource());

            return convertView;
        }


        class ViewHolder {
            TextView titleTV;
            NewsDetailImageView imageView;
            TextView sourceTV;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPage = 1;
    }
}