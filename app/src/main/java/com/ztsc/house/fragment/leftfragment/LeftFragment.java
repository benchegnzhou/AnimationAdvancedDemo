package com.ztsc.house.fragment.leftfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoRelativeLayout;
import com.ztsc.house.R;
import com.ztsc.house.customview.CircleImageView;
import com.ztsc.house.meui.SetMeActivity;
import com.ztsc.house.meui.SettingActivity;
import com.ztsc.house.ui.LoginActivity;
import com.ztsc.house.ui.MainActivity;
import com.ztsc.house.ui.SignActivity;
import com.ztsc.house.ui.TestOverScrollViewActivity;
import com.ztsc.house.usersetting.UserInformationManager;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author wuwenjie
 * @date 2014/11/14
 * @description 侧边栏菜单
 */
public class LeftFragment extends Fragment implements OnClickListener {
    @Bind(R.id.profile_image)
    CircleImageView profileImage;
    @Bind(R.id.rl_user_photo)
    AutoRelativeLayout rlUserPhoto;
    @Bind(R.id.tvToday)
    TextView tvToday;
    @Bind(R.id.tvLastlist)
    TextView tvLastlist;
    @Bind(R.id.tvDiscussMeeting)
    TextView tvDiscussMeeting;
    @Bind(R.id.tvMyFavorites)
    TextView tvMyFavorites;
    @Bind(R.id.tvMyComments)
    TextView tvMyComments;
    @Bind(R.id.tvMySettings)
    TextView tvMySettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_left_menu, null);
        ButterKnife.bind(this, view);
        initListener();
        return view;
    }


    public void initListener( ) {

        tvToday.setOnClickListener(this);
        tvLastlist.setOnClickListener(this);
        tvDiscussMeeting.setOnClickListener(this);
        tvMyFavorites.setOnClickListener(this);
        tvMyComments.setOnClickListener(this);
        tvMySettings.setOnClickListener(this);
        rlUserPhoto.setOnClickListener(this);
//        tvSetting.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_user_photo:
                if (UserInformationManager.getInstance().getUserIsLogin()) {
                    Intent intent = new Intent(getActivity(), SetMeActivity.class);
                    intent.putExtra("from", "me_fragment");
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.tvMySettings:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tvDiscussMeeting:
                startActivity(new Intent(getActivity(), TestOverScrollViewActivity.class));
                break;


            case R.id.tvMyComments:
                startActivity(new Intent(getActivity(), SignActivity.class));
                break;

        }




	/*	Fragment newContent = null;
        String title = null;
		switch (v.getId()) {
		case R.id.tvToday: // 今日
			newContent = new TodayFragment();
			title = getString(R.string.today);
			break;
		case R.id.tvLastlist:// 往期列表
			newContent = new LastListFragment();
			title = getString(R.string.lastList);
			break;
		case R.id.tvDiscussMeeting: // 讨论集会
			newContent = new DiscussFragment();
			title = getString(R.string.discussMeetting);
			break;
		case R.id.tvMyFavorites: // 我的收藏
			newContent = new MyFavoritesFragment();
			title = getString(R.string.myFavorities);
			break;
		case R.id.tvMyComments: // 我的评论
			newContent = new MyCommentsFragment();
			title = getString(R.string.myComments);
			break;
		case R.id.tvMySettings: // 设置
			newContent = new MySettingsFragment();
			title = getString(R.string.settings);
			break;
		default:
			break;
		}
		if (newContent != null) {
			switchFragment(newContent, title);
		}*/
    }

    /**
     * 切换fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment, String title) {
        if (getActivity() == null) {
            return;
        }
        if (getActivity() instanceof MainActivity) {
            MainActivity fca = (MainActivity) getActivity();
            fca.switchConent(fragment, title);
        }
    }

}
