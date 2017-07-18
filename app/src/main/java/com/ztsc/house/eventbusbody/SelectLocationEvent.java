package com.ztsc.house.eventbusbody;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztsc.house.bean.loacation.LocationSelectionBeen;


/**
 * Created by benchengzhou on 2017/4/25  17:17 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： SelectLocationEvent
 * 备    注： 选择当前位置
 */

public class SelectLocationEvent extends ZTAnyEventType{


    public SelectLocationEvent() {
    }

    @Override
    public LocationSelectionBeen parseResult() {
        if (this.code == FAIL) {
            return null;
        }
        if (TextUtils.isEmpty(this.result)) {
            return null;
        }
        LocationSelectionBeen locationSelectionBeen = new Gson().fromJson(this.result, LocationSelectionBeen.class);

        return locationSelectionBeen;
    }
}
