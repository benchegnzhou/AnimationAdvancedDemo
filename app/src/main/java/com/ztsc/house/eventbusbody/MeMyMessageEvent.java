package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.message.MyMessageResponseBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class MeMyMessageEvent extends ZTAnyEventType{


    public MeMyMessageEvent() {
    }

    @Override
    public MyMessageResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        MyMessageResponseBody mymessageresponsebody= new Gson().fromJson(this.result, MyMessageResponseBody.class);

        return mymessageresponsebody;
    }
}
