package com.luo.magiclamp.frame.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.image.ImageInfo;
import com.luo.magiclamp.R;
import com.luo.magiclamp.utils.DpiUtils;

/**
 * NewsImageView
 * <p/>
 * Created by luoyingxing on 16/10/13.
 */
public class NewsImageView extends ImageView {
    private boolean mIsSingle = true;
    private DraweeHolder mDrawHolder;

    public NewsImageView(Context context) {
        super(context);
        init(context, null);
    }

    public NewsImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NewsImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setHttpUri(Uri uri) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(new ControllerListener<ImageInfo>() {
                    @Override
                    public void onSubmit(String id, Object callerContext) {

                    }

                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        reSize(imageInfo);
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {

                    }

                    @Override
                    public void onIntermediateImageFailed(String id, Throwable throwable) {

                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {

                    }

                    @Override
                    public void onRelease(String id) {

                    }
                })
                .setOldController(mDrawHolder.getController())
                .build();
        mDrawHolder.setController(controller);
        setImageDrawable(mDrawHolder.getTopLevelDrawable());
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NewsImageView, 0, 0);

        mIsSingle = array.getBoolean(R.styleable.NewsImageView_single, true);

        array.recycle();

        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setFadeDuration(500)
                .build();
        mDrawHolder = DraweeHolder.create(hierarchy, getContext());
    }


    private void reSize(ImageInfo info) {
        int width = DpiUtils.getWidth();
        int hight = DpiUtils.getHeight();
        int imgH = info.getHeight();
        int imgW = info.getWidth();
        int lastH;
        int lastW;
        double radio;
        radio = (width * 1.0) / imgW;
        lastH = (int) (radio * imgH);
        lastW = (int) (radio * imgW);

        ViewGroup.LayoutParams lp = this.getLayoutParams();

        if (mIsSingle) {
            lp.width = width / 5 * 2;
            lp.height = lp.width / 3 * 2;
        } else {
            lp.width = (width - DpiUtils.dipTopx(16 + 8)) / 3;
            lp.height = lp.width / 3 * 2;
        }

        this.setLayoutParams(lp);
    }


    public DraweeHolder getDrawHolder() {
        return mDrawHolder;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mDrawHolder.onDetach();
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        mDrawHolder.onDetach();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mDrawHolder.onAttach();
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        mDrawHolder.onAttach();
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        super.verifyDrawable(who);
        return who == mDrawHolder.getHierarchy().getTopLevelDrawable();
    }

}
