package com.ztsc.house.fragment.loginfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.ztsc.house.R;
import com.ztsc.house.bean.getcode.LoginGetCodeResponseBody;
import com.ztsc.house.bean.login.CodeLoginResponseBody;
import com.ztsc.house.callback.JsonCallback;
import com.ztsc.house.dailog.SubmitUploadingDialog;
import com.ztsc.house.network.api.API;
import com.ztsc.house.network.api.net.PostKeyVule;
import com.ztsc.house.network.api.net.ReqCallBack;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.FileUtils;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;
import com.ztsc.house.utils.Util;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by xuyang on 2017/2/27.
 */

public class CaptchaLogin extends Fragment {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.btn_getmark)
    Button btnGetmark;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;


    private Intent intent;
    private Gson gson;
    private CountDownTime mTime;
    OkHttpClient client = new OkHttpClient();
    private String myCaptcha;
    private String phoneNum;
    private Context mContent;
    private SubmitUploadingDialog uploadingDialog;


    public CaptchaLogin(Context context) {
        mContent = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_msg, null);
        ButterKnife.bind(this, view);
        initialization();
        mTime = new CountDownTime(60000, 1000);//初始化对象
        btnGetmark.setEnabled(false);
        btnLogin.setEnabled(false);
        return view;
    }


    private void initialization() {

        gson = new Gson();
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean b = Util.checkPhone(etUsername.getText().toString());
                if (b) {
                    btnGetmark.setEnabled(true);
                } else {
                    btnGetmark.setEnabled(false);
                }
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Util.checkPhone(etUsername.getText().toString()) && etPassword.getText().toString().trim().length() == 6) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_getmark, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
//            获取验证码
            case R.id.btn_getmark:
                mTime.start();
                phoneNum = etUsername.getText().toString();
                getLoginCode(phoneNum);
                break;

//            点击登录
            case R.id.btn_login:
                // 关闭软键盘
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etUsername.getWindowToken(), 0);

                // 获取登录URL
                phoneNum = etUsername.getText().toString();
                myCaptcha = etPassword.getText().toString();
//                String url_login = BASE_URL + username + "/login.json";


                if (TextUtils.isEmpty(phoneNum)) {
                    ToastUtils.showToastShort("您还没有输入手机号哦");
                    return;
                } else if (phoneNum.length() > 12) {
                    ToastUtils.showToastShort("您的手机号不规范哦");
                    return;
                }
                if (TextUtils.isEmpty(myCaptcha)) {
                    ToastUtils.showToastShort("您还没有输入验证码呢");
                    return;
                }
                createProgressBar("登录中...");
                //执行网络请求操作
                CheckCode(phoneNum, myCaptcha);
                break;
        }
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
            btnGetmark.setClickable(false);
            btnGetmark.setText(l / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() { //计时结束回调该方法
            btnGetmark.setClickable(true);
            btnGetmark.setText("获取验证码");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mTime.cancel();
    }


    private void downFile() {
        String file = FileUtils.getSDCardPath(mContent) + "/" + UserInformationManager.getInstance().getUserId() + ".png";
        LogUtil.e("文件路径：" + file);
        PostKeyVule.downLoadFile(UserInformationManager.getInstance().getHeadImage(), file, new ReqCallBack() {
            @Override
            public void successCallBack(File file, String callBack) {
                LogUtil.e("成功");
            }

            @Override
            public void failedCallBack(String message, String callBack) {
                LogUtil.e("失败");
            }
        });


    }

    /**
     * 提交样式，不带有文字的ProgressBar
     */
    public void createProgressBar() {
        this.createProgressBar(null);
    }

    /**
     * 提交样式，带有文字的ProgressBar
     *
     * @param text 提示的文字内容
     */
    public void createProgressBar(String text) {

        if (uploadingDialog == null) {
            if (text == null) {
                uploadingDialog = new SubmitUploadingDialog(getActivity());
            } else {
                uploadingDialog = new SubmitUploadingDialog(getActivity(), text);
            }
            uploadingDialog.setCancelable(true);
        }
        if (!uploadingDialog.isShowing()) {
            uploadingDialog.show();
        }
    }

    /**
     * 提交对话框隐藏
     */
    public void disMissProgress() {
        if (uploadingDialog != null && uploadingDialog.isShowing()) {
            uploadingDialog.dismiss();
        }
    }

    /**
     * 用户登录获取验证码
     *
     * @param phoneNum
     */
    public void getLoginCode(final String phoneNum) {
        OkGo.<LoginGetCodeResponseBody>post(API.getUserLoginCodeUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .params("phoneNum", phoneNum)//
                .execute(new JsonCallback<LoginGetCodeResponseBody>(LoginGetCodeResponseBody.class) {
                    @Override
                    public void onStart(Request<LoginGetCodeResponseBody, ? extends Request> request) {
                        super.onStart(request);
                        createProgressBar("正在获取");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        disMissProgress();
                    }

                    @Override
                    public void onSuccess(Response<LoginGetCodeResponseBody> response) {
                        LoginGetCodeResponseBody.ResultBean result = response.body().getResult();
                        if ("0".equals(result.getStatus())) {
                            ToastUtils.showToastShort("获取验证码成功");
                        } else {
                            ToastUtils.showToastShort("获取验证码失败");
                            mTime.cancel();
                            btnGetmark.setClickable(true);
                            btnGetmark.setText("获取验证码");
                        }
                    }

                    @Override
                    public void onError(Response<LoginGetCodeResponseBody> response) {
                        ToastUtils.showToastShort("网络出问题了");
                        mTime.cancel();
                        super.onError(response);
                    }


                });
    }

    /**
     * 验证码校验
     */
    private void CheckCode(String phoneNum, String code) {
        OkGo.<CodeLoginResponseBody>post(API.getUserLoginByCodeUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .params("phoneNum", phoneNum)//
                .params("code", code)//
                .execute(new JsonCallback<CodeLoginResponseBody>(CodeLoginResponseBody.class) {
                    @Override
                    public void onStart(Request<CodeLoginResponseBody, ? extends Request> request) {
                        super.onStart(request);
                        createProgressBar();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        disMissProgress();
                    }

                    @Override
                    public void onSuccess(Response<CodeLoginResponseBody> response) {
                        CodeLoginResponseBody.ResultBean result = response.body().getResult();
                        if ("0".equals(result.getStatus())) {
                            ToastUtils.showToastShort("登录成功");
//                            downFile();
                            UserInformationManager.getInstance().setUserInformation(true, result.getHeadImage(),
                                    result.getPhoneNum(), result.getToken(), result.getGender(), result.getUserName(),
                                    result.getUserId(), result.getHuanxinId(), result.getHuanxinPassWord());

                            getActivity().setResult(10);
                            getActivity().finish();
                        } else {
                            ToastUtils.showToastShort("验证码错误");
                        }

                    }

                    @Override
                    public void onError(Response<CodeLoginResponseBody> response) {
                        super.onError(response);
                        ToastUtils.showToastShort("网络出问题了");
                    }
                });
    }
}
