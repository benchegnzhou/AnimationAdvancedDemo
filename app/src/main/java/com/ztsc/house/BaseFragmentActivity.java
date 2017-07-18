package com.ztsc.house;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;

import butterknife.ButterKnife;


/**
 * Created by benchengzhou on 2017/4/8.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：FragmentActivity权限申请
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {


    private int mReaquestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    /**
     * 由子类实现并且返回对应的完成监听操作和控件adapter的设置
     */
    protected abstract void initListener();

    /**
     * 用于子类的数据的初始化
     */
    protected abstract void initData();

    /**
     * 由子类实现并且返回对应的view
     *
     * @return
     */
    public abstract int getContentView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);//解除绑定，官方文档只对fragment做了解绑
    }


    protected void intent2Activity(Class<?> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        startActivity(intent);
    }

    protected void finishActivity() {
        this.finish();
    }

    /**
     * 沉浸式状态栏实现
     * ViewCompat.setFitsSystemWindows(mChildView, false)中的第二个参数设置为false就是全屏模式，
     * 而设置成true。像上述实例中， ViewCompat.setFitsSystemWindows(mChildView, false)
     * 就是说mChildView可以直接延伸到phoneWindow的顶部，相当于小米天气的那种效果。
     *
     * @param statusColor 状态栏切换后的颜色
     */
    public void setTransparentWindow(int statusColor) {
        //4.4版本设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // 设置状态栏透明
            this.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = this.getWindow();
            //设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
//            int statusColor =0xffff0000;
            window.setStatusBarColor(statusColor);
            //为了设置全屏
            ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }
        }

    }
    protected void requestPermition(int reaquest_code) {
        mReaquestCode = reaquest_code;
        if (getAndroidSDKVersion() >= 23) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请CALL_PHONE权限
                ActivityCompat.requestPermissions(BaseFragmentActivity.this, new String[]{Manifest.permission.SEND_SMS},
                        reaquest_code);
            }
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ToastUtils.showToastShort("onRequestPermissionsResult被调用！");
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == mReaquestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted准许
                ToastUtils.showToastShort("已获得授权！");

            } else {
                // Permission Denied拒绝
                ToastUtils.showToastShort("未获得授权！");
            }
        }
    }

    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            LogUtil.e("权限错误信息", e);
        }
        return version;
    }


}
