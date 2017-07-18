package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.loacation.Gps2LoacatonBeen;


/**
 * Created by benchengzhou on 2017/5/2  14:42 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： ChangeGps2LocationEvent
 * 备    注：将定位到的坐标转换物理坐标
 */


public class ChangeGps2LocationEvent extends ZTAnyEventType {


    public ChangeGps2LocationEvent() {
    }

    @Override
    public Gps2LoacatonBeen parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        Gps2LoacatonBeen gps2LoacatonBeen = new Gson().fromJson(this.result, Gps2LoacatonBeen.class);

        return gps2LoacatonBeen;
    }
}
