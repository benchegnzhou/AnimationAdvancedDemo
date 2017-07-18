package china.test.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by benchengzhou on 2017/6/12  18:22 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： OnItemDragListener
 * 备    注：
 */

public interface OnItemDragListener {

        void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos);
        void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to);
        void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos);

}
