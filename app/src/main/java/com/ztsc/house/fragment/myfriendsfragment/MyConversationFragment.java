package com.ztsc.house.fragment.myfriendsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.ztsc.house.R;
import com.ztsc.house.bean.ChatUserBean;
import com.ztsc.house.huanxin.chat.ChatActivity;
import com.ztsc.house.huanxin.conversation.ChatListAdapter;

import org.afinal.simplecache.ACache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyConversationFragment extends Fragment {
    @Bind(R.id.lv_chat_list)
    ListView lvChatList;
    private List<EMConversation> conversationList = new ArrayList<EMConversation>();
    private ChatListAdapter adapter;
    private EMMessageListener msgListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_conversation, container, false);
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        adapter = new  ChatListAdapter(getActivity(), 1, conversationList);
        lvChatList.setAdapter(adapter);
        final String st2 = getResources().getString(R.string.Cant_chat_with_yourself);
        lvChatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = adapter.getItem(position);
                String username = conversation.conversationId();
                // 进入聊天页面
                Intent intent = new Intent(getActivity(),  ChatActivity.class);
                String huanxinId = conversationList.get(position).conversationId();
                intent.putExtra("friendHuanxinId", huanxinId);
                ACache mCache = ACache.get(getActivity().getApplicationContext());
                ChatUserBean userBean = (ChatUserBean) mCache.getAsObject(username);
                if (userBean != null) {
                    intent.putExtra("friendName", userBean.getNickName());
                    intent.putExtra("userId", userBean.getUserId());
                    intent.putExtra("friendIcon",userBean.getUserPhoto());
                    startActivity(intent);
                } else {
                    intent.putExtra("friendName", "用户名丢了");
                    intent.putExtra("userId", "ID丢了");
                    intent.putExtra("friendIcon","");
                    startActivity(intent);
                }
            }
        });
        msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(final List<EMMessage> messages) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        conversationList.clear();
                        conversationList.addAll(loadConversationList());
                        for (int i = 0; i < messages.size(); i++) {
                            EMMessage emMessage = messages.get(i);
                            ACache mCache = ACache.get(getActivity().getApplicationContext());
                            ChatUserBean chatUserBean = new ChatUserBean();
                            chatUserBean.setHuanxinId(emMessage.getFrom());
                            chatUserBean.setNickName(emMessage.getStringAttribute("UserNickName", ""));
                            chatUserBean.setUserPhoto(emMessage.getStringAttribute("UserPhoto", ""));
                            chatUserBean.setUserId(emMessage.getStringAttribute("UserId", ""));
                            mCache.put(emMessage.getFrom(), chatUserBean);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };

    }

    /**
     * 获取会话列表
     */
    protected List<EMConversation> loadConversationList() {
        // 获取所有会话，包括陌生人
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        // 过滤掉messages size为0的conversation
        /**
         * 如果在排序过程中有新消息收到，lastMsgTime会发生变化 影响排序过程，Collection.sort会产生异常
         * 保证Conversation在Sort过程中最后一条消息的时间不变 避免并发问题
         */
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }

    /**
     * 根据最后一条消息的时间排序
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first == con2.first) {
                    return 0;
                } else if (con2.first > con1.first) {
                    return 1;
                } else {
                    return -1;
                }
            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        Log.i("msggggg", "onResume:");
        conversationList.clear();
        conversationList.addAll(loadConversationList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
