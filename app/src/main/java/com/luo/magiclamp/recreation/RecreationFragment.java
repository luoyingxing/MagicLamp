package com.luo.magiclamp.recreation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Joke;
import com.luo.magiclamp.entity.JokeBody;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.ui.pullableview.PullListView;
import com.luo.magiclamp.frame.ui.pullableview.PullToRefreshLayout;
import com.luo.magiclamp.frame.ui.view.NewsDetailImageView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

/**
 * RecreationFragment
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class RecreationFragment extends BaseFragment implements View.OnTouchListener {
    private View mRootView;
    private GridView mGridView;
    private PullListView mListView;
    private PullToRefreshLayout mPullToRefreshLayout;

    private GridViewAdapter mGridViewAdapter;
    private ListViewAdapter mListViewAdapter;

    private List<Title> mTitleList = new ArrayList<>();
    private int mPage = 1;

    public RecreationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recreation, container, false);
        init();
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    private void init() {
        findView();
        setAdapter();
        loadGridViewData();
        loadTextJoke();
    }

    private void findView() {
        mGridView = (GridView) mRootView.findViewById(R.id.gv_recreation_top);
        mPullToRefreshLayout = (PullToRefreshLayout) mRootView.findViewById(R.id.pl_recreation_content);
        mListView = (PullListView) mRootView.findViewById(R.id.lv_recreation_content);

        mPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                mLog.e("onRefresh");
                mListViewAdapter.clear();
                mJokeList.clear();
                mTextJokeList.clear();
                mImgJokeList.clear();
                mPage = 1;
                loadTextJoke();

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                mLog.e("onLoadMore");
                mJokeList.clear();
                loadTextJoke();
            }
        });
    }

    private void setAdapter() {
        mGridViewAdapter = new GridViewAdapter(getActivity());
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(new ItemClickListener());

        mListViewAdapter = new ListViewAdapter(getActivity(), new ArrayList<Joke>());
        mListView.setAdapter(mListViewAdapter);
    }

    private void loadGridViewData() {
        mTitleList.clear();
        mTitleList.add(new Title("神灯聊天", R.mipmap.icon_recreation_chat));
        mTitleList.add(new Title("星座运势", R.mipmap.icon_recreation_chart));
        mTitleList.add(new Title("星座配对", R.mipmap.icon_recreation_conjugate));
        mTitleList.add(new Title("生肖配对", R.mipmap.icon_recreation_animals));
        mTitleList.add(new Title("姓氏起源", R.mipmap.icon_recreation_name));
        mGridViewAdapter.addAll(mTitleList);
    }

    private List<Joke> mJokeList = new ArrayList<>();
    private List<Joke> mTextJokeList = new ArrayList<>();
    private List<Joke> mImgJokeList = new ArrayList<>();

    private void loadTextJoke() {
        showDialog();
        new ApiRequest<JokeBody>(ApiURL.API_RECREATION_JOKE_TEXT) {
            @Override
            protected void onSuccess(JokeBody body) {
                mTextJokeList.clear();
                mLog.e("text -- " + body.getResult().size());
                mTextJokeList.addAll(body.getResult());
            }

            @Override
            protected void onFinish(int what) {
                loadImgJoke();
            }

        }.addParam("key", Constant.API_KEY_RECREATION)
                .addParam("page", mPage)
                .get();
    }

    private void loadImgJoke() {
        new ApiRequest<JokeBody>(ApiURL.API_RECREATION_JOKE_IMG, true) {
            @Override
            protected void onSuccess(JokeBody body) {
                mPage++;
                mImgJokeList.clear();
                mLog.e("Img -- " + body.getResult().size());
                mImgJokeList.addAll(body.getResult());
                showJoke();
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
                mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                mPullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }

        }.addParam("key", Constant.API_KEY_RECREATION)
                .addParam("page", mPage)
                .get();
    }

    private void showJoke() {
        int textSize = mTextJokeList.size();
        int imgSize = mImgJokeList.size();
        int temp = textSize > imgSize ? textSize : imgSize;
        for (int i = 0; i < temp; i++) {
            if (!mTextJokeList.isEmpty()) {
                mJokeList.add(mTextJokeList.get(i));
            }
            if (!mImgJokeList.isEmpty()) {
                mJokeList.add(mImgJokeList.get(i));
            }
        }

        if (textSize > imgSize) {
            for (int j = temp; j < textSize; j++) {
                mJokeList.add(mTextJokeList.get(j));
            }
        } else if (imgSize > textSize) {
            for (int j = temp; j < imgSize; j++) {
                mJokeList.add(mImgJokeList.get(j));
            }
        }

        mListViewAdapter.addAll(mJokeList);
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    switchFragment(ChatFragment.class);
                    break;
                case 1:
                    switchFragment(ConstellationChartFragment.class);
                    break;
                case 2:
                    switchFragment(ConstellationConjugateFragment.class);
                    break;
                case 3:
                    switchFragment(AnimalsFragment.class);
                    break;
                case 4:
                    switchFragment(NameOriginFragment.class);
                    break;
            }
        }
    }

    private void switchFragment(Class<?> clazz) {
        Intent intent = new Intent(mActivity, BaseActivity.class);
        intent.putExtra(Constant.ARGS_FRAGMENT_NAME, clazz.getName());
        startActivity(intent);
    }

    private class GridViewAdapter extends ArrayAdapter<Title> {

        public GridViewAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_recreation, null);
                viewHolder = new ViewHolder();
                viewHolder.imageIV = (ImageView) convertView.findViewById(R.id.iv_item_recreation_image);
                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.tv_item_recreation_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Title title = getItem(position);
            viewHolder.imageIV.setImageDrawable(getResources().getDrawable(title.getImageId()));
            viewHolder.imageIV.setOnTouchListener(RecreationFragment.this);
            viewHolder.titleTV.setText(title.getTitle());

            return convertView;
        }

        class ViewHolder {
            ImageView imageIV;
            TextView titleTV;
        }
    }


    private class ListViewAdapter extends ArrayAdapter<Joke> {

        public ListViewAdapter(Context context, List<Joke> jokeLists) {
            super(context, 0, jokeLists);
        }

        @Override
        public int getItemViewType(int position) {
            return TextUtils.isEmpty(getItem(position).getUrl()) ? 0 : 1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderText viewHolderText;
            ViewHolderImg viewHolderImg;

            int type = getItemViewType(position);
            if (convertView == null) {

                switch (type) {
                    case 0:
                        convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_recreation_joke_text, null);
                        viewHolderText = new ViewHolderText();
                        viewHolderText.titleTV = (TextView) convertView.findViewById(R.id.tv_item_joke_text_title);
                        viewHolderText.contentTV = (TextView) convertView.findViewById(R.id.tv_item_joke_text_content);
                        viewHolderText.shareTV = (TextView) convertView.findViewById(R.id.tv_item_joke_text_share);
                        convertView.setTag(viewHolderText);

                        Joke joke = getItem(position);

//                        viewHolderText.titleTV.setText(joke.getContent());
                        viewHolderText.contentTV.setText(Html.fromHtml(joke.getContent()));
                        viewHolderText.shareTV.setOnTouchListener(RecreationFragment.this);
                        viewHolderText.shareTV.setOnClickListener(new ItemListener(joke));

                        break;
                    case 1:
                        convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_recreation_joke_img, null);
                        viewHolderImg = new ViewHolderImg();
                        viewHolderImg.titleTV = (TextView) convertView.findViewById(R.id.tv_item_joke_img_title);
                        viewHolderImg.imageView = (NewsDetailImageView) convertView.findViewById(R.id.tv_item_joke_img_image);
                        viewHolderImg.shareTV = (TextView) convertView.findViewById(R.id.tv_item_joke_img_share);

                        convertView.setTag(viewHolderImg);

                        Joke jokeImg = getItem(position);

                        viewHolderImg.titleTV.setText(jokeImg.getContent());
                        viewHolderImg.imageView.setHttpUri(Uri.parse(jokeImg.getUrl()));
                        viewHolderImg.shareTV.setOnTouchListener(RecreationFragment.this);
                        viewHolderImg.shareTV.setOnClickListener(new ItemListener(jokeImg));

                        break;
                }
            } else {
                switch (type) {
                    case 0:
                        viewHolderText = (ViewHolderText) convertView.getTag();

                        Joke joke = getItem(position);

//                        viewHolderText.titleTV.setText(joke.getContent());
                        viewHolderText.contentTV.setText(Html.fromHtml(joke.getContent()));
                        viewHolderText.shareTV.setOnTouchListener(RecreationFragment.this);
                        viewHolderText.shareTV.setOnClickListener(new ItemListener(joke));

                        break;
                    case 1:
                        viewHolderImg = (ViewHolderImg) convertView.getTag();

                        Joke jokeImg = getItem(position);

                        viewHolderImg.titleTV.setText(jokeImg.getContent());
                        viewHolderImg.imageView.setHttpUri(Uri.parse(jokeImg.getUrl()));
                        viewHolderImg.shareTV.setOnTouchListener(RecreationFragment.this);
                        viewHolderImg.shareTV.setOnClickListener(new ItemListener(jokeImg));
                        break;
                }
            }

            return convertView;
        }

        class ViewHolderText {
            TextView titleTV;
            TextView contentTV;
            TextView shareTV;
        }

        class ViewHolderImg {
            TextView titleTV;
            NewsDetailImageView imageView;
            TextView shareTV;
        }

    }


    private class ItemListener implements View.OnClickListener {
        private Joke mJoke;

        public ItemListener(Joke joke) {
            this.mJoke = joke;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_item_joke_text_share:
                    share(mJoke, TEXT);
                    break;
                case R.id.tv_item_joke_img_share:
                    share(mJoke, IMG);
                    break;
            }
        }
    }

    private static final int TEXT = 1;
    private static final int IMG = 2;

    private void share(Joke joke, int type) {
        String text;
        UMImage image;

        if (type == TEXT) {
            text = joke.getContent() + " — 来自神灯APP";
            image = new UMImage(getActivity(), ApiURL.APP_WEB_ADDRESS_IMAGE);
        } else {
            text = " — 来自神灯APP娱乐笑话";
            image = new UMImage(getActivity(), joke.getUrl());
        }

        new ShareAction(getActivity()).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE, SHARE_MEDIA.MORE)
                .withTitle(joke.getContent())
                .withText(text)
                .withMedia(image)
                .withTargetUrl(ApiURL.APP_LOAD_ADDRESS)
                .open();
    }


    class Title {
        private int imageId;
        private String title;

        public Title(String title, int imageId) {
            this.title = title;
            this.imageId = imageId;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
