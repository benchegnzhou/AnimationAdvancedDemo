package com.ztsc.house.meui;

import android.view.View;

import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;


import butterknife.OnClick;


public class HouseMinuteMsgActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_house_minute_msg;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_back, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title:
                break;
        }
    }
}
