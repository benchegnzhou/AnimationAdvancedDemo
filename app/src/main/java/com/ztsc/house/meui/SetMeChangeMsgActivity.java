package com.ztsc.house.meui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.user.UserMessageChangeResponseBody;
import com.ztsc.house.common.ConstantValue;
import com.ztsc.house.eventbusbody.UserMessageChangeEvent;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.network.api.doParams.ZTHouseHttpClient;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;
import com.ztsc.house.utils.Util;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

public class SetMeChangeMsgActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.btn_more)
    TextView btnMore;
    @Bind(R.id.nicename_setme_et)
    EditText nicenameSetmeEt;
    @Bind(R.id.msg_setme_tv)
    TextView msgSetmeTv;

    private Intent intent;
    private String niceName_old;
    private int eventCode;
    private int mEventCode;
    private String telNum_old;
    private String niceName;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initListener() {
    }

    /**
     * 数据请求和数据的处理
     */
    public void initData() {
        EventBus.getDefault().register(this);
        intent = getIntent();
        initListener();
        //控件显示
        btnMore.setVisibility(View.VISIBLE);

        btnMore.setText("保存");

        //处理事件码
        eventCode = intent.getIntExtra("eventCode", -1);
        switch (eventCode) {
            case ConstantValue.erroCode:                //错误处理
                finish();
                break;
            case ConstantValue.changeNiceNameCode:    //昵称修改
                niceName_old = intent.getStringExtra("nicename");
                textTitle.setText(this.getString(R.string.changeNiceName));
                msgSetmeTv.setText("一个好的昵称可以让你的朋友更容易记住你");   //提示区显示的文本
                if (TextUtils.isEmpty(niceName_old)) {                          //判断昵称为空
                    niceName_old = "";
                }
                nicenameSetmeEt.setText(niceName_old);
                break;
            case ConstantValue.changTelCode:            //电话修改
                telNum_old = intent.getStringExtra("telNum");
                textTitle.setText(this.getString(R.string.changePhone));
                msgSetmeTv.setText("通过验证联系方式可以提高账户的安全性");   //提示区显示的文本
                if (TextUtils.isEmpty(telNum_old)) {                          //判断昵称为空
                    telNum_old = "";
                }
                nicenameSetmeEt.setText(telNum_old);
                break;
        }
        mEventCode = eventCode;                         //记录事件码
    }

    @Override
    public int getContentView() {
        return R.layout.activitychangeme_msg;
    }

    @OnClick({R.id.iv_back, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_more:

                //确定保存执行的操作
                if (mEventCode == ConstantValue.changeNiceNameCode) {
                    niceName = nicenameSetmeEt.getText().toString();
                    if (TextUtils.isEmpty(niceName)) {
                        ToastUtils.showToastShort("昵称不能为空");
                    } else if (niceName.equals(niceName_old)) {
                        ToastUtils.showToastShort("您的昵称木有改变呢");
                    } else if (!niceName.equals(niceName_old)) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("{\"nickName\":\"").append(niceName).append("\"}");
                        ZTHouseHttpClient.doUserMessageChange(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(), stringBuffer.toString());
                    } else {
                    }
                } else if (mEventCode == ConstantValue.changTelCode) {
                    checkTel();
                }
                break;
        }
    }

    /**
     * 修改昵称
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserMessageChangeEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            UserMessageChangeResponseBody userMessageChangeResponseBody = event.parseResult();
            LogUtil.e(userMessageChangeResponseBody.toString());
            UserMessageChangeResponseBody.ResultBean result = userMessageChangeResponseBody.getResult();
            int status = result.getStatus();
            if (status == 0) {
                ToastUtils.showToastShort("修改昵称成功");
                UserInformationManager.getInstance().setNickName(niceName);
                intent.putExtra("nicename_new", niceName);
                setResult(RESULT_OK, intent);                        //设置结果集
                finish();
            } else {
                ToastUtils.showToastShort("修改失败");
            }
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }


    /**
     * 电话号码检查并将对应的值返回
     */
    private void checkTel() {
        String telNum = nicenameSetmeEt.getText().toString();
        if (TextUtils.isEmpty(telNum)) {
            ToastUtils.showToastShort("联系方式不能为空");
        } else if (!telNum.equals(telNum_old)) {
            if (Util.checkPhone(telNum) || Util.checkTelePhone(telNum)) {    //判断是一个电话号码
                intent.putExtra("telNum_new", telNum);
                setResult(RESULT_OK, intent);                               //设置结果集
                finish();
            } else {
                ToastUtils.showToastShort("联系方式格式错误");
            }
        } else {
            ToastUtils.showToastShort("您的联系方式木有改变呢");
        }
    }
}
