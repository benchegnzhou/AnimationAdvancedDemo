package com.ztsc.house;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.ztsc.house.dailog.SubmitUploadingDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.PermissionCallBackM;
import pub.devrel.easypermissions.easyPermission.EasyPermission;

public abstract class BaseSlidingMenuActivity extends SlidingFragmentActivity implements View.OnClickListener , EasyPermission.PermissionCallback {
    private SubmitUploadingDialog uploadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.appthemedark), false);
        ButterKnife.bind(this);
        initListener();
        initData();
    }


/* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.appthemedark), false);
        ButterKnife.bind(this);
        initListener();
        initData();

    }*/

    /**
     * 由子类实现并且返回对应的view
     *
     * @return
     */
    public abstract int getContentView();

    /**
     * 由子类实现并且返回对应的完成监听操作和控件adapter的设置
     */
    protected abstract void initListener();

    /**
     * 用于子类的数据的初始化
     */
    protected abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    protected void finishActivity() {
        this.finish();
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
                uploadingDialog = new SubmitUploadingDialog(this);
            } else {
                uploadingDialog = new SubmitUploadingDialog(this, text);
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
     * 内存垃圾释放
     */
    public void gcAndFinalize() {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        runtime.runFinalization();
        System.gc();
    }

    /**
     * 下面的事情和权限申请有关
     *
     *
     *
     *
     *
     */
    private int mRequestCode;
    private String[] mPermissions;
    private PermissionCallBackM mPermissionCallBack;

    //rationale: 申请授权理由
    protected void requestPermission(int requestCode, String[] permissions, String rationale,
                                     PermissionCallBackM permissionCallback) {
        this.mRequestCode = requestCode;
        this.mPermissionCallBack = permissionCallback;
        this.mPermissions = permissions;

        EasyPermission.with(this)
                .addRequestCode(requestCode)
                .permissions(permissions)
                //.nagativeButtonText(android.R.string.ok)
                //.positveButtonText(android.R.string.cancel)
                .rationale(rationale)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
            从Settings界面跳转回来，标准代码，就这么写
        */
        if (requestCode == EasyPermission.SETTINGS_REQ_CODE) {
            if (EasyPermission.hasPermissions(this, mPermissions)) {
                //已授权，处理业务逻辑
                onEasyPermissionGranted(mRequestCode, mPermissions);
            } else {
                onEasyPermissionDenied(mRequestCode, mPermissions);
            }
        }
    }

    /**
     * 权限申请
     * @param requestCode
     * @param perms
     */
    @Override
    public void onEasyPermissionGranted(int requestCode, String... perms) {
        if (mPermissionCallBack != null) {
            mPermissionCallBack.onPermissionGrantedM(requestCode, perms);
        }
    }

    @Override
    public void onEasyPermissionDenied(final int requestCode, final String... perms) {
        //rationale: Never Ask Again后的提示信息
        if (EasyPermission.checkDeniedPermissionsNeverAskAgain(this, "授权啊,不授权没法用啊," + "去设置里授权大哥", android.R.string.ok,
                android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        if (mPermissionCallBack != null) {
                            mPermissionCallBack.onPermissionDeniedM(
                                    requestCode, perms);
                        }
                    }
                }, perms)) {
            return;
        }

        if (mPermissionCallBack != null) {
            mPermissionCallBack.onPermissionDeniedM(requestCode, perms);
        }
    }


}
