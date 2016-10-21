package com.luo.magiclamp.frame.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.luo.magiclamp.R;

/**
 * LoadingDialog
 * <p/>
 * Created by Administrator on 2016/10/21.
 */
public class LoadingDialog extends Dialog {
    private Context context;
    private ImageView imageLoading;
    private Animation operatingAnim;

    public LoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_loading_dialog);
        imageLoading = (ImageView) findViewById(R.id.iv_view_loading);
        operatingAnim = AnimationUtils.loadAnimation(context, R.anim.rotating);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);

        imageLoading.startAnimation(operatingAnim);
    }

    @Override
    public void show() {
        if (imageLoading != null) {
            imageLoading.startAnimation(operatingAnim);
        }
        super.show();
    }

    @Override
    public void dismiss() {
        if (imageLoading != null) {
            imageLoading.clearAnimation();
        }
        super.dismiss();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            dismiss();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}