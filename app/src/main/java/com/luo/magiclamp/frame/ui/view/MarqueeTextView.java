package com.luo.magiclamp.frame.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug;
import android.widget.TextView;

/**
 * MarqueeTextView
 * <p/>
 * Created by luoyingxing on 16/10/09.
 */
public class MarqueeTextView extends TextView {

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context) {
        super(context);
    }

    /**
     * 欺骗Android系统，让当前没有焦点的判断为true，实现button效果
     */
    @Override
    @ViewDebug.ExportedProperty(category = "focus")
    public boolean isFocused() {
        return true;
    }
}
