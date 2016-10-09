package com.luo.magiclamp.frame.ui.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class ScrollExListView extends ExpandableListView {
    public ScrollExListView(Context context) {
        super(context);
    }

    public ScrollExListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollExListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
