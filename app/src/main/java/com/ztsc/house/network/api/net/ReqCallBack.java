package com.ztsc.house.network.api.net;

import java.io.File;

/**
 * Created by benchengzhou on 2017/3/28 11:13 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： TestActivityActivity
 * 备    注：
 */
public interface ReqCallBack {

    void successCallBack(File file, String callBack);

    void failedCallBack(String message, String callBack);
}
