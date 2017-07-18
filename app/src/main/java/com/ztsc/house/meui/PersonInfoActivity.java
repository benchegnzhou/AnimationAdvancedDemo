package com.ztsc.house.meui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ztsc.house.R;
import com.ztsc.house.bean.friendsbean.AddFriendsResponseBody;
import com.ztsc.house.eventbusbody.AddFriendsEvent;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.network.api.doParams.ZTHouseHttpClient;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonInfoActivity extends AppCompatActivity {

    @Bind(R.id.btn_add)
    Button btnAdd;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

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
            if (result.getStatusCode()==0){
                ToastUtils.showToastShort("添加好友成功");
                btnAdd.setText("已添加");
                btnAdd.setEnabled(false);
            }else {
                ToastUtils.showToastShort("添加失败请重试");

            }
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add:
                ZTHouseHttpClient.doAddFriends(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(),userId);
                break;
        }
    }
}
