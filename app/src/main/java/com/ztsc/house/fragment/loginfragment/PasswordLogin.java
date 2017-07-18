package com.ztsc.house.fragment.loginfragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
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
import com.ztsc.house.bean.login.PwLoginResponseBody;
import com.ztsc.house.callback.JsonCallback;
import com.ztsc.house.common.ConstantValue;
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

import static com.ztsc.house.R.id.btn_login;


/**
 * Created by xuyang on 2017/2/27.
 */

public class PasswordLogin extends Fragment {
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    TextInputEditText etPassword;
    @Bind(btn_login)
    Button btnLogin;

    private Intent intent;
    private Gson gson;
    private final Context mContext;
    private String phoneNum;
    private String password;
    private SubmitUploadingDialog uploadingDialog;


    public PasswordLogin(Context context) {
        super();
        mContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_password, null);
        ButterKnife.bind(this, view);
        initialization();
        return view;
    }

    private void initialization() {

        gson = new Gson();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(btn_login)
    public void onClick(View v) {
        switch (v.getId()) {
            case btn_login:
                // 关闭软键盘
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etUsername.getWindowToken(), 0);
                // 获取登录URL
                phoneNum = etUsername.getText().toString();
                password = etPassword.getText().toString();
                if (TextUtils.isEmpty(phoneNum)) {
                    ToastUtils.showToastShort("您还没有输入用户名哦");
                    return;
                } else if (!Util.checkPhone(phoneNum)) {
                    ToastUtils.showToastShort("您的用户名不规范哦");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showToastShort("您还没有输入密码呢");
                    return;
                }

                boolean hasPermation = requestPermission();
                requestSDPermission();
                if (!hasPermation) {
                    ToastUtils.showToastShort("请设置权限");
                    break;
                }
                logIn(phoneNum, password);
                btnLogin.setEnabled(false);
                break;
        }
    }




    /**
     * 密码提交验证
     */
    private void logIn(String phoneNum, String passWord) {
        OkGo.<PwLoginResponseBody>post(API.getUserLoginByPasswordUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .params("phoneNum", phoneNum)//
                .params("passWord", passWord)//
                .execute(new JsonCallback<PwLoginResponseBody>(PwLoginResponseBody.class) {
                    @Override
                    public void onStart(Request<PwLoginResponseBody, ? extends Request> request) {
                        super.onStart(request);
                        createProgressBar();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        disMissProgress();
                    }

                    @Override
                    public void onSuccess(Response<PwLoginResponseBody> response) {
                        PwLoginResponseBody.ResultBean result = response.body().getResult();
                        if (0==result.getStatus()) {
                            ToastUtils.showToastShort("登录成功");
//                            downFile(result.getUserId());
                            //写入用户信息

                            UserInformationManager.getInstance().setUserInformation(true, result.getHeadImage(),
                                    result.getPhoneNum(), result.getToken(), result.getGender(), result.getUserName(),
                                    result.getUserId(), result.getHuanxinId(), result.getHuanxinPassWord());
                            getActivity().setResult(10);
                            getActivity().finish();
                        } else {
                            ToastUtils.showToastShort("用户名或密码错误");
                            btnLogin.setEnabled(true);
                        }

                    }

                    @Override
                    public void onError(Response<PwLoginResponseBody> response) {
                        super.onError(response);
                        response.body();
                        ToastUtils.showToastShort("网络出问题了");
                        btnLogin.setEnabled(true);
                    }
                });
    }

    private void downFile(String picName) {
        String file = FileUtils.getSDCardPath(mContext) + "/" + picName + ".png";
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
     * 6.0动态网络权限请求
     *
     * @return 自带网络权限检测的功能 返回是不是拥有对应的权限
     */

    public boolean requestPermission() {
        boolean hasPermission = false;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请摄像头的权限
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_PHONE_STATE},
                    ConstantValue.REQUEST_CODE_PHNE_STATUS);
            hasPermission = false;
        } else {
            hasPermission = true;
        }
        return hasPermission;
    }


    /**
     * 6.0动态网络权限请求
     *
     * @return 自带网络权限检测的功能 返回是不是拥有对应的权限
     */

    public boolean requestSDPermission() {
        boolean hasPermission = false;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请摄像头的权限
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    ConstantValue.REQUEST_CODE_WRITE_SDCARD);

        } else {
            hasPermission = true;
        }
        return hasPermission;
    }

    /**
     * 6.0新权限的注册回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //doNext(requestCode,grantResults);


        switch (requestCode) {
            case ConstantValue.REQUEST_CODE_PHNE_STATUS:
                if (  ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {       //是获取电话状态申请的回调，并且没有成功
                    ToastUtils.showToastShort("您已经拒绝权限请在设置中重新开启权限 ");
                } else  {
                    //权限成功
                    phoneNum = etUsername.getText().toString();
                    password = etPassword.getText().toString();
                    logIn(phoneNum, password);
                }
                break;
            case ConstantValue.REQUEST_CODE_WRITE_SDCARD:
                if ( ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {       //是内存读写权限申请的回调，并且没有成功
                    ToastUtils.showToastShort("您已经拒绝权限请在设置中重新开启权限 ");
                } else  {
                    //权限成功
                    ToastUtils.showToastShort("内存读写权限成功");
                }


                break;
        }


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

}
