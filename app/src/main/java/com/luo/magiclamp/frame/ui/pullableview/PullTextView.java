package com.luo.magiclamp.frame.ui.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class PullTextView extends TextView implements Pull {

    public PullTextView(Context context) {
        super(context);
    }

    public PullTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        return true;
    }

    @Override
    public boolean canPullUp() {
        return true;
    }

}
