package com.luo.magiclamp.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.convenient.TestFragment;
import com.luo.magiclamp.entity.News;
import com.luo.magiclamp.entity.NewsDetails;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiMsg;
import com.luo.magiclamp.frame.network.ApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPageFragment
 * <p/>
 * create by luoyingxing on 2016/10/09
 */
public class ViewPageFragment extends Fragment {
    public static final String TABLE_NUM = "tableNum";
    public static final int ITEM_VIEW_TYPE = 2;
    private View mRootView;

    private ListView mListView;
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
        Log.e("ViewPageFragment", "onCreate");
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTableNum = arguments.getInt(TABLE_NUM);
            Log.e("ViewPageFragment", "mTableNum = " + mTableNum);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("ViewPageFragment", "onActivityCreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_news_details, container, false);
        Log.e("ViewPageFragment", "onCreateView");
        return mRootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("ViewPageFragment", "onStart");
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("ViewPageFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ViewPageFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("ViewPageFragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("ViewPageFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ViewPageFragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("ViewPageFragment", "onDetach");
    }

    private void init() {
        setAdapter();
        loadNews();
    }

    private void setAdapter() {
        mListView = (ListView) mRootView.findViewById(R.id.lv_news_details);
        mListViewAdapter = new ListViewAdapter(getContext(), new ArrayList<NewsDetails>());
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), BaseActivity.class);
//                intent.putExtra(Constant.ARGS_FRAGMENT_NAME, TestFragment.class.getName());
//                startActivity(intent);
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

    private void loadNews() {
        new ApiRequest<News>(ApiURL.API_NEWS_GET_NEWS) {
            @Override
            protected void onSuccess(News result) {
                mListViewAdapter.addAll(result.getData());
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
                .addParam("pagesize", Constant.PAGE_SIZE_DEFAULT)
                .get();

    }

}
