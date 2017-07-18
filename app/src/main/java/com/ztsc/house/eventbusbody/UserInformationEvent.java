package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.user.UserInformationResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserInformationEvent extends ZTAnyEventType{


    public UserInformationEvent() {
    }

    @Override
    public UserInformationResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        UserInformationResponseBody addfriendsresponsebody= new Gson().fromJson(this.result, UserInformationResponseBody.class);

        return addfriendsresponsebody;
    }
}
