package com.ztsc.house.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.zhy.autolayout.AutoRelativeLayout;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.fragment.myfriendsfragment.MyConversationFragment;
import com.ztsc.house.fragment.myfriendsfragment.MyFriendsFragment;
import com.ztsc.house.huanxin.friends.AddFriends;
import com.ztsc.house.meui.SearchFriendsResultActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyConversationActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener {

    @Bind(R.id.more)
    ImageView more;
    @Bind(R.id.rg_choose)
    RadioGroup rgChoose;
    @Bind(R.id.rb_conversation)
    RadioButton rbConversation;
    @Bind(R.id.rb_friends)
    RadioButton rbFriends;
    @Bind(R.id.rl_bar)
    RelativeLayout rlBar;
    @Bind(R.id.rl_container)
    AutoRelativeLayout rlContainer;


    private List<Fragment> list = new ArrayList<>();
    private FragmentManager supportFragmentManager;
    private int PAGEINDEX;

    @Override
    protected void initListener() {
        rgChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_conversation:
                        rbFriends.setTextColor(Color.WHITE);
                        rbConversation.setTextColor(getResources().getColor(R.color.apptheme));

                        FragmentTransaction fragmentTransaction1 = supportFragmentManager.beginTransaction();
                        if (!list.get(0).isAdded()) {
                            fragmentTransaction1.add(R.id.rl_container, list.get(0)).hide(list.get(PAGEINDEX)).show(list.get(0));
                            PAGEINDEX = 0;
                        } else {
                            fragmentTransaction1.hide(list.get(PAGEINDEX)).show(list.get(0));
                            PAGEINDEX = 0;
                        }
                        fragmentTransaction1.commit();
                        break;
                    case R.id.rb_friends:
                        rbConversation.setTextColor(Color.WHITE);
                        rbFriends.setTextColor(getResources().getColor(R.color.apptheme));
                        FragmentTransaction fragmentTransaction2 = supportFragmentManager.beginTransaction();
                        if (!list.get(1).isAdded()) {
                            fragmentTransaction2.add(R.id.rl_container, list.get(1)).hide(list.get(PAGEINDEX)).show(list.get(1));
                            PAGEINDEX = 1;
                        } else {
                            fragmentTransaction2.hide(list.get(PAGEINDEX)).show(list.get(1));
                            PAGEINDEX = 1;
                        }
                        fragmentTransaction2.commit();
                        break;
                }
            }
        });

    }

    @Override
    protected void initData() {
        list.add(new MyConversationFragment());
        list.add(new MyFriendsFragment());
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.rl_container, list.get(0)).show(list.get(0));
        PAGEINDEX = 0;
        fragmentTransaction.commit();
    }

    @Override
    public int getContentView() {
        return R.layout.conversation;
    }

    @OnClick({R.id.iv_back, R.id.more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.more:
                PopupMenu popup = new PopupMenu(this, view);//第二个参数是绑定的那个view
                popup.getMenuInflater().inflate(R.menu.my_conversation_popmenu, popup.getMenu());
                popup.setOnMenuItemClickListener(this);
                popup.show();
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.same_town:
                Intent intent = new Intent(this, SearchFriendsResultActivity.class);
                intent.putExtra("ToSearchResult", "NearUsers");
                startActivity(intent);
                break;
            case R.id.active_people:
                Intent intent1 = new Intent(this, SearchFriendsResultActivity.class);
                intent1.putExtra("ToSearchResult", "ActiveUsers");
                startActivity(intent1);
                break;
            case R.id.search:
                startActivity(new Intent(this, AddFriends.class));
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
