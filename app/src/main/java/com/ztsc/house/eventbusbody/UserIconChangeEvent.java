package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.user.UserIconChangeResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserIconChangeEvent extends ZTAnyEventType {


    public UserIconChangeEvent() {
    }

    @Override
    public UserIconChangeResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        UserIconChangeResponseBody usericonchangeresponsebody= new Gson().fromJson(this.result, UserIconChangeResponseBody.class);

        return usericonchangeresponsebody;
    }
}
