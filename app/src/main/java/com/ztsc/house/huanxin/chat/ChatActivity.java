package com.ztsc.house.huanxin.chat;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.util.NetUtils;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.ChatUserBean;
import com.ztsc.house.huanxin.utils.EaseCommonUtils;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.ToastUtils;

import org.afinal.simplecache.ACache;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class ChatActivity extends BaseActivity implements EMMessageListener {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.activity_mchat)
    RelativeLayout activityMchat;
    @Bind(R.id.srl_pulltoloadmore)
    SwipeRefreshLayout srlPulltoloadmore;
    @Bind(R.id.lv_chat)
    ListView listView;
    private int chatType = 1;
    //    对话人的姓名
    private String toChatUsername;
    private EditText et_content;
    private Button btn_send;
    private List<EMMessage> msgList = new ArrayList<>();
    private  ChatAdapter adapter;
    //标题栏上对话人的名字
    private TextView towhom;
    private EMConversation conversation;
    protected int pagesize = 20;
    private String toChatFriendname;
    private String friendIconUrl;
    private int keyHeight;
    private String friendUserId = "123123";
    private MyConnectionListener myConnectionListener;
    private ACache mCache;


    @Override
    protected void initListener() {
        EMClient.getInstance().chatManager().addMessageListener(this);
        //注册一个监听连接状态的listener
        myConnectionListener = new MyConnectionListener();
        EMClient.getInstance().addConnectionListener(myConnectionListener);
    }

    @Override
    protected void initData() {
        //初始化控件
        initView();
        //为控件添加点击事件
        onClick();
        //在线监听
        softInputListen();
        srlPulltoloadmore.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadMoreMessage();
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.activity_mchat;
    }


    private void LoadMoreMessage() {
        if (msgList.size() > 0) {
            List<EMMessage> emMessages = conversation.loadMoreMsgFromDB(msgList.get(0).getMsgId(), pagesize);
            if (emMessages.size() > 0) {
                msgList.addAll(0, emMessages);
            } else {
                ToastUtils.showToastShort("没有更多消息了");
            }
            Log.i("chatchat", "新的聊天记录" + emMessages);
            adapter.notifyDataSetChanged();
            listView.setSelection(emMessages.size());
        } else {
            ToastUtils.showToastShort("木有更多记录了");
        }
        srlPulltoloadmore.setRefreshing(false);
    }

    private void softInputListen() {

//        activityMchat.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                //获取屏幕高度
//                int screenHeight = ChatActivity.this.getWindowManager().getDefaultDisplay().getHeight();
//                //阀值设置为屏幕高度的1/3
//                keyHeight = screenHeight/3;
//
//                Log.i("inputinput", "屏幕高度有变化"+keyHeight);
//                //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
//                //old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
//                   Log.i("inputinput", "相减"+(oldBottom - bottom));
//                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
//                    Log.i("inputinput", "软键盘弹出了");
//                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
//                    Log.i("inputinput", "软键盘关闭");
//                }
//            }
//        });
//
        activityMchat.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int i = activityMchat.getRootView().getHeight() - activityMchat.getHeight();
                if (i > 100) {
                    listView.setSelection(listView.getCount() - 1);
                } else {
                }
            }
        });
        et_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setSelection(listView.getCount() - 1);
            }
        });
    }

    protected void getAllMessage() {
        // 获取当前conversation对象
        conversation = EMClient.getInstance().chatManager().getConversation(toChatUsername, EaseCommonUtils.getConversationType(chatType), true);
//         把此会话的未读数置为0
        conversation.markAllMessagesAsRead();
//         初始化db时，每个conversation加载数目是getChatOptions().getNumberOfMessagesLoaded
//         这个数目如果比用户期望进入会话界面时显示的个数不一样，就多加载一些
        final List<EMMessage> msgs = conversation.getAllMessages();
        int msgCount = msgs != null ? msgs.size() : 0;
        if (msgCount < conversation.getAllMsgCount() && msgCount < pagesize) {
            String msgId = null;
            if (msgs != null && msgs.size() > 0) {
                msgId = msgs.get(0).getMsgId();
            }
            conversation.loadMoreMsgFromDB(msgId, pagesize - msgCount);
        }
    }


    @Override
    public void onClick(View v) {

    }


    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
        }

        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (error == EMError.USER_REMOVED) {
                        // 显示帐号已经被移除
                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                    } else {
                        if (NetUtils.hasNetwork(ChatActivity.this)) {
                            //连接不到聊天服务器
                        } else {
                            //当前网络不可用，请检查网络设置
                        }
                    }
                }
            });
        }
    }

    //按钮发送消息监听
    private void onClick() {
        //点击发送
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = et_content.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    return;
                }
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
                // 增加自己特定的属性
                String headImage = UserInformationManager.getInstance().getHeadImage();
                message.setAttribute("UserPhoto", UserInformationManager.getInstance().getHeadImage());
                message.setAttribute("UserNickName", UserInformationManager.getInstance().getNickName());
                message.setAttribute("UserId", UserInformationManager.getInstance().getUserId());

                //如果是群聊，设置chattype，默认是单聊
                message.setChatType(EMMessage.ChatType.Chat);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
