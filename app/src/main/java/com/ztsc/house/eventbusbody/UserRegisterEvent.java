package com.ztsc.house.eventbusbody;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.checkcode.RegistCodeCheckResponseBody;

/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class UserRegisterEvent extends ZTAnyEventType {


    public UserRegisterEvent() {
    }

    @Override
    public RegistCodeCheckResponseBody parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        RegistCodeCheckResponseBody registGetCodeResponseBody = new Gson().fromJson(this.result, RegistCodeCheckResponseBody.class);

        return registGetCodeResponseBody;
    }
}
