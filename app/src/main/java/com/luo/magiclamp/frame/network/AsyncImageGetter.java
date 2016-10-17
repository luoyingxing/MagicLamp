package com.luo.magiclamp.frame.network;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.luo.magiclamp.R;
import com.luo.magiclamp.utils.DpiUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AsyncImageGetter implements Html.ImageGetter {
    private Context mContext;
    private TextView mTextView;
    private int mImgMaxWidth;

    public AsyncImageGetter(TextView textView, int maxWidth) {
        this.mContext = textView.getContext();
        this.mTextView = textView;
        mImgMaxWidth = maxWidth;
    }

    @Override
    public Drawable getDrawable(String source) {
        String imageName = md5(source);
        String savePath;
        File filesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (filesDir != null) {
            savePath = filesDir.getAbsolutePath() + "/" + imageName;
        } else {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + mContext.getPackageName() + "/" + imageName;
        }
        String[] ss = source.split("\\.");
        if (ss.length > 1) {
            String ext = ss[ss.length - 1];
            savePath += "." + ext;
        }
        File file = new File(savePath);
        URLDrawable drawable;
        if (file.exists()) {
            drawable = new URLDrawable(BitmapDrawable.createFromPath(savePath));
        } else {
            drawable = new URLDrawable(mContext.getResources().getDrawable(R.mipmap.icon_load_failed));
            new ImageAsync(drawable).execute(savePath, source);
        }
        return drawable;
    }

    private class ImageAsync extends AsyncTask<String, Integer, Drawable> {
        private URLDrawable drawable;

        public ImageAsync(URLDrawable drawable) {
            this.drawable = drawable;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String savePath = params[0];
            String urlStr = params[1];
            InputStream in = null;
            try {
                URL url = new URL(urlStr);
                URLConnection conn = url.openConnection();
                conn.getDoInput();
                conn.connect();
                in = conn.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (in == null) return null;
            try {
                File file = new File(savePath);
                String basePath = file.getParent();
                File basePathFile = new File(basePath);
                if (!basePathFile.exists()) {
                    basePathFile.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[4 * 1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                fos.flush();
                fos.close();
                return Drawable.createFromPath(savePath);
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null && drawable != result) {
                drawable.setDrawable(result);
                mTextView.setText(mTextView.getText());
            }
        }
    }

    public class URLDrawable extends BitmapDrawable {
        private Drawable drawable;

        public URLDrawable(Drawable defaultDraw) {
            setDrawable(defaultDraw);
        }

        private void setDrawable(Drawable nDrawable) {
            drawable = nDrawable;
            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
            float dpi = metrics.scaledDensity;
            float width = drawable.getIntrinsicWidth() * dpi * dpi / 2;
            float height = drawable.getIntrinsicHeight() * dpi * dpi / 2;
            int maxWidth = mImgMaxWidth;
            if (maxWidth <= 0) {
                maxWidth = metrics.widthPixels;
            }
            if (width > mImgMaxWidth) {
                height /= (width / mImgMaxWidth);
                width = maxWidth;
            }
            drawable.setBounds(0, 0, (int) width, (int) height);
            setBounds(0, 0, (int) width, (int) height);
            if (drawable instanceof BitmapDrawable) {
                ((BitmapDrawable) drawable).setTargetDensity(metrics);
            }
        }

        @Override
        public void draw(Canvas canvas) {
            drawable.draw(canvas);
        }
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static void setHtmlText(TextView tv, String text, int dpOfSpaceWidth) {
        //根据TextView两边空白宽度及屏幕宽度设置图片的最大宽度
        int maxWidth = DpiUtils.getWidth() - DpiUtils.dipTopx(dpOfSpaceWidth);
        tv.setText(Html.fromHtml(text, new AsyncImageGetter(tv, maxWidth), null));
    }
}
