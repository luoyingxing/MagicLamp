package com.luo.magiclamp.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.luo.magiclamp.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p/>
 * 1) 处理推送通知
 * 2) 处理推送消息
 */
public class InfoReceiver extends BroadcastReceiver {
    private static final String TAG = "InfoReceiver";
    private static final String PUSH_ALERT = "cn.jpush.android.ALERT";
    private static final String PUSH_MESSAGE = "cn.jpush.android.MESSAGE";
    public static final String PUSH_NUMBER = "push_number";
    public static final String PUSH_NUMBER_COLLEGE = "college_number";
    public static final String PUSH_NUMBER_COURSE = "course_number";
    public static final String NOTICE_TYPE = "noticeType";
    private static final int CODE_ALERT = 201;
    private static final int CODE_MESSAGE = 202;

    private String mNoticeType;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        mLog("getAction -> " + intent.getAction() + ", extras: [" + printBundle(bundle) + "]");

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            mLog("接收Registration Id : " + regId);

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            mLog("收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//            sendMessageToMainActivity(context, bundle, CODE_ALERT);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            mLog("接收到推送下来的通知的ID: " + notificationId);
//            sendMessageToMainActivity(context, bundle, CODE_MESSAGE);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            mLog("用户点击打开了通知");

            //打开自定义的Activity
            Intent i = new Intent(context, MainActivity.class);
            i.putExtras(bundle);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            mLog("用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            mLog(intent.getAction() + " connected state change to " + connected);
        } else {
            mLog("Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:").append(key).append(", value:").append(bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    mLog("This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey --> ").append(key).append(", value: [").append(myKey).append(" - ").append(json.optString(myKey)).append("]");
                        if (myKey.equalsIgnoreCase(InfoReceiver.NOTICE_TYPE)) {
                            mNoticeType = json.optString(myKey);
                            if (mNoticeType.equalsIgnoreCase("1")) {  //学院通知
                                PushService.addNotify(PushService.NOTIFY_COLLEGE);

                            } else if (mNoticeType.equalsIgnoreCase("2")) {  //课程通知
                                PushService.addNotify(PushService.NOTIFY_COURSE);
                            }
                        }
                    }
                } catch (JSONException e) {
                    mLog("Get message extra JSON error!");
                }

            } else {
                sb.append("\n key:").append(key).append(", value:").append(bundle.getString(key));

                if (key.equalsIgnoreCase(PUSH_ALERT)) {
                    mLog("收到通知：" + bundle.getString(key));
                } else if (key.equalsIgnoreCase(PUSH_MESSAGE)) {
                    mLog("收到消息：" + bundle.getString(key));
                }
            }
        }
        return sb.toString();
    }


//    private void sendMessageToMainActivity(Context context, Bundle bundle, int code) {
//        if (MainActivity.isForeground) {
//            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            msgIntent.putExtra(MainActivity.KEY_CODE, code);
//            msgIntent.putExtra(InfoReceiver.NOTICE_TYPE, mNoticeType);
//            if (!PushUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (null != extraJson && extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException ignored) {
//
//                }
//            }
//            context.sendBroadcast(msgIntent);
//        }
//    }

    private static void mLog(String msg) {
        Log.d(TAG, msg);
    }
}
