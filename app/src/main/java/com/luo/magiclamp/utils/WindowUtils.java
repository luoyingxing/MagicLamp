package com.luo.magiclamp.utils;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.luo.magiclamp.R;


/**
 * WindowUtils
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class WindowUtils {

    public static void statusBar(AppCompatActivity activity) {
        statusBar(activity, R.color.theme_color);
    }

    public static void statusBar(AppCompatActivity activity, int color) {
        // only for sdk >= 19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getSupportActionBar().setElevation(0);

            ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
            int statusBarHeight = getStatusBarHeight(activity.getApplicationContext());

            View mTopView = mContentView != null ? mContentView.getChildAt(0) : null;
            if (mTopView != null && mTopView.getLayoutParams() != null && mTopView.getLayoutParams().height == statusBarHeight) {
                mTopView.setBackgroundColor(activity.getApplicationContext().getResources().getColor(color));
                return;
            }
            if (mTopView != null) {
                ViewCompat.setFitsSystemWindows(mTopView, true);
            }

            mTopView = new View(activity.getApplicationContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            mTopView.setBackgroundColor(activity.getApplicationContext().getResources().getColor(color));
            if (mContentView != null) {
                mContentView.addView(mTopView, 0, lp);
            }
        }

    }

    public static int getStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
