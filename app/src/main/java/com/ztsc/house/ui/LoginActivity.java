package com.ztsc.house.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.adapter.MyLogin_PageAdapter;
import com.ztsc.house.fragment.loginfragment.CaptchaLogin;
import com.ztsc.house.fragment.loginfragment.PasswordLogin;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;



public class LoginActivity extends BaseActivity {

    //顶部导航
    @Bind(R.id.login_tablayout)
    TabLayout loginTablayout;
    //ViewPager
    @Bind(R.id.login_viewpager)
    ViewPager loginViewpager;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.btn_more)
    TextView btnMore;

    private String[] titles = {"密码登录", "验证码登录"};
    private List<Fragment> fragmentList;
    private MyLogin_PageAdapter adapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        textTitle.setText("登录");
        btnMore.setText("账号激活");
        initViewPager();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    //初始化ViewPager
    private void initViewPager() {
        fragmentList = new ArrayList<>();
        PasswordLogin f_password = new PasswordLogin(this);
        CaptchaLogin f_captcha = new CaptchaLogin(LoginActivity.this);

        fragmentList.add(f_password);
        fragmentList.add(f_captcha);
        //创建Viewpager的适配器
        adapter = new MyLogin_PageAdapter(getSupportFragmentManager(), fragmentList);
        loginViewpager.setAdapter(adapter);
        //初始化ViewPager
        loginTablayout.setupWithViewPager(loginViewpager);
        for (int i = 0; i < titles.length; i++) {
            loginTablayout.getTabAt(i).setText(titles[i]);
        }
    }

    @OnClick({R.id.iv_back, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                setResult(2);
                finish();
                break;
            case R.id.btn_more:
                startActivity(new Intent(this,  RegisterActivity.class));
                this.finish();
                break;
        }
    }
}
