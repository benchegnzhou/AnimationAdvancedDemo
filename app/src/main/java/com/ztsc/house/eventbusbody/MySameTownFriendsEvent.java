package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.friendsbean.SameTownFriendsResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class MySameTownFriendsEvent extends ZTAnyEventType{


    public MySameTownFriendsEvent() {
    }

    @Override
    public SameTownFriendsResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        SameTownFriendsResponseBody sametownfriendsresponsebody= new Gson().fromJson(this.result, SameTownFriendsResponseBody.class);

        return sametownfriendsresponsebody;
    }
}
