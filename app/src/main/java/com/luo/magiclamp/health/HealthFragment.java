package com.luo.magiclamp.health;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Health;
import com.luo.magiclamp.entity.HealthDetails;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * HealthFragment
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class HealthFragment extends BaseFragment {
    private View mRootView;
    private ListView mListView;
    private ListViewAdapter mListViewAdapter;

    private int[] mLayoutBg = new int[]{R.drawable.bg_blue_color, R.drawable.bg_green_color,
            R.drawable.bg_purple_color, R.drawable.bg_yellow_color, R.drawable.bg_orange_color};

    private int[] mTitleBg = new int[]{R.color.bg_blue_color, R.color.bg_green_color,
            R.color.bg_purple_color, R.color.bg_yellow_color, R.color.bg_orange_color};

    public HealthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_health, container, false);
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
        mListView = (ListView) mRootView.findViewById(R.id.lv_health_list);
    }

    private void setAdapter() {
        mListViewAdapter = new ListViewAdapter(getActivity(), new ArrayList<HealthDetails>());
        mListView.setAdapter(mListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(mListViewAdapter.getItem(position).getTitle());
            }
        });
    }

    private void loadHealthData() {
        showDialog();
        new ApiRequest<Health>(ApiURL.API_HEALTH_CLASSIFY, true) {
            @Override
            protected void onSuccess(Health result) {
                if (result.getHealthDetails() != null) {
                    mListViewAdapter.addAll(result.getHealthDetails());
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.get();
    }


    private class ListViewAdapter extends ArrayAdapter<HealthDetails> {

        public ListViewAdapter(Context context, List<HealthDetails> detailsList) {
            super(context, 0, detailsList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_health_list, null);
                viewHolder = new ViewHolder();
                viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.ll_item_health_list_root);
                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_title);
                viewHolder.descriptionTV = (TextView) convertView.findViewById(R.id.tv_item_health_list_description);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            HealthDetails details = getItem(position);

            viewHolder.linearLayout.setBackgroundResource(mLayoutBg[position % 5]);
            viewHolder.titleTV.setBackgroundResource(mTitleBg[position % 5]);
            viewHolder.titleTV.setText(details.getTitle());
            viewHolder.descriptionTV.setText(details.getDescription());

            return convertView;
        }


        class ViewHolder {
            LinearLayout linearLayout;
            TextView titleTV;
            TextView descriptionTV;
        }
    }
}
