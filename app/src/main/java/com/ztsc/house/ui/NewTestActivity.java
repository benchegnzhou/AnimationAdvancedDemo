package com.ztsc.house.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.TestBody;
import com.ztsc.house.eventbusbody.TestEvent;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.network.api.doParams.ZTHouseHttpClient;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;



public class NewTestActivity extends BaseActivity {

    @Bind(R.id.et_aaaa)
    EditText etAaaa;
    @Bind(R.id.btn_aaa)
    Button btnAaa;
    @Bind(R.id.btn_aaaasdkjsa)
    Button btnAaaasdkjsa;
    @Bind(R.id.activity_new_test)
    RelativeLayout activityNewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        btnAaa.setOnClickListener(this);
        btnAaaasdkjsa.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_new_test;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_aaa:

                break;
            case R.id.btn_aaaasdkjsa:
                ZTHouseHttpClient.doTest();
                break;

        }
    }

    /**

     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TestEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            TestBody testBody = event.parseResult();
            LogUtil.e(testBody.toString());
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
