package com.ztsc.house.eventbusbody;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.user.UserMessageChangeResponseBody;

/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserMessageChangeEvent extends ZTAnyEventType {


    public UserMessageChangeEvent() {
    }

    @Override
    public UserMessageChangeResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        UserMessageChangeResponseBody usermessagechangeresponsebody= new Gson().fromJson(this.result, UserMessageChangeResponseBody.class);

        return usermessagechangeresponsebody;
    }
}
