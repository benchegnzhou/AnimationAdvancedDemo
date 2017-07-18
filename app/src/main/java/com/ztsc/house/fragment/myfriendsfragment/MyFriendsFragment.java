package com.ztsc.house.fragment.myfriendsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ztsc.house.R;
import com.ztsc.house.bean.friendsbean.DeleteFriendsResponseBody;
import com.ztsc.house.bean.friendsbean.MyFriendsResponseBody;
import com.ztsc.house.dailog.LoadingDialog;
import com.ztsc.house.eventbusbody.DeleteFriendsEvent;
import com.ztsc.house.eventbusbody.GetFriendsListEvent;
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
import butterknife.ButterKnife;

public class MyFriendsFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    @Bind(R.id.lv_friends_list)
    ListView lvFriendsList;
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;
    private List<MyFriendsResponseBody.ResultBean.FriendListBean> friendlist = new ArrayList<>();
    private FriendListAdapter adapter;
    private PopupMenu popup;
    private int mPosition;
    private boolean isRun = true;
    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_friends, container, false);
        ButterKnife.bind(this, view);
        adapter = new FriendListAdapter();
        lvFriendsList.setAdapter(adapter);
        initListen();
        loadingDialog = new LoadingDialog(getActivity(), new LoadingDialog.onErrorReloadCallBack() {
            @Override
            public void onReloadClick() {
                loadData();
                loadingDialog.SyncState(LoadingDialog.STATE_LOADING);
            }
        });
        loadingDialog.SyncState(LoadingDialog.STATE_LOADING);
        loadData();
        return view;
    }

    private void loadData() {
        friendlist.clear();
        ZTHouseHttpClient.doGetFriendsList(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken());
    }

    private void initListen() {
        lvFriendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),  UserInformationActivity.class);
                intent.putExtra("userId",friendlist.get(position).getFriendId());
                startActivity(intent);
            }
        });
        lvFriendsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                //第二个参数是绑定的那个view
                popup = new PopupMenu(getActivity(), view);
                popup.getMenuInflater().inflate(R.menu.my_friends_delete_popmenu, popup.getMenu());
                popup.setOnMenuItemClickListener(MyFriendsFragment.this);
                popup.show();
                return false;
            }
        });
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    /**
     * 用户获取好友列表
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(  GetFriendsListEvent event) {
        if (srl.isRefreshing()){
            srl.setRefreshing(false);
        }
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            MyFriendsResponseBody myFriendsResponseBody = event.parseResult();
            LogUtil.e(myFriendsResponseBody.toString());
            MyFriendsResponseBody.ResultBean result = myFriendsResponseBody.getResult();
            friendlist.addAll(result.getFriendList());
            adapter.notifyDataSetChanged();
            loadingDialog.SyncState(LoadingDialog.STATE_SUCCEED);
        } else {
            loadingDialog.SyncState(LoadingDialog.STATE_ERROR);
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    /**
     * 用户删除好友
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DeleteFriendsEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            DeleteFriendsResponseBody deleteFriendsResponseBody = event.parseResult();
            LogUtil.e(deleteFriendsResponseBody.toString());
            DeleteFriendsResponseBody.ResultBean result = deleteFriendsResponseBody.getResult();
            if (result.getStatusCode() == 0) {
                ToastUtils.showToastShort("删除成功");
                friendlist.remove(mPosition);
                adapter.notifyDataSetChanged();
            } else {
                ToastUtils.showToastShort("删除失败");
            }
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                if (isRun) {
                    isRun = false;
                    ZTHouseHttpClient.doDeleteFriends(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(), friendlist.get(mPosition).getFriendId());
                    popup.dismiss();
                } else {
                    ToastUtils.showToastShort("正在执行上一个删除操作，稍后再试");
                }
                break;
            case R.id.cancel:
                popup.dismiss();
                break;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class FriendListAdapter extends BaseAdapter {

        public FriendListAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return friendlist.size();
        }

        @Override
        public Object getItem(int i) {
            return friendlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = new ViewHolder();
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.friend_list_item, null);
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
            MyFriendsResponseBody.ResultBean.FriendListBean friendListBean = friendlist.get(i);
            holder.username.setText(friendListBean.getFriendName());
            Picasso.with(getActivity()).load(friendListBean.getFriendImgUrl()).error(R.drawable.default_avatar).into(holder.usericon);

            holder.age.setText(friendListBean.getFriendAge() + "岁");

            String gender = friendListBean.getFriendGender();
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
            int relationship = friendListBean.getRelationship();
            switch (relationship) {
                case 0:
                    holder.statue.setText("我关注的");
                    break;
                case 1:
                    holder.statue.setText("粉丝");
                    break;
                case 2:
                    holder.statue.setText("好友");
                    break;
            }


            return convertView;
        }

        public class ViewHolder {
            TextView username, statue, signature, age, gender;
            ImageView usericon;
        }
    }

}