package com.luo.magiclamp.cartoon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Cartoon;
import com.luo.magiclamp.entity.CartoonPack;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.pullableview.PullListView;
import com.luo.magiclamp.frame.ui.pullableview.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * CartoonFragment
 * <p>
 * Created by Administrator on 2017/2/26.
 */

public class CartoonFragment extends BaseFragment {
    private View mRootView;
    private PullListView mListView;
    private PullToRefreshLayout mPullToRefreshLayout;
    private ListViewAdapter mListViewAdapter;

    private int mPage = 1;

    private int[] mLayoutBg = new int[]{R.drawable.bg_blue_color, R.drawable.bg_green_color,
            R.drawable.bg_purple_color, R.drawable.bg_yellow_color, R.drawable.bg_orange_color};

    private int[] mTitleBg = new int[]{R.color.bg_blue_color, R.color.bg_green_color,
            R.color.bg_purple_color, R.color.bg_yellow_color, R.color.bg_orange_color};

    public CartoonFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_cartoon, container, false);
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
        loadCartoonData();
    }

    private void findView() {
        mPullToRefreshLayout = (PullToRefreshLayout) mRootView.findViewById(R.id.pl_cartoon_content);
        mListView = (PullListView) mRootView.findViewById(R.id.lv_cartoon_content);

        mPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mLog.e("onRefresh");
                mListViewAdapter.clear();
                mPage = 1;
                loadCartoonData();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                mLog.e("onLoadMore");
                loadCartoonData();
            }
        });
    }

    private void setAdapter() {
        mListViewAdapter = new ListViewAdapter(mActivity, new ArrayList<Cartoon>());
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, BaseActivity.class);
                intent.putExtra(CartoonDetailsFragment.PARAM, mListViewAdapter.getItem(position));
                intent.putExtra(Constant.ARGS_FRAGMENT_NAME, CartoonDetailsFragment.class.getName());
                startActivity(intent);
            }
        });
    }

    private void loadCartoonData() {
        showDialog();
        new ApiRequest<CartoonPack>(ApiURL.API_CARTOON_LIST) {
            @Override
            protected void onSuccess(CartoonPack result) {
                if (result != null) {
                    mPage++;
                    mListViewAdapter.addAll(result.getShowapi_res_body().getPagebean().getContentlist());
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
                mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                mPullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

        }.addParam("showapi_appid", Constant.API_KEY_SHOW_ID)
                .addParam("showapi_sign", Constant.API_KEY_SHOW)
                .addParam("page", mPage)
                .get();
    }


    private class ListViewAdapter extends ArrayAdapter<Cartoon> {

        public ListViewAdapter(Context context, List<Cartoon> cartoonList) {
            super(context, 0, cartoonList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_cartoon_list, null);
                viewHolder = new ViewHolder();
                viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.ll_item_cartoon_list_root);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_cartoon_list_image);
                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.tv_item_cartoon_list_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Cartoon cartoon = getItem(position);

//            viewHolder.linearLayout.setBackgroundResource(mLayoutBg[position % 5]);
            viewHolder.titleTV.setText(cartoon.getTitle());

            return convertView;
        }


        class ViewHolder {
            LinearLayout linearLayout;
            ImageView imageView;
            TextView titleTV;
        }
    }
}
