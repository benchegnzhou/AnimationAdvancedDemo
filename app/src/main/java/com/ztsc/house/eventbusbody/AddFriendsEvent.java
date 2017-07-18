package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.friendsbean.AddFriendsResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class AddFriendsEvent extends  ZTAnyEventType {


    public AddFriendsEvent() {
    }

    @Override
    public AddFriendsResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        AddFriendsResponseBody addfriendsresponsebody= new Gson().fromJson(this.result, AddFriendsResponseBody.class);

        return addfriendsresponsebody;
    }
}
