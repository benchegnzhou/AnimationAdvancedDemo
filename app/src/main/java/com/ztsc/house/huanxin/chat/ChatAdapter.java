package com.ztsc.house.huanxin.chat;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.squareup.picasso.Picasso;
import com.ztsc.house.R;
import com.ztsc.house.ui.UserInformationActivity;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.FileUtils;

import java.io.File;
import java.util.List;


/**
 * Created by xuyang on 2017/3/7.
 */

public class ChatAdapter extends BaseAdapter {
    private List<EMMessage> msgs;
    private Context context;
    private LayoutInflater inflater;
    private String FriendIconUrl;
    private String userId = "";

    public ChatAdapter(List<EMMessage> msgs, String FriendIconUrl, Context context_, String userId) {
        this.msgs = msgs;
        this.FriendIconUrl = FriendIconUrl;
        this.context = context_;
        this.userId = userId;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public Object getItem(int position) {
        return msgs.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        EMMessage message = (EMMessage) getItem(position);
        return message.direct() == EMMessage.Direct.RECEIVE ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EMMessage message = (EMMessage) getItem(position);
        final int viewType = getItemViewType(position);
        if (convertView == null) {
            if (viewType == 0) {
                convertView = inflater.inflate(R.layout.item_chat_receive, parent, false);
            } else {
                convertView = inflater.inflate(R.layout.item_chat_send, parent, false);
            }
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.tvMessage = (TextView) convertView.findViewById(R.id.tv_chatcontent);
            holder.ivFriendIcon = (ImageView) convertView.findViewById(R.id.iv_userhead);
            convertView.setTag(holder);
        }
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        holder.tvMessage.setText(txtBody.getMessage());
        if (viewType == 0) {
//            我收到的
            if (TextUtils.isEmpty(FriendIconUrl)){
                Picasso.with(context).load(R.mipmap.defultuserphoto).into(holder.ivFriendIcon);
            }else {
                Picasso.with(context).load(FriendIconUrl).skipMemoryCache().error(R.drawable.default_avatar).into(holder.ivFriendIcon);
            }
        } else {
//            我发送的
            String userNativeIconPath = FileUtils.getSDCardPath(context) + "/" + UserInformationManager.getInstance().getUserId() + ".png";
            if ((!TextUtils.isEmpty(userNativeIconPath)) && (new File(userNativeIconPath).exists())) {
                Log.i("myhead", "我的头像的路径为：" + userNativeIconPath);
                Picasso.with(context).
                        load(new File(userNativeIconPath)).
                        skipMemoryCache().
                        error(R.drawable.default_avatar).
                        into(holder.ivFriendIcon);
            } else {
                Picasso.with(context).load(UserInformationManager.getInstance().getHeadImage()).skipMemoryCache().error(R.drawable.default_avatar).into(holder.ivFriendIcon);
            }


        }
        holder.ivFriendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,  UserInformationActivity.class);
                switch (viewType) {
                    case 0:
                        intent.putExtra("userId",userId);
                        intent.putExtra("fromWhere","other");
                        break;
                    case 1:
                        intent.putExtra("userId", UserInformationManager.getInstance().getUserId());
                        intent.putExtra("fromWhere","me");
                        break;
                }
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    public static class ViewHolder {
        ImageView ivFriendIcon;
        TextView tvMessage;

    }
}
