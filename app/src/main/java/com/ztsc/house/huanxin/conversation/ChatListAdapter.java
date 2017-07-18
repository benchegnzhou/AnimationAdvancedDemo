package com.ztsc.house.huanxin.conversation;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.DateUtils;
import com.squareup.picasso.Picasso;
import com.ztsc.house.R;
import com.ztsc.house.bean.ChatUserBean;

import org.afinal.simplecache.ACache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by xuyang on 2017/3/7.
 */

public class ChatListAdapter extends ArrayAdapter<EMConversation> {
    private List<EMConversation> conversationList;
    private List<EMConversation> copyConversationList;

    private boolean notiyfyByFilter;

    protected int primaryColor;
    protected int secondaryColor;
    protected int timeColor;
    protected int primarySize;
    protected int secondarySize;
    protected float timeSize;
    private Context mContext;

    public ChatListAdapter(Context context, int resource, List<EMConversation> objects) {
        super(context, resource, objects);
        mContext = context;
        conversationList = objects;
        copyConversationList = new ArrayList<EMConversation>();
        copyConversationList.addAll(objects);
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public EMConversation getItem(int arg0) {
        if (arg0 < conversationList.size()) {
            return conversationList.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_list_item, parent, false);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.unreadLabel = (TextView) convertView.findViewById(R.id.unread_msg_number);
            holder.message = (TextView) convertView.findViewById(R.id.message);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.msgState = convertView.findViewById(R.id.msg_state);
            holder.imageView = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(holder);
        }
        ACache mCache = ACache.get(mContext);
        // 获取与此用户/群组的会话
        EMConversation conversation = getItem(position);
        // 获取用户username或者群组groupid
        String username = conversation.conversationId();

        ChatUserBean userBean = (ChatUserBean) mCache.getAsObject(username);

        if (userBean!=null){
            holder.name.setText("与 " + userBean.getNickName() + " 的会话");

            String userPhoto = userBean.getUserPhoto();
            if (TextUtils.isEmpty(userPhoto)){
                Picasso.with(mContext).load(R.mipmap.defultuserphoto).error(R.mipmap.defultuserphoto).into(holder.imageView);
            }else {
                Picasso.with(mContext).load(userPhoto).error(R.mipmap.defultuserphoto).into(holder.imageView);
            }
        }else {
            holder.name.setText("与 " + conversation.conversationId() + " 的会话");
        }



        if (conversation.getUnreadMsgCount() > 0) {
            // 显示与此用户的消息未读数
            holder.unreadLabel.setText(String.valueOf(conversation.getUnreadMsgCount()));
            holder.unreadLabel.setVisibility(View.VISIBLE);
        } else {
            holder.unreadLabel.setVisibility(View.INVISIBLE);
        }
        if (conversation.getAllMsgCount() != 0) {
            // 把最后一条消息的内容作为item的message内容
            EMMessage lastMessage = conversation.getLastMessage();
            String s = lastMessage.getBody().toString();
            String substring = s.substring(s.indexOf('"')+1, s.lastIndexOf('"'));
            holder.message.setText(substring);
            holder.time.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
            if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
                holder.msgState.setVisibility(View.VISIBLE);
            } else {
                holder.msgState.setVisibility(View.GONE);
            }
        }




        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!notiyfyByFilter) {
            copyConversationList.clear();
            copyConversationList.addAll(conversationList);
            notiyfyByFilter = false;
        }
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setTimeColor(int timeColor) {
        this.timeColor = timeColor;
    }

    public void setPrimarySize(int primarySize) {
        this.primarySize = primarySize;
    }

    public void setSecondarySize(int secondarySize) {
        this.secondarySize = secondarySize;
    }

    public void setTimeSize(float timeSize) {
        this.timeSize = timeSize;
    }
    private static class ViewHolder {
        /** 和谁的聊天记录 */
        TextView name;
        /** 消息未读数 */
        TextView unreadLabel;
        /** 最后一条消息的内容 */
        TextView message;
        /** 最后一条消息的时间 */
        TextView time;
        /** 最后一条消息的发送状态 */
        View msgState;
        /** 整个list中每一行总布局 */
        ImageView imageView;
    }
}







