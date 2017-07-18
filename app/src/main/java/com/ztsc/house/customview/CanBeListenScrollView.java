package com.ztsc.house.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.ztsc.house.listener.ScrollViewListener;

/**
 * Created by xuyang on 2017/4/21.
 */

public class CanBeListenScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener = null;

    public CanBeListenScrollView(Context context) {
        super(context);
    }

    public CanBeListenScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public CanBeListenScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
