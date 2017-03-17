package com.luo.magiclamp.cartoon;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.luo.magiclamp.ApiURL;
import com.luo.magiclamp.Constant;
import com.luo.magiclamp.R;
import com.luo.magiclamp.entity.Cartoon;
import com.luo.magiclamp.entity.CartoonPack;
import com.luo.magiclamp.entity.GirlImagePack;
import com.luo.magiclamp.frame.BaseActivity;
import com.luo.magiclamp.frame.BaseFragment;
import com.luo.magiclamp.frame.network.ApiRequest;
import com.luo.magiclamp.frame.recycler.ViewHolder;
import com.luo.magiclamp.frame.recycler.XAdapter;
import com.luo.magiclamp.frame.recycler.XRecyclerView;
import com.luo.magiclamp.frame.tool.FrescoBuilder;
import com.luo.magiclamp.utils.DpiUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * CartoonFragment
 * <p>
 * Created by Administrator on 2017/2/26.
 */

public class CartoonFragment extends BaseFragment {
    private View mRootView;
    private XRecyclerView mRecyclerView;
    private XAdapter mAdapter;

    private int mPage = 1;

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
        mRecyclerView = (XRecyclerView) mRootView.findViewById(R.id.xv_cartoon_content);
    }

    private void setAdapter() {
        mAdapter = new XAdapter<Cartoon>(mActivity, new ArrayList<Cartoon>(), R.layout.item_cartoon_list) {
            @Override
            public void convert(final ViewHolder holder, Cartoon cartoon) {
                holder.setText(R.id.tv_item_cartoon_list_title, cartoon.getTitle());
                SimpleDraweeView imageView = holder.getView(R.id.iv_item_cartoon_list_image);

                new FrescoBuilder(mActivity, imageView, cartoon.getImage()) {
                    @Override
                    public double reSize(int imageWidth) {
                        return ((DpiUtils.getWidth() - DpiUtils.dipTopx(14)) * 1.0) / 2 / imageWidth;
                    }
                }.builder();

            }
        };
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(4));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new XAdapter.OnItemClickListeners<Cartoon>() {
            @Override
            public void onItemClick(ViewHolder holder, Cartoon item, int position) {
                Intent intent = new Intent(mActivity, BaseActivity.class);
                intent.putExtra(CartoonDetailsFragment.PARAM, item);
                intent.putExtra(Constant.ARGS_FRAGMENT_NAME, CartoonDetailsFragment.class.getName());
                startActivity(intent);
            }
        });

        mRecyclerView.setOnLoadMoreListener(new XRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mLog.e("onLoadMore");
                loadCartoonData();
            }
        });
    }

    private void loadCartoonData() {
        showDialog();
        new ApiRequest<CartoonPack>(ApiURL.API_CARTOON_LIST) {
            @Override
            protected void onSuccess(CartoonPack result) {
                if (result != null &&
                        result.getShowapi_res_body() != null &&
                        result.getShowapi_res_body().getPagebean() != null) {
                    loadCartoonImage(result);
                }
            }

            @Override
            protected void onFinish(int what) {
                hideDialog();
            }

        }.addParam("showapi_appid", Constant.API_KEY_SHOW_ID)
                .addParam("showapi_sign", Constant.API_KEY_SHOW)
                .addParam("page", mPage)
                .get();
    }

    private void loadCartoonImage(final CartoonPack cartoonPack) {
        showDialog();
        new ApiRequest<GirlImagePack>(ApiURL.API_GIRL_IMAGE) {
            @Override
            protected void onSuccess(GirlImagePack result) {
                update(cartoonPack, result);
            }

            @Override
            protected void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                super.onFailed(what, url, tag, exception, responseCode, networkMillis);
                update(cartoonPack, null);
            }

        }.addParam("showapi_appid", Constant.API_KEY_SHOW_ID)
                .addParam("showapi_sign", Constant.API_KEY_SHOW)
                .addParam("num", cartoonPack.getShowapi_res_body().getPagebean().getContentlist().size())
                .addParam("page", mPage)
                .get();
    }

    private void update(CartoonPack cartoonPack, GirlImagePack girlImagePack) {
        mPage++;
        List<Cartoon> cartoonList = new ArrayList<>();
        List<Cartoon> list = cartoonPack.getShowapi_res_body().getPagebean().getContentlist();
        if (girlImagePack != null &&
                girlImagePack.getShowapi_res_body() != null &&
                girlImagePack.getShowapi_res_body().getNewslist() != null) {

            int count = girlImagePack.getShowapi_res_body().getNewslist().size();

            for (int i = 0; i < list.size(); i++) {
                Cartoon cartoon = list.get(i);
                if (i < count) {
                    cartoon.setImage(girlImagePack.getShowapi_res_body().getNewslist().get(i).getPicUrl());
                } else {
                    cartoon.setImage(girlImagePack.getShowapi_res_body().getNewslist().get(0).getPicUrl());
                }

                cartoonList.add(cartoon);
            }
        }
        hideDialog();
        mAdapter.addAll(cartoonList);
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        /**
         * @param space 传入的值，其单位视为dp
         */
        public SpaceItemDecoration(int space) {
            this.mSpace = DpiUtils.dipTopx(space);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int itemCount = mAdapter.getItemCount();
            int pos = parent.getChildAdapterPosition(view);

            outRect.left = 0;
            outRect.top = 0;
            outRect.bottom = 0;

            if (pos != (itemCount - 1)) {
                outRect.right = mSpace;
            } else {
                outRect.right = 0;
            }
        }
    }
}