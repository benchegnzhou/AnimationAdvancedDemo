package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.friendsbean.MyFriendsResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class GetFriendsListEvent extends ZTAnyEventType{


    public GetFriendsListEvent() {
    }

    @Override
    public MyFriendsResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        MyFriendsResponseBody myfriendsresponsebody= new Gson().fromJson(this.result, MyFriendsResponseBody.class);

        return myfriendsresponsebody;
    }
}
