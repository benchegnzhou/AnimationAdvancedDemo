package com.ztsc.house.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.huanxin.friends.AddFriends;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by xuyang on 2017/3/23.
 */

public class MyFriendsActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_add)
    Button tvAddfriend;
    @Bind(R.id.lv_chat_list)
    ListView lvFriendList;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_my_friends;
    }

    @OnClick({R.id.iv_back, R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add:
                startActivity(new Intent(this, AddFriends.class));
                break;
        }
    }




    //环信获取好友列表
    public void getfriendslist() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    friendlist = EMClient.getInstance().contactManager().getAllContactsFromServer();
//                    Log.i("aaa", "好友列表的为：" + friendlist.toString());

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            adapter = new FriendListAdapter();
//                            lvChatList.setAdapter(adapter);
//                        }
//                    });
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
            }
//            }
        }).start();
    }




}
