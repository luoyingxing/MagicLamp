package com.luo.magiclamp.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import java.util.Random;

/**
 * ColorUtils
 * <p/>
 * Created by luoyingxing on 16/10/9.
 */
public class ColorUtils {
    public static View getViewByPosition(ListView listView, int position) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
        if (position < firstListItemPosition || position > lastListItemPosition) {
            return listView.getAdapter().getView(position, null, listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    /**
     * 返回随机不重复的5种颜色
     */
    public static int[] getRandomColors() {
        int[] colors = {android.R.color.holo_orange_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_purple,
        };
        Random rand = new Random();
        int count = colors.length;
        for (int i = 0; i < colors.length; i++) {
            int index = rand.nextInt(count--);
            if (index == count) continue;
            colors[index] = colors[index] ^ colors[count];
            colors[count] = colors[count] ^ colors[index];
            colors[index] = colors[index] ^ colors[count];
        }
        return colors;
    }

    public static int getRandomHexColor() {
        Integer color = new Random().nextInt();
        String colorStr = String.format("#%08x", color).toUpperCase();
        return Color.parseColor(colorStr);
    }

}
