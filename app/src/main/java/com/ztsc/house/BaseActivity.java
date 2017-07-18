package com.ztsc.house;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;


import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;


import com.githang.statusbar.StatusBarCompat;
import com.ztsc.house.dailog.SubmitUploadingDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

import pub.devrel.easypermissions.BasePermissionActivity;

/**
 * Created by benchengzhou on 2017/5/10  14:22 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： BaseActivity
 * 备    注：
 */
public abstract class BaseActivity extends BasePermissionActivity implements View.OnClickListener {

    private SubmitUploadingDialog uploadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        othersInit();
        setContentView(getContentView());
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.appthemedark), false);
        ButterKnife.bind(this);
        initListener();
        initData();
    }
    /**
     * 由子类实现并且返回对应的view
     *
     * @return
     */
    public abstract int getContentView();



    public void othersInit() {

    }


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

    /**
     * 1、获取main在窗体的可视区域
     * 2、获取main在窗体的不可视区域高度
     * 3、判断不可视区域高度
     * 1、大于100：键盘显示  获取Scroll的窗体坐标
     * 算出main需要滚动的高度，使scroll显示。
     * 2、小于100：键盘隐藏
     *
     * @param mView  根布局
     * @param scroll 需要显示的最下方View
     */
    public void addLayoutListener(final View mView, final View scroll) {
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                mView.getWindowVisibleDisplayFrame(rect);
                int mainInvisibleHeight = mView.getRootView().getHeight() - rect.bottom;
                if (mainInvisibleHeight > 100) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;
                    mView.scrollTo(0, srollHeight);
                } else {
                    mView.scrollTo(0, 0);
                }
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
}
