package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.login.PwLoginResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserLoginByPasswordEvent extends ZTAnyEventType {


    public UserLoginByPasswordEvent() {
    }

    @Override
    public PwLoginResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        PwLoginResponseBody  pwloginresponsebody= new Gson().fromJson(this.result, PwLoginResponseBody.class);

        return pwloginresponsebody;
    }
}
