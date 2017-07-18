package com.ztsc.house.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ztsc.house.R;
import com.ztsc.house.entity.SystemMessageEntity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by benchengzhou on 2017/6/12  17:09 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： MyMessageDraggableAdapter
 * 备    注：参考链接： http://blog.csdn.net/wshiduo/article/details/54667971
 */

public class MyMessageDraggableAdapter extends RecyclerView.Adapter<MyMessageDraggableAdapter.FullDelDemoVH> {
    ArrayList<String> list = new ArrayList<>();


   /* public MyMessageDraggableAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

*/
    private Context mContext;
    private LayoutInflater mInfalter;
    private List<SystemMessageEntity> mDatas;
    private onContextClickListener onClickListener;

    public MyMessageDraggableAdapter(Context context, List<SystemMessageEntity> mDatas) {
        mContext = context;
        mInfalter = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public FullDelDemoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FullDelDemoVH(mInfalter.inflate(R.layout.item_my_message_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(final FullDelDemoVH holder, final int position) {
        ((SwipeMenuLayout) holder.itemView).setIos(false).setLeftSwipe(  true  );//这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑

//        holder.content.setText(mDatas.get(position) + (position % 2 == 0 ? "我右白虎" : "我左青龙"));

        //验证长按
        holder.content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "longclig", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onLongClick() called with: v = [" + v + "]");
                return false;
            }
        });

//        holder.btnUnRead.setVisibility(position % 3 == 0 ? View.GONE : View.VISIBLE);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(holder.getAdapterPosition());
                }
            }
        });
        //注意事项，设置item点击，不能对整个holder.itemView设置咯，只能对第一个子View，即原来的content设置，这算是局限性吧。
        (holder.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "onClick:" + mDatas.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onClick() called with: v = [" + v + "]");

                if(onClickListener!=null){
                    onClickListener.onClick(position);
                }
            }
        });
       /* //置顶：
        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnSwipeListener){
                    mOnSwipeListener.onTop(holder.getAdapterPosition());
                }

            }
        });*/
        SystemMessageEntity messageListBean = mDatas.get(position);
        holder.tvTitle.setText(messageListBean.getMsgContent());
        holder.tvCategory.setText(messageListBean.getMsgTitle());
        holder.tvCreateTime.setText(messageListBean.getCreateTime());
        holder.tvCircleImg.setVisibility(messageListBean.isRead()?View.GONE:View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);
        void onTop(int pos);
    }

    /**
     * 和Activity通信的接口
     */
    public interface onContextClickListener {
        void onClick(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    public void setOnContextClickListener(onContextClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    class FullDelDemoVH extends RecyclerView.ViewHolder {
        AutoRelativeLayout content;
        Button btnDelete;
        Button btnUnRead;
        Button btnTop;
        TextView tvCategory;
        TextView tvTitle;
        TextView tvCreateTime;
        ImageView tvCircleImg;

        public FullDelDemoVH(View itemView) {
            super(itemView);
            content = (AutoRelativeLayout) itemView.findViewById(R.id.rl_context);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvCreateTime = (TextView) itemView.findViewById(R.id.tv_createtime);
            tvCircleImg = (ImageView) itemView.findViewById(R.id.tv_circle_img);

          /*  btnUnRead = (Button) itemView.findViewById(R.id.btnUnRead);
            btnTop = (Button) itemView.findViewById(R.id.btnTop);*/
        }
    }
}
