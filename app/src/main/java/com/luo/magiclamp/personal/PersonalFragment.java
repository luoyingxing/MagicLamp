package com.luo.magiclamp.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iflytek.autoupdate.IFlytekUpdate;
import com.iflytek.autoupdate.IFlytekUpdateListener;
import com.iflytek.autoupdate.UpdateConstants;
import com.iflytek.autoupdate.UpdateErrorCode;
import com.iflytek.autoupdate.UpdateInfo;
import com.iflytek.autoupdate.UpdateType;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.ui.scroll.ScrollListView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

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

    private Handler mHandler;

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

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                showToast(msg.obj);
            }
        };
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
//                        share();
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), BaseActivity.class);
                        intent2.putExtra(Constant.ARGS_FRAGMENT_NAME, AboutFragment.class.getName());
                        startActivity(intent2);
                        break;
                    case 2:
                        updateApp();
                        break;
                    case 3:
                        Intent intent4 = new Intent(getActivity(), BaseActivity.class);
                        intent4.putExtra(Constant.ARGS_FRAGMENT_NAME, HelpFragment.class.getName());
                        startActivity(intent4);
                        break;
                }
            }
        });
    }

    private IFlytekUpdate updManager;

    private void updateApp() {
        updManager = IFlytekUpdate.getInstance(getActivity());
        updManager.setParameter(UpdateConstants.EXTRA_STYLE, UpdateConstants.UPDATE_UI_DIALOG);
        updManager.autoUpdate(getActivity(), new IFlytekUpdateListener() {
            @Override
            public void onResult(int code, UpdateInfo result) {
                if (code == UpdateErrorCode.OK && result != null) {
                    if (result.getUpdateType() == UpdateType.NoNeed) {
                        Message message = mHandler.obtainMessage();
                        message.obj = "已经是最新版本！";
                        mHandler.sendMessage(message);
                        return;
                    }
                    updManager.showUpdateInfo(getActivity(), result);
                } else {
                    Message message = mHandler.obtainMessage();
                    message.obj = "请求更新失败！";
                    mHandler.sendMessage(message);
                }
            }
        });
    }

    private void loadItemData() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("我的工具", R.mipmap.icon_personal_tools));
        items.add(new Item("关于软件", R.mipmap.icon_personal_about));
        items.add(new Item("检测更新", R.mipmap.icon_personal_update));
        items.add(new Item("帮助", R.mipmap.icon_personal_help));
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

    public static String url = "http://www.ux11148841.icoc.me/";
    public static String text = "“神灯”是一款集娱乐、健康和工具于一体的APP。";
    public static String title = "神灯APP";
    public static String imageurl = "http://11617157.s21i-11.faiusr.com/4/ABUIABAEGAAg1ISzwAUowJavmwIw0AU4gAo!160x160.png";

    private void share() {
//        UmengTool.getSignature(getActivity());
//        UmengTool.checkWx(getActivity());
        new ShareAction(getActivity()).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.MORE)
                .withTitle(title)
                .withText(text + "——来自神灯APP")
                .withMedia(new UMImage(getActivity(), imageurl))
                .withTargetUrl("http://www.ux11148841.icoc.me/")
                .setCallback(umShareListener)
                .open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(getActivity(), " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
