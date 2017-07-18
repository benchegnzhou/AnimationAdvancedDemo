package com.ztsc.house.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.friendsbean.AddFriendsResponseBody;
import com.ztsc.house.bean.user.UserInformationResponseBody;
import com.ztsc.house.eventbusbody.AddFriendsEvent;
import com.ztsc.house.eventbusbody.UserInformationEvent;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.huanxin.chat.ChatActivity;
import com.ztsc.house.network.api.doParams.ZTHouseHttpClient;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.CircleImageTransformation;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserInformationActivity extends BaseActivity {

    @Bind(R.id.iv_user_photo)
    ImageView ivUserPhoto;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_gender)
    TextView tvGender;
    @Bind(R.id.btn_addfriend)
    TextView btnAddfriend;
    @Bind(R.id.btn_talk)
    TextView btnTalk;
    @Bind(R.id.iv_topic_pic1)
    ImageView ivTopicPic1;
    @Bind(R.id.iv_topic_pic2)
    ImageView ivTopicPic2;
    @Bind(R.id.iv_topic_pic3)
    ImageView ivTopicPic3;
    @Bind(R.id.iv_topic_pic4)
    ImageView ivTopicPic4;
    @Bind(R.id.tv_registdate)
    TextView tvRegistdate;

    @Bind(R.id.rl_do)
    AutoLinearLayout rlDo;
    @Bind(R.id.rl2)
    AutoRelativeLayout rl2;
    private String userId;
    private String huanxinUserName;
    private String nickName;
    private String fromWhere = "00";
    private UserInformationResponseBody.ResultBean userBean;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        btnAddfriend.setEnabled(false);
        btnTalk.setEnabled(false);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        if (!TextUtils.isEmpty(intent.getStringExtra("fromWhere"))) {
            fromWhere = intent.getStringExtra("fromWhere");
        }
        if (UserInformationManager.getInstance().getUserIsLogin()) {
            if (userId.equals(UserInformationManager.getInstance().getUserId())) {
                rlDo.setVisibility(View.GONE);
            }
        }

        ZTHouseHttpClient.doGetUserInformation(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(), userId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_user_information;
    }

    /**
     * 用户添加好友列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AddFriendsEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            AddFriendsResponseBody addFriendsResponseBody = event.parseResult();
            LogUtil.e(addFriendsResponseBody.toString());
            AddFriendsResponseBody.ResultBean result = addFriendsResponseBody.getResult();
            if (result.getStatusCode() == 0) {
                ToastUtils.showToastShort("添加好友成功");
                btnAddfriend.setText("已关注");
                btnAddfriend.setEnabled(false);
            } else {
                ToastUtils.showToastShort("添加失败请重试");
            }
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    /**
     * 根据userid查询用户信息
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserInformationEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            UserInformationResponseBody userInformationResponseBody = event.parseResult();
            LogUtil.e(userInformationResponseBody.toString());
            userBean = userInformationResponseBody.getResult();
            Picasso.with(UserInformationActivity.this).load(userBean.getImageUrl()).transform(new CircleImageTransformation()).into(ivUserPhoto);
            nickName = userBean.getNickName();
            tvUsername.setText(nickName);
            huanxinUserName = userBean.getHuanxinUserName();
//            tvAge.setText(result.get);
            List<UserInformationResponseBody.ResultBean.TopicImageUrlListBean> topicImageUrlList = userBean.getTopicImageUrlList();
            if (topicImageUrlList.size() >= 4) {
                Picasso.with(this).load(topicImageUrlList.get(0).getUrl()).error(R.drawable.banner_default).into(ivTopicPic1);
                Picasso.with(this).load(topicImageUrlList.get(1).getUrl()).error(R.drawable.banner_default).into(ivTopicPic2);
                Picasso.with(this).load(topicImageUrlList.get(2).getUrl()).error(R.drawable.banner_default).into(ivTopicPic3);
                Picasso.with(this).load(topicImageUrlList.get(3).getUrl()).error(R.drawable.banner_default).into(ivTopicPic4);
            } else if (topicImageUrlList.size() == 0) {
                ivTopicPic1.setVisibility(View.GONE);
                ivTopicPic2.setVisibility(View.GONE);
                ivTopicPic3.setVisibility(View.GONE);
                ivTopicPic4.setVisibility(View.GONE);
                rl2.setVisibility(View.GONE);
            } else if (topicImageUrlList.size() == 1) {
                Picasso.with(this).load(topicImageUrlList.get(0).getUrl()).error(R.drawable.banner_default).into(ivTopicPic1);
                ivTopicPic2.setVisibility(View.GONE);
                ivTopicPic3.setVisibility(View.GONE);
                ivTopicPic4.setVisibility(View.GONE);
            } else if (topicImageUrlList.size() == 2) {
                Picasso.with(this).load(topicImageUrlList.get(0).getUrl()).error(R.drawable.banner_default).into(ivTopicPic1);
                Picasso.with(this).load(topicImageUrlList.get(1).getUrl()).error(R.drawable.banner_default).into(ivTopicPic2);
                ivTopicPic3.setVisibility(View.GONE);
                ivTopicPic4.setVisibility(View.GONE);
            } else if (topicImageUrlList.size() == 3) {
                Picasso.with(this).load(topicImageUrlList.get(0).getUrl()).error(R.drawable.banner_default).into(ivTopicPic1);
                Picasso.with(this).load(topicImageUrlList.get(1).getUrl()).error(R.drawable.banner_default).into(ivTopicPic2);
                Picasso.with(this).load(topicImageUrlList.get(2).getUrl()).error(R.drawable.banner_default).into(ivTopicPic3);
                ivTopicPic4.setVisibility(View.GONE);
            }


            int gender = userBean.getGender();
            switch (gender) {
                case 0:
                    tvGender.setBackground(getResources().getDrawable(R.drawable.userinfor_gender_male));
                    tvGender.setText("男♂");
                    break;
                case 1:
                    tvGender.setBackground(getResources().getDrawable(R.drawable.userinfor_gender_female));
                    tvGender.setText("女♀");
                    break;
            }
            int relationship = userBean.getRelationship();

            btnTalk.setEnabled(true);
            switch (relationship) {
                case 0:   //我关注的
                    btnAddfriend.setText("已关注");
                    break;
                case 1:   //对方关注我
                    btnAddfriend.setEnabled(true);
                    btnAddfriend.setText("关注");

                    break;
                case 2:     //双向关注
                    btnAddfriend.setText("已关注");
                    break;
                case 3:    //陌生人
                    btnAddfriend.setEnabled(true);
                    btnAddfriend.setText("关注");
                    break;
                default:
                    break;
            }
            tvRegistdate.setText(userBean.getRegisterDate().trim());
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    @OnClick({R.id.iv_back, R.id.btn_addfriend, R.id.btn_talk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_addfriend:
                //添加好友
                if (UserInformationManager.getInstance().getUserIsLogin()) {
                    ZTHouseHttpClient.doAddFriends(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(), userId);
                } else {
                    ToastUtils.showToastShort("请先登录");
                }
                break;
            case R.id.btn_talk:
                // 进入聊天页面
                if (UserInformationManager.getInstance().getUserIsLogin()) {
                    switch (fromWhere) {
//                        从聊天界面点击对方头像进来的
                        case "other":
                            finish();
                            break;
//                        从其他地方进来的
                        case "00":
                            Intent intent = new Intent(UserInformationActivity.this, ChatActivity.class);
                            intent.putExtra("friendHuanxinId", huanxinUserName);
                            intent.putExtra("friendName", nickName);
                            intent.putExtra("friendIcon", userBean.getImageUrl());
                            intent.putExtra("userId", userBean.getUserId());
                            startActivity(intent);
                            UserInformationActivity.this.finish();
                            break;
                    }
                } else {
                    ToastUtils.showToastShort("请先登录");
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
