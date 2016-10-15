package com.luo.magiclamp.recreation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Chat;
import com.luo.magiclamp.entity.JokeList;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.ui.view.NewsDetailImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ChatFragment
 * <p/>
 * Created by Administrator on 2016/10/15.
 */
public class ChatFragment extends BaseFragment implements View.OnTouchListener {
    private View mRootView;
    private ListView mListView;
    private EditText mContentEd;
    private ImageView mSendIV;

    private ListViewAdapter mListViewAdapter;

    public ChatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_chat, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.getBackView().setImageResource(R.mipmap.icon_arrow_left_white);
        mActivity.getTitleView().setTextColor(getResources().getColor(R.color.white));
        mActivity.setTitle("神灯聊天");
        mActivity.getActionbarLayout().setBackgroundColor(getResources().getColor(R.color.theme_color));
    }

    private void init() {
        findView();
        setAdapter();
    }

    private int type = 0;

    private void findView() {
        mListView = (ListView) mRootView.findViewById(R.id.lv_chat_view);
        mContentEd = (EditText) mRootView.findViewById(R.id.ed_chat_content);
        mSendIV = (ImageView) mRootView.findViewById(R.id.iv_chat_send);
        mSendIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("send");
                if (type == 0){
                    type++;
                    mListViewAdapter.add(new Chat("空气为什么是蓝色的", type));
                }else {
                    type --;
                    mListViewAdapter.add(new Chat("阳光进入大气时阳光进入大气时,阳光进入大气时", type));
                }
            }
        });
    }

    private void setAdapter() {
        mListViewAdapter = new ListViewAdapter(getActivity());
        mListView.setAdapter(mListViewAdapter);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.90f, 1.0f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.90f, 1.0f);
                ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY).setDuration(200).start();
                break;
        }
        return false;
    }


    private class ListViewAdapter extends ArrayAdapter<Chat> {

        public ListViewAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public int getItemViewType(int position) {
            return getItem(position).getType() == 1 ? 1 : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderApp viewHolderApp;
            ViewHolderUser viewHolderUser;

            int type = getItemViewType(position);
            if (convertView == null) {

                switch (type) {
                    case 0: //app
                        convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_chat_list_app, null);
                        viewHolderApp = new ViewHolderApp();
                        viewHolderApp.contentTV = (TextView) convertView.findViewById(R.id.tv_item_chat_app_content);
                        convertView.setTag(viewHolderApp);

                        Chat chat = getItem(position);
                        viewHolderApp.contentTV.setText(chat.getText());

                        break;
                    case 1: //user
                        convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_chat_list_user, null);
                        viewHolderUser = new ViewHolderUser();
                        viewHolderUser.contentTV = (TextView) convertView.findViewById(R.id.tv_item_chat_user_content);
                        convertView.setTag(viewHolderUser);

                        Chat chats = getItem(position);
                        viewHolderUser.contentTV.setText(chats.getText());

                        break;
                }
            } else {
                switch (type) {
                    case 0:
                        viewHolderApp = (ViewHolderApp) convertView.getTag();

                        Chat chat = getItem(position);
                        viewHolderApp.contentTV.setText(chat.getText());

                        break;
                    case 1:
                        viewHolderUser = (ViewHolderUser) convertView.getTag();

                        Chat chats = getItem(position);
                        viewHolderUser.contentTV.setText(chats.getText());

                        break;
                }
            }


            return convertView;
        }

        class ViewHolderUser {
            TextView contentTV;
        }

        class ViewHolderApp {
            TextView contentTV;
        }
    }
}