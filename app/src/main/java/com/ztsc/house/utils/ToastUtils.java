package com.ztsc.house.utils;

import android.widget.Toast;

import com.ztsc.house.BuildConfig;
import com.ztsc.house.application.MApplication;


/**
 * Created by lenovo on 2016/8/31.
 * 全局的toast类
 */
public class ToastUtils {

    /*
     * 定义的土司工具类,实现单例的可以连续的弹出内容的土司
     */
    public static Toast toast;
    public static boolean isShowToast = BuildConfig.TOAST_DEBUG;

    /**
     * 用户提示吐司
     * @param text
     */
    public static void showToastShort(String text) {
        // 如果toast为空就创建
        if (toast == null) {
            toast = Toast.makeText(MApplication.sAppContext, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text); // 如果不为空就修改显示的内容
        }
        // 显示土司
        toast.show();
    }

    /**
     * 用户提示吐司
     * @param text
     */
    public static void showToastLong(String text) {
        // 如果toast为空就创建
        if (toast == null) {
            toast = Toast.makeText(MApplication.sAppContext, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text); // 如果不为空就修改显示的内容
        }
        // 显示土司
        toast.show();
    }

    /**
     * 调试时短吐司
     * @param text
     */
    public static void showDebugToastShort(String text) {
        if(!isShowToast){
            return;
        }

        // 如果toast为空就创建
        if (toast == null) {
            toast = Toast.makeText(MApplication.sAppContext, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text); // 如果不为空就修改显示的内容
        }
        // 显示土司
        toast.show();
    }

    /**
     * 调试时长吐司
     * @param text
     */
    public static void showDebugToastLong(String text) {
        if(!isShowToast){
            return;
        }


        // 如果toast为空就创建
        if (toast == null) {
            toast = Toast.makeText(MApplication.sAppContext, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text); // 如果不为空就修改显示的内容
        }
        // 显示土司
        toast.show();
    }



}