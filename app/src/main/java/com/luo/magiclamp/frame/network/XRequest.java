package com.luo.magiclamp.frame.network;

import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * XRequest 这个类是请求神灯网页html端数据的，通过正则来解析其中的json数据，暂时只支持GET请求
 * html数据暂时以一定的字符串包含起来，以便作为正则表达式的解析。
 * <p>
 * 表达式规则目前默认为：  htmlContent luo&xx json luo&xx htmlContent
 * REGEX = "luo&xx"
 * <p>
 * 1、先得到数据流；
 * 2、正则解析数据；
 * 3、转成实体类；
 * 4、返回。
 * <p>
 * Created by luoyingxing on 2017/3/29.
 */

public class XRequest<T> {
    /**
     * 表达式规则
     */
    private static final String REGEX = "luo&xx";
    private static final String GET = "GET";
    private int mConnectTimeout = 4000;
    private int mReadTimeout = 6000;
    /**
     * 请求的次数
     */
    private int mRequestCount = 20;
    private String mUrl;

    public XRequest() {
    }

    public XRequest(String url) {
        this.mUrl = url;
    }

    public XRequest setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public XRequest setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
        return this;
    }

    public XRequest setRequestCount(int requestCount) {
        this.mRequestCount = requestCount;
        return this;
    }

    public XRequest setReadTimeout(int readTimeout) {
        this.mReadTimeout = readTimeout;
        return this;
    }

    public void send() {
        new HtmlAsyncTask().execute(mUrl);
    }

    private class HtmlAsyncTask extends AsyncTask<String, Integer, T> {

        @Override
        protected T doInBackground(String... params) {
            String json = null;
            int count = 0;
            while (count < mRequestCount) {
                count++;
                json = getStreamJson(params[0]);
                if (TextUtils.isEmpty(json)) {
                    mLog("json is null, request again in " + count + " second-rate...");
                } else {
                    break;
                }
            }

            mLog("Response ===> " + json);
            try {
                T obj = new Gson().fromJson(json, getType());
                return obj;
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                mLog("json parse error!");
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onStart();
        }

        @Override
        protected void onPostExecute(T obj) {
            super.onPostExecute(obj);
            if (obj == null) {
                onFailed();
            } else {
                onSuccess(obj);
            }
            onFinish();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

    }

    public void onStart() {
        mLog("onStart()");
    }

    public void onSuccess(T result) {
        mLog("onSuccess()");
    }

    public void onFailed() {
        mLog("onFailed()");
    }

    public void onFinish() {
        mLog("onFinish()");
    }

    /**
     * 从 html 地址取得数据流，并解析网页内容，返回json字符串
     *
     * @param urlString html地址
     * @return json 结果
     */
    private String getStreamJson(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(GET);
            conn.setConnectTimeout(mConnectTimeout);
            conn.setReadTimeout(mReadTimeout);
            InputStream inputStream = conn.getInputStream();

            byte[] bytes = new byte[1024];
            StringBuilder content = new StringBuilder();
            while (inputStream.read(bytes) != -1) {
                content.append(new String(bytes, "utf-8"));
            }
            inputStream.close();
            conn.disconnect();

            String data = String.valueOf(Html.fromHtml(content.toString()));
            if (TextUtils.isEmpty(data)) {
                mLog("the data is get from inputStream is empty");
                return null;
            }

            String[] result = data.split(REGEX);
            mLog("result length :" + result.length);

            if (result.length > 2) {
                return result[1];
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Type getType() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType genericSuperclassType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = genericSuperclassType.getActualTypeArguments();
        return actualTypeArguments[0];
    }

    private void mLog(Object object) {
        Log.v("XRequest ====> ", "" + object);
    }
}