package com.luo.magiclamp.push;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.luo.magiclamp.MainApplication;


/**
 * PushService 存储推送的信息状态
 * <p/>
 * Created by luoyingxing on 16/8/9.
 */
public class PushService {
    private static final String TAG = "PushService";
    private static Context mContext = MainApplication.getAppContext();

    public static final String NOTIFY_COLLEGE = "push_college";
    public static final String NOTIFY_COURSE = "push_course";


    public static void addNotify(String key) {
        int number = PreferenceManager.getDefaultSharedPreferences(mContext).getInt(key, 0);
        PreferenceManager.getDefaultSharedPreferences(mContext).edit().putInt(key, ++number).commit();
        Log.d(TAG, "The number of " + key + "is " + number);
    }

    public static void addNotify(String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(mContext).edit().putInt(key, value).commit();
    }

    public static int getNotifyNumber(String key) {
        return PreferenceManager.getDefaultSharedPreferences(mContext).getInt(key, 0);
    }

    public static void clearNotify(String key) {
        PreferenceManager.getDefaultSharedPreferences(mContext).edit().putInt(key, 0).commit();
    }

    public static boolean isShowNotify() {
        return getNotifyNumber(NOTIFY_COURSE) + getNotifyNumber(NOTIFY_COLLEGE) != 0;
    }

    public static int getTotalNumber() {
        return getNotifyNumber(NOTIFY_COURSE) + getNotifyNumber(NOTIFY_COLLEGE);
    }

}
