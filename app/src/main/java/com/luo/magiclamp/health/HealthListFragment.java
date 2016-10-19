package com.luo.magiclamp.health;

import android.content.Context;
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
import com.luo.magiclamp.entity.Health;
import com.luo.magiclamp.entity.HealthDetails;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.pullableview.PullListView;
import com.luo.magiclamp.frame.ui.pullableview.PullToRefreshLayout;
import com.luo.magiclamp.frame.ui.view.NewsDetailImageView;
import com.luo.magiclamp.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * HealthListFragment
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class HealthListFragment extends BaseFragment {
    public static final String PARAM = "id";
    public static final String TITLE = "title";
    private View mRootView;
    private ListViewAdapter mListViewAdapter;

    private PullListView mListView;
    private PullToRefreshLayout mPullToRefreshLayout;

    private String mTitle = null;
    private int mId = 0;
    private int mPage = 1;

    public HealthListFragment() {
    }

    public static HealthListFragment newInstance(int id, String title) {
        HealthListFragment fragment = new HealthListFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM, id);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(PARAM);
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_health_list, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle(mTitle);
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
        setAdapter();
        loadHealthData();
    }

    private void findView() {
        mListView = (PullListView) mRootView.findViewById(R.id.lv_health_list_list);
        mPullToRefreshLayout = (PullToRefreshLayout) mRootView.findViewById(R.id.pl_health_list_list);

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
        mListViewAdapter = new ListViewAdapter(getActivity(), new ArrayList<HealthDetails>());
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.switchFragment(HealthDetailsFragment.newInstance(mListViewAdapter.getItem(position).getId()), true);
            }
        });
    }

    private void loadHealthData() {
        showDialog();
        new ApiRequest<Health>(ApiURL.API_HEALTH_LIST, true) {
            @Override
            protected void onSuccess(Health result) {
                if (result.getHealthDetails() != null) {
                    if (result.getHealthDetails().size() < Constant.PAGE_SIZE_DEFAULT) {
                        showToast("全部已加载完");
                    } else {
                        mPage++;
                        mListViewAdapter.addAll(result.getHealthDetails());
                    }
                }

            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
                mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                mPullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

        }.addParam("page", mPage)
                .addParam("id", mId)
                .addParam("rows", Constant.PAGE_SIZE_DEFAULT)
                .get();
    }


    private class ListViewAdapter extends ArrayAdapter<HealthDetails> {

        public ListViewAdapter(Context context, List<HealthDetails> detailsList) {
            super(context, 0, detailsList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_health_list_list, null);
                viewHolder = new ViewHolder();
                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_list_title);
                viewHolder.imageView = (NewsDetailImageView) convertView.findViewById(R.id.iv_item_health_list_list_image);
                viewHolder.descriptionTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_list_description);
                viewHolder.visitTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_list_count);
                viewHolder.collectTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_list_collect);
                viewHolder.discussTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_list_discuss);
                viewHolder.timeTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_list_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            HealthDetails details = getItem(position);

            viewHolder.titleTV.setText(details.getTitle());
            viewHolder.imageView.setHttpUri(Uri.parse(ApiURL.API_HEALTH_LIST_IMAGE + details.getImg()));
            viewHolder.descriptionTV.setText(details.getDescription());
            viewHolder.visitTV.setText("" + details.getCount());
            viewHolder.collectTV.setText("" + details.getFcount());
            viewHolder.discussTV.setText("" + details.getRcount());
            viewHolder.timeTV.setText(TimeUtils.showTime(details.getTime()));

            return convertView;
        }


        class ViewHolder {
            TextView titleTV;
            NewsDetailImageView imageView;
            TextView descriptionTV;
            TextView visitTV;
            TextView collectTV;
            TextView discussTV;
            TextView timeTV;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPage = 1;
    }
}
