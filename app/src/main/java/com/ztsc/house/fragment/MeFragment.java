package com.ztsc.house.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ztsc.house.R;
import com.ztsc.house.bean.ChatUserBean;
import com.ztsc.house.meui.SetMeActivity;
import com.ztsc.house.meui.SettingActivity;
import com.ztsc.house.ui.LoginActivity;
import com.ztsc.house.ui.MyConversationActivity;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.CircleImageTransformation;
import com.ztsc.house.utils.FileUtils;
import com.ztsc.house.utils.ToastUtils;

import org.afinal.simplecache.ACache;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * 个人（我的）
 */
public class MeFragment extends Fragment implements View.OnClickListener, EMConnectionListener {

    //    设置按钮
    @Bind(R.id.ib_setting)
    RelativeLayout ibSetting;
    //    用户头像
    @Bind(R.id.about_usericon)
    ImageView aboutUsericon;
    //    用户昵称
    @Bind(R.id.me_userid)
    TextView meUserid;
    //    服务订单
    @Bind(R.id.rl_service_order)
    RelativeLayout rlServiceOrder;
    //    求助订单
    @Bind(R.id.rl_help_deal)
    RelativeLayout rlHelpDeal;
    //    我的消息
    @Bind(R.id.rl_my_message)
    RelativeLayout rlMyMessage;
    //    我的邻里圈
    @Bind(R.id.rl_my_neighborhoods)
    RelativeLayout rlMyNeighborhoods;
    //    我的发布
    @Bind(R.id.rl_server_myrelease)
    RelativeLayout rlServerMyrelease;
    //    我的房屋
    @Bind(R.id.rl_server_house)
    RelativeLayout rlServerHouse;
    //    我的小区
    @Bind(R.id.rl_server_village)
    RelativeLayout rlServerVillage;
    //    我的物业
    @Bind(R.id.rl_my_community)
    RelativeLayout rlMyCommunity;
    //    我的好友
    @Bind(R.id.rl_server_my_friends)
    LinearLayout rlServerMyFriends;
    //未登陆状态的用户信息布局
    @Bind(R.id.rl_user_isnotlogin)
    AutoRelativeLayout rlUserIsnotlogin;
    //登录状态的用户信息布局
    @Bind(R.id.rl_user_islogin)
    AutoRelativeLayout rlUserIslogin;
    //登录状态的用户头像
    @Bind(R.id.about_usericon_login)
    ImageView aboutUsericonLogin;
    //    登录状态的用户名
    @Bind(R.id.me_userid_login)
    TextView meUseridLogin;
    //    登录状态的签名
    @Bind(R.id.tv_signature_login)
    TextView tvSignatureLogin;
    //    未读提示
    @Bind(R.id.tv_new_message)
    TextView tvNewMessage;

    private EMMessageListener msgListener;

    private int messageUnreadCount = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View aboutmeview = inflater.inflate(R.layout.aboutme, container, false);
        ButterKnife.bind(this, aboutmeview);
        initListener();
        boolean userIsLogin = UserInformationManager.getInstance().getUserIsLogin();

