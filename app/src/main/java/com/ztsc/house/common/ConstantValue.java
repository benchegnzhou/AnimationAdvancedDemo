package com.ztsc.house.common;

/**
 * Created by lenovo on 2016/10/18.
 * app常量的定义
 */
public class ConstantValue {

    /**
     * 启动相机拍照的请求码
     */
    public static final int REQUEST_CODE_PHOTOGRAPH = 100;

    /**
     * 6.0获取电话状态权限的请求码
     */
    public static final int REQUEST_CODE_PHNE_STATUS = 101;
    /**
     * 6.0获取内存读写权限的请求码
     */
    public static final int REQUEST_CODE_WRITE_SDCARD = 102;


    /**
     * 本地媒体库查询的结果码
     */
    public static int REQUEST_CODE_MULTISELECT_ALBUM = 200;
    /**
     * 从媒体库筛选图片的请求码
     */
    public static final int REQUEST_CODE_ALBUM = 300;

    /**
     * 昵称修改请求码
     */
    public static final int REQUEST_CODE_NICENAMECHANGE_RESULT = 400;


    /**
     * 电话修改请求码
     */
    public static final int REQUEST_CODE_TELNUMCHANGE_RESULT = 401;
    /**
     * Activity  跳转协议码
     */
    public static final int erroCode = -1;                //错误注册协议码
    public static final int changeNiceNameCode = 0;     //昵称修改注册协议码
    public static final int changTelCode = 1;            //电话修改注册协议码

    /**
     * 用户个人信息存储
     */
    public static final String USER_INFORMATION_PATH = "wuyeUser";

    /**
     * 用户相关信息存储
     */
    public static final String ABOUT_USER_INFORMATION_PATH = "wuyeAboutUsers";



}
