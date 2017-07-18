package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.TestBody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class TestEvent extends ZTAnyEventType{


    public TestEvent() {
    }

    @Override
    public TestBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        TestBody testBody = new Gson().fromJson(this.result, TestBody.class);

        return testBody;
    }
}