        if (userIsLogin) {

            rlUserIslogin.setVisibility(View.VISIBLE);
            rlUserIsnotlogin.setVisibility(View.GONE);

            meUseridLogin.setText(UserInformationManager.getInstance().getNickName());
            showUserIcon();
        } else {
            rlUserIslogin.setVisibility(View.GONE);
            rlUserIsnotlogin.setVisibility(View.VISIBLE);


            meUserid.setText("登录/注册");
            Picasso.with(getActivity()).load(R.mipmap.defultuserphoto).placeholder(R.mipmap.defultuserphoto).
                    transform(new CircleImageTransformation()).
                    error(R.mipmap.defultuserphoto).into(aboutUsericon);
        }
//注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(this);
        initMessageListener();
        return aboutmeview;
    }

    private void initMessageListener() {
        msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(final List<EMMessage> messages) {
                Log.i("mmmmmm", "收到了一条消息");
                //收到消息
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("mmmmmm", "消息总条数为" + messages.size());
                        tvNewMessage.setVisibility(View.VISIBLE);
                        int r = ++messageUnreadCount;
                        tvNewMessage.setText(r > 99 ? "99" : r + "");
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
                    }
                });
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(final List<EMMessage> messages) {
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
     * 头像显示
     */
    private void showUserIcon() {


        String userNativeIconPath = FileUtils.getSDCardPath(getContext()) + "/" + UserInformationManager.getInstance().getUserId() + ".png";
        String headImage = UserInformationManager.getInstance().getHeadImage();


        if ((!TextUtils.isEmpty(userNativeIconPath)) && (new File(userNativeIconPath).exists())) {

            Picasso.with(getActivity()).
                    load(new File(userNativeIconPath)).placeholder(R.mipmap.defultuserphoto).
                    skipMemoryCache().
                    transform(new CircleImageTransformation()).
                    error(R.mipmap.defultuserphoto).into(aboutUsericonLogin);
        } else if (!TextUtils.isEmpty(headImage)) {
            Picasso.with(getActivity()).
                    load(headImage).placeholder(R.mipmap.defultuserphoto).
                    transform(new CircleImageTransformation()).
                    skipMemoryCache().
                    error(R.mipmap.defultuserphoto).
                    into(aboutUsericonLogin);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        boolean userIsLogin = UserInformationManager.getInstance().getUserIsLogin();
        if (userIsLogin) {
            rlUserIslogin.setVisibility(View.VISIBLE);
            rlUserIsnotlogin.setVisibility(View.GONE);
            showUserIcon();
        } else {
            rlUserIslogin.setVisibility(View.GONE);
            rlUserIsnotlogin.setVisibility(View.VISIBLE);
            meUserid.setText("登录/注册");
            Picasso.with(getActivity()).load(R.mipmap.defultuserphoto).
                    transform(new CircleImageTransformation()).placeholder(R.mipmap.defultuserphoto).
                    error(R.mipmap.defultuserphoto).into(aboutUsericon);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * 按钮点击事件的初始化
     */
    private void initListener() {

        ibSetting.setOnClickListener(this);
        aboutUsericon.setOnClickListener(this);
        meUserid.setOnClickListener(this);
        rlServiceOrder.setOnClickListener(this);
        rlHelpDeal.setOnClickListener(this);
        rlMyMessage.setOnClickListener(this);

        rlMyNeighborhoods.setOnClickListener(this);
        rlServerMyrelease.setOnClickListener(this);
        rlServerHouse.setOnClickListener(this);
        rlServerVillage.setOnClickListener(this);
        rlServerMyFriends.setOnClickListener(this);
        rlMyCommunity.setOnClickListener(this);
        aboutUsericonLogin.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean userIsLogin = UserInformationManager.getInstance().getUserIsLogin();
        if (userIsLogin) {
            rlUserIslogin.setVisibility(View.VISIBLE);
            rlUserIsnotlogin.setVisibility(View.GONE);


            meUseridLogin.setText(UserInformationManager.getInstance().getNickName());
            showUserIcon();
        } else {

            rlUserIslogin.setVisibility(View.GONE);
            rlUserIsnotlogin.setVisibility(View.VISIBLE);


            meUserid.setText("登录/注册");
            Picasso.with(getActivity()).load(R.mipmap.defultuserphoto).
                    transform(new CircleImageTransformation()).placeholder(R.mipmap.defultuserphoto).
                    error(R.mipmap.defultuserphoto).into(aboutUsericon);
        }

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            EMClient.getInstance().chatManager().removeMessageListener(msgListener);
        } else {
            EMClient.getInstance().chatManager().addMessageListener(msgListener);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11) {
            tvNewMessage.setVisibility(View.GONE);
            messageUnreadCount = 0;
        }


//        去登录页面
        if (requestCode == 10) {
            //登录成功
            if (resultCode == 10) {
                if (UserInformationManager.getInstance().getUserIsLogin()) {
                    EMClient.getInstance().login(UserInformationManager.getInstance().getHuanxinUserName(), UserInformationManager.getInstance().getHuanxinUserpassword(), new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            Log.i("aaabb", "登录成功");
                        }

                        @Override
                        public void onError(int i, String s) {
                            Log.i("aaabb", "用户名或密码错误");
                        }

                        @Override
                        public void onProgress(int i, String s) {
                        }
                    });

                } else {
                }
                rlUserIslogin.setVisibility(View.VISIBLE);
                rlUserIsnotlogin.setVisibility(View.GONE);

                meUseridLogin.setText(UserInformationManager.getInstance().getNickName());
                showUserIcon();
            } else if (resultCode == 20) {
                rlUserIslogin.setVisibility(View.GONE);
                rlUserIsnotlogin.setVisibility(View.VISIBLE);

                meUserid.setText(UserInformationManager.getInstance().getNickName());
                showUserIcon();
            }
        }
//        去设置页面
        if (requestCode == 20) {
//            退出登录成功
            if (resultCode == 10) {
                rlUserIslogin.setVisibility(View.GONE);
                rlUserIsnotlogin.setVisibility(View.VISIBLE);


//                UserInformationManager.getInstance().setUserInformation(false, null, null, null, 0, null, null, 0, null, null);
                Picasso.with(getActivity()).load(R.mipmap.defultuserphoto).
                        transform(new CircleImageTransformation()).placeholder(R.mipmap.defultuserphoto).
                        error(R.mipmap.defultuserphoto).into(aboutUsericon);
                meUserid.setText("登录/注册");
            } else {
                rlUserIslogin.setVisibility(View.VISIBLE);
                rlUserIsnotlogin.setVisibility(View.GONE);
            }


        }
    }

    //封装好的页面跳转方法
    protected void intent2Activity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(getActivity(), tarActivity);
        startActivity(intent);
    }


    private void SaveUserId(String id) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = getActivity().openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void toLoginActivity() {
        ToastUtils.showToastShort("请登录");
        startActivityForResult(new Intent(getActivity(), LoginActivity.class), 10);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            //用户头像的点击事件
            case R.id.about_usericon:
                toLoginActivity();
                break;
            //用户头像的点击事件
            case R.id.about_usericon_login:
                Intent intent = new Intent(getActivity(), SetMeActivity.class);
                intent.putExtra("from", "me_fragment");
                startActivityForResult(intent, 20);
                break;
            //用户昵称的点击事件
            case R.id.me_userid:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(SetMeActivity.class);
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 10);
                }
                break;
            //服务订单的点击事件
            case R.id.rl_service_order:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(ServiceOrdersActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //求助订单的点击事件
            case R.id.rl_help_deal:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(HelpOrdersActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //我的消息的点击事件
            case R.id.rl_my_message:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(MyMessageActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //我的邻里圈的点击事件
            case R.id.rl_my_neighborhoods:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(MyNeighboursActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //我的发布的点击事件
            case R.id.rl_server_myrelease:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(MeMyReleaseMenuActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //我的房屋的点击事件
            case R.id.rl_server_house:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(MyHouseActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //我的小区的点击事件
            case R.id.rl_server_village:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity( china.test.meui.MyCommunityActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //我的物业的点击事件
            case R.id.rl_my_community:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
//                    intent2Activity(MeMyCommunityMenuActivity.class);
                } else {
                    toLoginActivity();
                }
                break;
            //我的好友的点击事件
            case R.id.rl_server_my_friends:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
                    startActivityForResult(new Intent(getActivity(), MyConversationActivity.class), 11);
                } else {
                    toLoginActivity();
                }
                break;
            //设置的点击事件
            case R.id.ib_setting:
                startActivityForResult(new Intent(getActivity(), SettingActivity.class), 20);
                break;
        }
    }


    @Override
    public void onConnected() {
        Log.i("aaabb", "在线");
    }

    @Override
    public void onDisconnected(int i) {
        if (UserInformationManager.getInstance().getUserIsLogin()) {
            EMClient.getInstance().login(UserInformationManager.getInstance().getHuanxinUserName(), UserInformationManager.getInstance().getHuanxinUserpassword(), new EMCallBack() {
                @Override
                public void onSuccess() {
                    Log.i("aaabb", "离线重连登录成功");
                }

                @Override
                public void onError(int i, String s) {
                    Log.i("aaabb", "离线重登用户名或密码错误");
                }

                @Override
                public void onProgress(int i, String s) {
                    Log.i("aaabb", "正在进行重连" + s);
                }
            });
        } else {
            Log.i("aaabb", "用户没有登录");
        }
    }
}