//                发送的消息添加到聊天页面中
                msgList.add(message);
                adapter.notifyDataSetChanged();
                if (msgList.size() > 0) {
                    listView.setSelection(listView.getCount() - 1);
                }
                et_content.setText("");
                et_content.clearFocus();
                message.setMessageStatusCallback(new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        btn_send.setEnabled(false);
                        Log.i("aaa", "发送成功");
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.i("aaa", "发送失败" + i + "," + s);
                    }

                    @Override
                    public void onProgress(int i, String s) {
                    }
                });

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conversation.markAllMessagesAsRead();
                EMClient.getInstance().removeConnectionListener(myConnectionListener);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        conversation.markAllMessagesAsRead();
        EMClient.getInstance().removeConnectionListener(myConnectionListener);
    }

    private void initView() {
        btn_send = (Button) findViewById(R.id.btn_send);
        towhom = (TextView) findViewById(R.id.tv_tousername);
        et_content = (EditText) findViewById(R.id.et_msgedit);
        btn_send.setEnabled(false);
        mCache = ACache.get(ChatActivity.this);//缓存

//        获取从上一界面传过来的值
        Intent intent = getIntent();
        toChatUsername = intent.getStringExtra("friendHuanxinId");
        toChatFriendname = intent.getStringExtra("friendName");
        friendIconUrl = intent.getStringExtra("friendIcon");
        if ("".equals(intent.getStringExtra("userId"))) {

        } else {
            friendUserId = intent.getStringExtra("userId");
        }

        ChatUserBean chatUserBean = new ChatUserBean();
        chatUserBean.setHuanxinId(toChatUsername);
        chatUserBean.setNickName(toChatFriendname);
        chatUserBean.setUserPhoto(friendIconUrl);
        chatUserBean.setUserId(friendUserId);
        String id = toChatUsername;
        mCache.put(id, chatUserBean);


        Log.i("aaabb", "对方的环信id为：" + toChatUsername);
        towhom.setText(toChatFriendname);
//        获取当前对话
        getAllMessage();
//        初始化聊天记录
        msgList.addAll(conversation.getAllMessages());
        adapter = new  ChatAdapter(msgList, friendIconUrl, ChatActivity.this, friendUserId);
        listView.setAdapter(adapter);
        listView.setSelection(listView.getCount() - 1);
        listView.setDividerHeight(0);
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_content.getText())) {
                    btn_send.setEnabled(true);
                } else {
                    btn_send.setEnabled(false);
                }
            }
        });
    }

    //收到信息
    @Override
    public void onMessageReceived(final List<EMMessage> messages) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (final EMMessage message : messages) {
                    String username = null;
                    // 群组消息
                    if (message.getChatType() == EMMessage.ChatType.GroupChat || message.getChatType() == EMMessage.ChatType.ChatRoom) {
                        username = message.getTo();
                    } else {
                        // 单聊消息
                        username = message.getFrom();
                    }
                    msgList.addAll(messages);
                    ChatUserBean chatUserBean = new ChatUserBean();
                    chatUserBean.setHuanxinId(message.getFrom());
                    chatUserBean.setNickName(message.getStringAttribute("UserNickName", ""));
                    chatUserBean.setUserPhoto(message.getStringAttribute("UserPhoto", ""));
                    chatUserBean.setUserId(message.getStringAttribute("UserId", ""));
                    String id = message.getFrom();
                    mCache.put(id, chatUserBean);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(listView.getCount() - 1);
                }
            }
        });
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
