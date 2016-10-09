package com.luo.magiclamp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.luo.magiclamp.utils.Logger;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * MainApplication
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static final Logger mLog = new Logger(TAG, Log.VERBOSE);
    private static MainApplication mApp;
    private static RequestQueue mQueue = null;

    public static Context getAppContext() {
        return mApp;
    }

    public static MainApplication getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Fresco.initialize(mApp);

        //初始化NoHttp
        NoHttp.initialize(this);
        //初始化请求队列
        mQueue = NoHttp.newRequestQueue();

//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        MobclickAgent.setCatchUncaughtExceptions(false);
    }


}
