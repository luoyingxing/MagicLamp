package com.luo.magiclamp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.luo.magiclamp.frame.network.MyNoHttp;
import com.luo.magiclamp.utils.FileUtils;
import com.luo.magiclamp.utils.Logger;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * MainApplication
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static final Logger mLog = new Logger(TAG, Log.VERBOSE);
    private static MainApplication mApp;

    private String mLoginCookie;

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
        MyNoHttp.initialize(this);
        Fresco.initialize(mApp);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        UMShareAPI.get(this);
//        MobclickAgent.setCatchUncaughtExceptions(false);
    }

    public String getLoginCookie() {
        if (mLoginCookie == null) {
            mLoginCookie = FileUtils.getPref(Constant.PREFS_LOGIN_COOKIE);
        }
        return mLoginCookie;
    }

    public void setLoginCookie(String loginCookie) {
        this.mLoginCookie = loginCookie;
        if (loginCookie == null) {
            FileUtils.removePref(Constant.PREFS_LOGIN_COOKIE);
        } else {
            FileUtils.savePref(Constant.PREFS_LOGIN_COOKIE, loginCookie);
        }
    }

    {
        PlatformConfig.setWeixin("wxa5b67d19293fc24b", "f2e945c3573a408b5c5e68a3bd9ecddf");
        PlatformConfig.setSinaWeibo("2008846005", "f773d06ddcbfbd8a9e8b0445d0840992");
        PlatformConfig.setQQZone("1105627122", "S2UTVBFwCGL4p7o3");
    }

}