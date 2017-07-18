package com.ztsc.house.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ztsc.house.BaseActivity;
import com.ztsc.house.BaseSlidingMenuActivity;
import com.ztsc.house.R;
import com.ztsc.house.customview.SmoothOverScrollview;
import com.ztsc.house.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TestOverScrollViewActivity extends BaseActivity {

    @Bind(R.id.tv_view)
    TextView tvView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_test_over_scroll_view;
    }

    @Override
    protected void initListener() {
        tvView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_view:
                ToastUtils.showToastShort("点击");
                break;

        }
    }
}
