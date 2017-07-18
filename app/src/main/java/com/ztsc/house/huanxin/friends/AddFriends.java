package com.ztsc.house.huanxin.friends;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.meui.SearchFriendsResultActivity;

import butterknife.Bind;
import butterknife.OnClick;


public class AddFriends extends BaseActivity {
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.btn_request)
    Button btnRequest;

    @Override
    protected void initListener() {
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = etUsername.getText().toString().trim();
                Intent intent = new Intent(AddFriends.this,  SearchFriendsResultActivity.class);
                intent.putExtra("ToSearchResult", "MySearch");
                intent.putExtra("fname", fname);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_add_friends;
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.iv_back, R.id.btn_request})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_request:
                String fname = etUsername.getText().toString().trim();
                Intent intent = new Intent(AddFriends.this, SearchFriendsResultActivity.class);
                intent.putExtra("ToSearchResult", "MySearch");
                intent.putExtra("fname", fname);
                startActivity(intent);
                finish();
                break;
        }
    }

}
