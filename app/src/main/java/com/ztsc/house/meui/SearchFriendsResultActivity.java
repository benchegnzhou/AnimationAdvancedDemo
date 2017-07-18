package com.ztsc.house.meui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.friendsbean.SearchFriendsResponseBody;
import com.ztsc.house.dailog.LoadingDialog;
import com.ztsc.house.eventbusbody.GetActiveFriendsListEvent;
import com.ztsc.house.eventbusbody.SearchFriendsByPhoneEvent;
import com.ztsc.house.eventbusbody.SearchFriendsEvent;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.network.api.doParams.ZTHouseHttpClient;
import com.ztsc.house.ui.UserInformationActivity;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchFriendsResultActivity extends BaseActivity {
    @Bind(R.id.lv_search_result_friends)
    ListView lvsearchresultfriends;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private List<SearchFriendsResponseBody.ResultBean.UserListBean> friendslist = new ArrayList<>();
    private SearchFriendResultAdapter mAdapter;
    public static final String FROM_KEYWORD_SEARCH = "MySearch";
    public static final String FROM_NEAR_USERS = "NearUsers";
    public static final String ACTIVE_USERS = "ActiveUsers";
    public static final String TYPE = "ToSearchResult";
    private AlertDialog friendSelectDialog;
    private LoadingDialog loadingDialog;
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mAdapter = new SearchFriendResultAdapter();
        lvsearchresultfriends.setAdapter(mAdapter);
        final Intent intent = getIntent();
        String fromActivityId = intent.getStringExtra(TYPE);
        String fname = intent.getStringExtra("fname");
        if (FROM_KEYWORD_SEARCH.equals(fromActivityId)) {
//            手动搜索的
            tvTitle.setText("搜索结果");
            ZTHouseHttpClient.doSearchFriendsByPhone(UserInformationManager.getInstance().getUserId(), fname, UserInformationManager.getInstance().getToken());
        } else if (FROM_NEAR_USERS.equals(fromActivityId)) {
//            附近的人
            ZTHouseHttpClient.doGetSameZoneUser(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getUserCurrentVillageID(), UserInformationManager.getInstance().getToken());
//            ZTHouseHttpClient.doGetSameZoneUser(UserInformationManager.getInstance().getUserId(), "V001", UserInformationManager.getInstance().getToken());
        } else if (ACTIVE_USERS.equals(fromActivityId)) {
//             活跃的人
            tvTitle.setText("活跃的人");
            ZTHouseHttpClient.doGetActiveFriends(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(), "3");
        }

        lvsearchresultfriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userId = friendslist.get(position).getUserId();
                Intent intent2 = new Intent(SearchFriendsResultActivity.this,  UserInformationActivity.class);
                intent2.putExtra("userId", userId);
                startActivity(intent2);
            }
        });
        initFriendsSelectDialog();
        loadingDialog = new LoadingDialog(this, new LoadingDialog.onErrorReloadCallBack() {
            @Override
            public void onReloadClick() {
            }
        });
        loadingDialog.SyncState(LoadingDialog.STATE_LOADING);
    }

    private void initFriendsSelectDialog() {
            /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
    */
        AlertDialog.Builder selectFriendsDialogBuilder =
                new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.selectfriendsdialog, null);
        selectFriendsDialogBuilder.setTitle("筛选搜索结果");
        selectFriendsDialogBuilder.setView(dialogView);

        final TextView tvgenderAll = (TextView) dialogView.findViewById(R.id.tv_gender_all);
        final TextView tvgenderMale = (TextView) dialogView.findViewById(R.id.tv_gender_male);
        final TextView tvgenderFemale = (TextView) dialogView.findViewById(R.id.tv_gender_female);
        final TextView tvActive1 = (TextView) dialogView.findViewById(R.id.tv_active_1);
        final TextView tvActive3 = (TextView) dialogView.findViewById(R.id.tv_active_3);
        final TextView tvActive30 = (TextView) dialogView.findViewById(R.id.tv_active_30);
        final TextView tvAge1 = (TextView) dialogView.findViewById(R.id.tv_age_1);
        final TextView tvAge2 = (TextView) dialogView.findViewById(R.id.tv_age_2);
        final TextView tvAge3 = (TextView) dialogView.findViewById(R.id.tv_age_3);
        final TextView tvAge4 = (TextView) dialogView.findViewById(R.id.tv_age_4);
        final TextView tvAge5 = (TextView) dialogView.findViewById(R.id.tv_age_5);


        View.OnClickListener dialogOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_gender_all:
                        tvgenderAll.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvgenderMale.setBackground(null);
                        tvgenderFemale.setBackground(null);
                        break;
                    case R.id.tv_gender_male:
                        tvgenderAll.setBackground(null);
                        tvgenderMale.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvgenderFemale.setBackground(null);
                        break;
                    case R.id.tv_gender_female:
                        tvgenderAll.setBackground(null);
                        tvgenderMale.setBackground(null);
                        tvgenderFemale.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        break;
                    case R.id.tv_active_1:
                        tvActive1.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvActive3.setBackground(null);
                        tvActive30.setBackground(null);
                        break;
                    case R.id.tv_active_3:
                        tvActive1.setBackground(null);
                        tvActive3.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvActive30.setBackground(null);
                        break;
                    case R.id.tv_active_30:
                        tvActive1.setBackground(null);
                        tvActive3.setBackground(null);
                        tvActive30.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        break;
                    case R.id.tv_age_1:
                        tvAge1.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvAge2.setBackground(null);
                        tvAge3.setBackground(null);
                        tvAge4.setBackground(null);
                        tvAge5.setBackground(null);
                        break;
                    case R.id.tv_age_2:
                        tvAge1.setBackground(null);
                        tvAge2.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvAge3.setBackground(null);
                        tvAge4.setBackground(null);
                        tvAge5.setBackground(null);
                        break;
                    case R.id.tv_age_3:
                        tvAge1.setBackground(null);
                        tvAge2.setBackground(null);
                        tvAge3.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvAge4.setBackground(null);
                        tvAge5.setBackground(null);
                        break;
                    case R.id.tv_age_4:
                        tvAge1.setBackground(null);
                        tvAge2.setBackground(null);
                        tvAge3.setBackground(null);
                        tvAge4.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        tvAge5.setBackground(null);
                        break;
                    case R.id.tv_age_5:
                        tvAge1.setBackground(null);
                        tvAge2.setBackground(null);
                        tvAge3.setBackground(null);
                        tvAge4.setBackground(null);
                        tvAge5.setBackground(getResources().getDrawable(R.drawable.friendsselectdialogtextviewshape));
                        break;
                }
            }
        };
        tvgenderAll.setOnClickListener(dialogOnClickListener);
        tvgenderMale.setOnClickListener(dialogOnClickListener);
        tvgenderFemale.setOnClickListener(dialogOnClickListener);
        tvActive1.setOnClickListener(dialogOnClickListener);
        tvActive3.setOnClickListener(dialogOnClickListener);
        tvActive30.setOnClickListener(dialogOnClickListener);
        tvAge1.setOnClickListener(dialogOnClickListener);
        tvAge2.setOnClickListener(dialogOnClickListener);
        tvAge3.setOnClickListener(dialogOnClickListener);
        tvAge4.setOnClickListener(dialogOnClickListener);
        tvAge5.setOnClickListener(dialogOnClickListener);


        selectFriendsDialogBuilder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        selectFriendsDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        friendSelectDialog = selectFriendsDialogBuilder.create();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_search_friends_result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 用户获取同小区好友列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SearchFriendsEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            SearchFriendsResponseBody searchFriendsResponseBody = event.parseResult();
            LogUtil.e(searchFriendsResponseBody.toString());
            SearchFriendsResponseBody.ResultBean result = searchFriendsResponseBody.getResult();
            friendslist.addAll(result.getUserList());
            mAdapter.notifyDataSetChanged();
            loadingDialog.SyncState(LoadingDialog.STATE_SUCCEED);
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
            loadingDialog.SyncState(LoadingDialog.STATE_ERROR);
        }
    }

    /**
     * 根据手机号获取好友列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SearchFriendsByPhoneEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            SearchFriendsResponseBody searchFriendsResponseBody = event.parseResult();
            LogUtil.e(searchFriendsResponseBody.toString());
            SearchFriendsResponseBody.ResultBean result = searchFriendsResponseBody.getResult();
            friendslist.addAll(result.getUserList());
            mAdapter.notifyDataSetChanged();
            loadingDialog.SyncState(LoadingDialog.STATE_SUCCEED);
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
            loadingDialog.SyncState(LoadingDialog.STATE_ERROR);
        }
    }

    /**
     * 用户获取活跃的好友列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(  GetActiveFriendsListEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            SearchFriendsResponseBody searchFriendsResponseBody = event.parseResult();
            LogUtil.e(searchFriendsResponseBody.toString());
            SearchFriendsResponseBody.ResultBean result = searchFriendsResponseBody.getResult();
            friendslist.addAll(result.getUserList());
            mAdapter.notifyDataSetChanged();
            loadingDialog.SyncState(LoadingDialog.STATE_SUCCEED);
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
            loadingDialog.SyncState(LoadingDialog.STATE_ERROR);
        }
    }


    @OnClick({R.id.iv_back, R.id.iv_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_select:
                friendSelectDialog.show();
                break;
        }
    }

    public class SearchFriendResultAdapter extends BaseAdapter {

        public SearchFriendResultAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return friendslist.size();
        }

        @Override
        public Object getItem(int i) {
            return friendslist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = LayoutInflater.from(SearchFriendsResultActivity.this).inflate(R.layout.item_friends_result, null);
                holder.username = (TextView) convertView.findViewById(R.id.tv_item_name);
                holder.statue = (TextView) convertView.findViewById(R.id.tv_statue);
                holder.usericon = (ImageView) convertView.findViewById(R.id.iv_user_icon);
                holder.signature = (TextView) convertView.findViewById(R.id.tv_signature);
                holder.age = (TextView) convertView.findViewById(R.id.tv_age);
                holder.gender = (TextView) convertView.findViewById(R.id.tv_gender);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final SearchFriendsResponseBody.ResultBean.UserListBean userListBean = friendslist.get(i);
            holder.username.setText(userListBean.getUserName());
            Picasso.with(SearchFriendsResultActivity.this).load(userListBean.getUserImgUrl()).error(R.drawable.default_avatar).into(holder.usericon);
            holder.age.setText(userListBean.getUserAge() + "岁");

            String gender = userListBean.getUserGender();
            switch (gender) {
                case "0":
                    holder.gender.setBackground(getResources().getDrawable(R.drawable.userinfor_gender_male));
                    holder.gender.setText("男♂");
                    break;
                case "1":
                    holder.gender.setBackground(getResources().getDrawable(R.drawable.userinfor_gender_female));
                    holder.gender.setText("女♀");
                    break;
            }

            int relationship = userListBean.getRelationship();
            switch (relationship){
                case 0:
                    holder.statue.setText("我关注的");
                    break;
                case 1:
                    holder.statue.setText("粉丝");
                    break;
                case 2:
                    holder.statue.setText("好友");
                    break;
                default:
                    holder.statue.setText("陌生人");
            }
            return convertView;
        }

        public class ViewHolder {
            TextView username, statue, signature, age, gender;
            ImageView usericon;
        }
    }


}
