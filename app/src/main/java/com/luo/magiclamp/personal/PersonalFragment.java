package com.luo.magiclamp.personal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.ui.scroll.ScrollListView;

import java.util.ArrayList;

/**
 * PersonalFragment
 * <p/>
 * Created by luoyingxing on 16/10/20.
 */
public class PersonalFragment extends BaseFragment {
    private View mRootView;
    private ScrollListView mListView;

    private ListAdapter mListAdapter;

    public PersonalFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_personal, container, false);
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
        loadItemData();
    }

    private void findView() {
        mListView = (ScrollListView) mRootView.findViewById(R.id.lv_personal_list);
    }

    private void setAdapter() {
        mListAdapter = new ListAdapter(getActivity());
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLog.e(mListAdapter.getItem(position).getTitle());
                switch (position) {
                    case 0:
                        showToast("暂无任何工具");
                        break;
                    case 1:
//                        Intent intent2 = new Intent(getActivity(), BaseActivity.class);
//                        intent2.putExtra(Constant.ARGS_FRAGMENT_NAME, CollectFragment.class.getName());
//                        startActivity(intent2);
                        break;
                    case 2:
//                        Intent intent3 = new Intent(getActivity(), BaseActivity.class);
//                        intent3.putExtra(Constant.ARGS_FRAGMENT_NAME, ChangeGradeFragment.class.getName());
//                        startActivity(intent3);
                        break;
                    case 3:
//                        Intent intent4 = new Intent(getActivity(), BaseActivity.class);
//                        intent4.putExtra(Constant.ARGS_FRAGMENT_NAME, ChangeGradeFragment.class.getName());
//                        startActivity(intent4);
                        break;
                }
            }
        });
    }

    private void loadItemData() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("我的工具", R.mipmap.icon_chat_edit));
        items.add(new Item("关于软件", R.mipmap.icon_chat_edit));
        items.add(new Item("检测更新", R.mipmap.icon_chat_edit));
        items.add(new Item("帮助", R.mipmap.icon_chat_edit));
        mListAdapter.clear();
        mListAdapter.addAll(items);
        mListAdapter.notifyDataSetChanged();
    }

    private class ListAdapter extends ArrayAdapter<Item> {

        public ListAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_personal_setting, null);
                viewHolder = new ViewHolder();
                viewHolder.mIcon = (ImageView) convertView.findViewById(R.id.iv_item_personal_icon);
                viewHolder.mTitleTV = (TextView) convertView.findViewById(R.id.tv_item_personal_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Item item = getItem(position);
            viewHolder.mIcon.setImageDrawable(getResources().getDrawable(item.getIconId()));
            viewHolder.mTitleTV.setText(item.getTitle());
            return convertView;
        }

        class ViewHolder {
            ImageView mIcon;
            TextView mTitleTV;
        }
    }

    class Item {
        private String title;
        private int iconId;

        public Item() {
        }

        public Item(String title, int iconId) {
            this.title = title;
            this.iconId = iconId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIconId() {
            return iconId;
        }

        public void setIconId(int iconId) {
            this.iconId = iconId;
        }
    }


}
