package com.ztsc.house.bean;

import java.io.Serializable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by benchengzhou on 2017/3/28 11:13 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： TestActivityActivity
 * 备    注：
 */

public class CommonRespose <T> implements Serializable {
    private static final long serialVersionUID = 5213230387175987834L;
    public int code;
    public String message;
    public T result;

    @Override
    public String toString() {
        return "CommonRespose{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}


