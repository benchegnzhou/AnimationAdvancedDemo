package com.ztsc.house.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by xuyang on 2017/4/12.
 */

public class NoScrollAndShowAllGridView extends GridView {
    public NoScrollAndShowAllGridView(Context context) {
        super(context);
    }
    public NoScrollAndShowAllGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NoScrollAndShowAllGridView(Context context, AttributeSet attrs, int  defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;  //禁止GridView滑动
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO 自动生成的方法存根
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
