package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.getcode.LoginGetCodeResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserLoginGetCodeEvent extends ZTAnyEventType{


    public UserLoginGetCodeEvent() {
    }

    @Override
    public LoginGetCodeResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        LoginGetCodeResponseBody logingetcoderesponsebody= new Gson().fromJson(this.result, LoginGetCodeResponseBody.class);

        return logingetcoderesponsebody;
    }
}
