package com.ztsc.house.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.zhy.autolayout.AutoLinearLayout;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.getcode.UserActivateGetCodeResponseBody;
import com.ztsc.house.bean.getcode.UserRegistCodeCheckResponseBody;
import com.ztsc.house.bean.getcode.UserSerPasswordResponseBody;
import com.ztsc.house.callback.JsonCallback;
import com.ztsc.house.network.api.API;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.ToastUtils;
import com.ztsc.house.utils.Util;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class RegisterActivity extends BaseActivity {
    //    页面的标题
    @Bind(R.id.text_title)
    TextView textTitle;
    //    导航-手机号输入提示
    @Bind(R.id.register_text_phonenum)
    TextView registerTextPhonenum;
    //    导航-验证码输入提示
    @Bind(R.id.register_text_code)
    TextView registerTextCode;
    //    导航-密码输入提示
    @Bind(R.id.register_text_password)
    TextView registerTextPassword;
    //    手机号输入框
    @Bind(R.id.et_phonenum)
    EditText etPhonenum;
    //    验证码发送按钮
    @Bind(R.id.btn_sendcode)
    Button btnSendcode;
    //    同意协议
    @Bind(R.id.cb_isagree)
    CheckBox cbIsagree;
    //    验证码输入框
    @Bind(R.id.et_code)
    EditText etCode;
    //    验证码再次发送按钮
    @Bind(R.id.btn_resend)
    Button btnResend;
    //    验证码提交按钮
    @Bind(R.id.btn_commit)
    Button btnCommit;
    //    密码输入框
    @Bind(R.id.et_password)
    EditText etPassword;
    //    密码再次输入
    @Bind(R.id.et_password_again)
    EditText etPasswordAgain;
    //    最后一步注册按钮
    @Bind(R.id.btn_regist)
    Button btnRegist;
    //    发送验证码界面
    @Bind(R.id.rl_code_send)
    AutoLinearLayout rlCodeSend;
    //    校验验证码界面
    @Bind(R.id.rl_code_check)
    AutoLinearLayout rlCodeCheck;
    //    设置密码界面
    @Bind(R.id.rl_password_set)
    AutoLinearLayout rlPasswordSet;
    @Bind(R.id.btn_more)
    TextView btnMore;


    //    手机号
    private String phoneNum;
    //    验证码
    private String code;
    private CountDownTime mTime;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initListener() {
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etCode.getText().length() == 6) {
                    btnCommit.setEnabled(true);
                } else {
                    btnCommit.setEnabled(false);
                }
            }
        });
        etPhonenum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Util.checkPhone(etPhonenum.getText().toString())) {
                    btnSendcode.setEnabled(true);
                } else {
                    btnSendcode.setEnabled(false);
                }
            }
        });
    }

    public void initData() {

        textTitle.setText("账号激活");
        mTime = new CountDownTime(60000, 1000);//初始化对象
        btnSendcode.setEnabled(false);
        btnMore.setVisibility(View.GONE);
        btnCommit.setEnabled(false);
        changeGuideStepTextColor(1);
    }

    private void changeGuideStepTextColor(int step) {
        switch (step) {
            case 1:
                registerTextPhonenum.setTextColor(getResources().getColor(R.color.apptheme));
                registerTextCode.setTextColor(0xff4e4e4e);
                registerTextPassword.setTextColor(0xff4e4e4e);
                break;
            case 2:
                registerTextPhonenum.setTextColor(0xff4e4e4e);
                registerTextCode.setTextColor(getResources().getColor(R.color.apptheme));
                registerTextPassword.setTextColor(0xff4e4e4e);
                break;
            case 3:
                registerTextPhonenum.setTextColor(0xff4e4e4e);
                registerTextCode.setTextColor(0xff4e4e4e);
                registerTextPassword.setTextColor(getResources().getColor(R.color.apptheme));
                break;
        }
    }


    @OnClick({R.id.iv_back, R.id.btn_more, R.id.btn_sendcode, R.id.btn_resend, R.id.btn_commit, R.id.btn_regist})
    public void onClick(View view) {
        switch (view.getId()) {
//            返回
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_more:
                break;
//            点击发送验证码
            case R.id.btn_sendcode:
                phoneNum = etPhonenum.getText().toString();
                if (!cbIsagree.isChecked()) {
                    ToastUtils.showToastShort("请先阅读并同意用户协议");
                    return;
                }
                if (Util.checkPhone(phoneNum)) {
                    getRegistCode(phoneNum);
                } else {
                    ToastUtils.showToastShort("请输入正确的手机号码");
                }
                createProgressBar("请稍候...");
                break;
//            点击验证码再次发送
            case R.id.btn_resend:
                mTime.start();
                phoneNum = etPhonenum.getText().toString();
                resendRegistCode(phoneNum);
                break;
//            提交验证码进行校验
            case R.id.btn_commit:
                code = etCode.getText().toString();
                CheckCode(phoneNum, code);
                break;
            case R.id.btn_regist:
                String firstInput = etPassword.getText().toString();
                final String secondInput = etPasswordAgain.getText().toString();
                if (Util.checkPassword(firstInput)) {
                    if (firstInput.equals(secondInput)) {
                        String token = UserInformationManager.getInstance().getToken();
                        setPassword(secondInput, token);
                    } else {
                        ToastUtils.showToastShort("两次输入的密码不一致，请检查");
                    }
                } else {
                    ToastUtils.showToastShort("密码设置不规范");
                }
                break;
        }
    }

    /**
     * 设置密码
     */
    private void setPassword(String passWord, String token) {
        OkGo.<UserSerPasswordResponseBody>post(API.getUserRegistSetPasswordUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .params("phoneNum", phoneNum)//
                .params("token", token)//
                .params("passWord", passWord)//
                .execute(new JsonCallback<UserSerPasswordResponseBody>(UserSerPasswordResponseBody.class) {
                    @Override
                    public void onStart(Request<UserSerPasswordResponseBody, ? extends Request> request) {
                        super.onStart(request);
                        createProgressBar();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        disMissProgress();
                    }

                    @Override
                    public void onSuccess(Response<UserSerPasswordResponseBody> response) {
                        UserSerPasswordResponseBody.ResultBean result = response.body().getResult();
                        if ("0".equals(result.getStatus())) {
                            ToastUtils.showToastShort("密码设置成功");
//                            UserInformationManager.getInstance().setToken(result.get);
                            finish();
                        } else {
                            ToastUtils.showToastShort("密码设置失败");
                        }
                        rlCodeCheck.setVisibility(View.GONE);
                        rlPasswordSet.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Response<UserSerPasswordResponseBody> response) {
                        super.onError(response);
                        ToastUtils.showToastShort("网络出问题了");
                    }
                });
    }

    /**
     * 验证码校验
     */
    private void CheckCode(String phoneNum, String code) {
        OkGo.<UserRegistCodeCheckResponseBody>post(API.getUserRegistCheckCodeUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .params("phoneNum", phoneNum)//
                .params("code", code)//
                .execute(new JsonCallback<UserRegistCodeCheckResponseBody>(UserRegistCodeCheckResponseBody.class) {
                    @Override
                    public void onStart(Request<UserRegistCodeCheckResponseBody, ? extends Request> request) {
                        super.onStart(request);
                        createProgressBar();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        disMissProgress();
                    }

                    @Override
                    public void onSuccess(Response<UserRegistCodeCheckResponseBody> response) {
                        UserRegistCodeCheckResponseBody.ResultBean result = response.body().getResult();
                        if ("0".equals(result.getStatus())) {
                            ToastUtils.showToastShort("激活成功，请设置登录密码");
                            //写入用户信息

                            UserInformationManager.getInstance().setUserInformation(true, result.getHeadImage(),
                                    result.getPhoneNum(), result.getToken(), result.getGender(), result.getUserName(),
                                    result.getUserId(), result.getHuanxinId(), result.getHuanxin_pass_word());
                            rlCodeCheck.setVisibility(View.GONE);
                            rlPasswordSet.setVisibility(View.VISIBLE);
                            changeGuideStepTextColor(3);
                        } else {
                            ToastUtils.showToastShort("验证码错误");
                        }

                    }

                    @Override
                    public void onError(Response<UserRegistCodeCheckResponseBody> response) {
                        super.onError(response);
                        ToastUtils.showToastShort("网络出问题了");
                    }
                });
    }

    /**
     * 再次发送验证码
     */
    private void resendRegistCode(final String phoneNum) {
        OkGo.<UserActivateGetCodeResponseBody>post(API.getUserRegistGetCodeUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .params("phoneNum", phoneNum)//
                .execute(new JsonCallback<UserActivateGetCodeResponseBody>(UserActivateGetCodeResponseBody.class) {
                    @Override
                    public void onStart(Request<UserActivateGetCodeResponseBody, ? extends Request> request) {
                        super.onStart(request);
                        createProgressBar();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        disMissProgress();
                    }

                    @Override
                    public void onSuccess(Response<UserActivateGetCodeResponseBody> response) {
                        UserActivateGetCodeResponseBody.ResultBean result = response.body().getResult();
                        if ("0".equals(result.getStatus())) {
                            ToastUtils.showToastShort("再次发送成功");
                        } else {
                            ToastUtils.showToastShort("您的手机号已经激活，请登录");    //手机号注册返回登录界面并且携带手机号
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("phoneNum", phoneNum);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<UserActivateGetCodeResponseBody> response) {
                        ToastUtils.showToastShort("网络出问题了");
                        super.onError(response);
                    }


                });
    }

    /**
     * 用户注册获取验证码
     *
     * @param phoneNum
     */
    public void getRegistCode(final String phoneNum) {
        OkGo.<UserActivateGetCodeResponseBody>post(API.getUserRegistGetCodeUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .params("phoneNum", phoneNum)//
                .execute(new JsonCallback<UserActivateGetCodeResponseBody>(UserActivateGetCodeResponseBody.class) {
                    @Override
                    public void onStart(Request<UserActivateGetCodeResponseBody, ? extends Request> request) {
                        super.onStart(request);
                        createProgressBar();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        disMissProgress();
                    }

                    @Override
                    public void onSuccess(Response<UserActivateGetCodeResponseBody> response) {
                        UserActivateGetCodeResponseBody.ResultBean result = response.body().getResult();
                        if ("0".equals(result.getStatus())) {
                            //手机号可用
                            rlCodeSend.setVisibility(View.GONE);
                            rlCodeCheck.setVisibility(View.VISIBLE);
                            mTime.start();
                            changeGuideStepTextColor(2);
                        } else {
                            ToastUtils.showToastShort("您的手机号已经激活，请登录");    //手机号注册返回登录界面并且携带手机号
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("phoneNum", phoneNum);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<UserActivateGetCodeResponseBody> response) {
                        ToastUtils.showToastShort("网络出问题了");
                        super.onError(response);
                    }


                });
    }

    /**
     * 第一种方法 使用android封装好的 CountDownTimer
     * 创建一个类继承 CountDownTimer
     */
    public class CountDownTime extends CountDownTimer {

        //构造函数  第一个参数代表总的计时时长  第二个参数代表计时间隔  单位都是毫秒
        public CountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) { //每计时一次回调一次该方法
            btnResend.setClickable(false);
            btnResend.setText("重新获取(" + l / 1000 + ")");
        }

        @Override
        public void onFinish() { //计时结束回调该方法
            btnResend.setClickable(true);
            btnResend.setText("获取验证码");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        mTime.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);

        //取消所有请求
//        OkGo.getInstance().cancelAll();
    }
}
