package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.friendsbean.DeleteFriendsResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class DeleteFriendsEvent extends ZTAnyEventType{


    public DeleteFriendsEvent() {
    }

    @Override
    public DeleteFriendsResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        DeleteFriendsResponseBody deletefriendsresponsebody= new Gson().fromJson(this.result, DeleteFriendsResponseBody.class);

        return deletefriendsresponsebody;
    }
}
