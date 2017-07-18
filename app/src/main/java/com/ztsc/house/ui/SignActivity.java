package com.ztsc.house.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.bean.usersignbean.UserSignGetTokenResponse;
import com.ztsc.house.bean.usersignbean.UserSignInBean;
import com.ztsc.house.bean.usersignbean.UserSignoutBean;
import com.ztsc.house.callback.JsonCallback;
import com.ztsc.house.network.api.API;
import com.ztsc.house.service.LocationService;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignActivity extends BaseActivity {

    //签到
    private static final int SIGNIN = 0;
    private static final int SIGNOUT = 1;
    @Bind(R.id.btn_sign_in)
    Button btnSingIn;
    @Bind(R.id.btn_sign_out)
    Button btnSingOut;
    @Bind(R.id.tv_infomation)
    TextView tvInfomation;
    private String userId;
    private boolean isInitCache;
    private String mTokien;
    private String workPlanId;
    private String workPlanName;
    private LocationService mLocationService;
    private String gpsX;
    private String gpsY;
    private String locationX;
    private String locationY;
    private String locationName;


    @Override
    public int getContentView() {
        return R.layout.activity_sign;
    }

    @Override
    protected void initListener() {
        btnSingIn.setOnClickListener(this);
        btnSingOut.setOnClickListener(this);
        bindService(new Intent(this, LocationService.class), conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void initData() {
        userId = UserInformationManager.getInstance().getUserId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                if (checkParamsIsPull()) {
                    return;
                }
                getToken4Signin(SIGNIN);
                break;
            case R.id.btn_sign_out:
                if (checkParamsIsPull()) {
                    return;
                }
                getToken4Signin(SIGNOUT);
                break;
        }
    }

    /**
     * 参数空判断
     *
     * @return
     */
    private boolean checkParamsIsPull() {
        if (TextUtils.isEmpty(gpsX ) || TextUtils.isEmpty(gpsY) ||
                TextUtils.isEmpty(locationX )|| TextUtils.isEmpty(locationY )
                || TextUtils.isEmpty(locationName)) {
            ToastUtils.showToastShort("定位失败请检查位置服务是否开启！");
            return true;
        }

        return false;
    }

    /**
     * 获取令牌签到
     * <p>
     * 每次执行签到和签退操作都需要重新获取验证码
     */
    private void getToken4Signin(final int signIn) {
        if (TextUtils.isEmpty(userId)) {
            ToastUtils.showToastShort("用户未登录");
            return;
        }
        OkGo.<UserSignGetTokenResponse>post(API.getSignInTokenUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .cacheKey("asdjjsad")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(3600 * 1000)  //毫秒
                .params("userId", userId)//
                .execute(new JsonCallback<UserSignGetTokenResponse>(UserSignGetTokenResponse.class) {

                             @Override
                             public void onSuccess(Response<UserSignGetTokenResponse> response) {
                                 UserSignGetTokenResponse body = response.body();
                                 if (body.getCode() != 200) {
                                     ToastUtils.showToastShort("参数错误：\n" + body.getMessage());
                                     return;
                                 }
                                 if (body.getResult().getStatus() != 0) {
                                     ToastUtils.showToastShort("错误信息：\n" + body.getResult().getInformation());
                                     return;
                                 }
                                 mTokien = body.getResult().getToken();
                                 workPlanId = body.getResult().getWorkPlanId();
                                 workPlanName = body.getResult().getWorkPlanName();
                                 if (signIn == SIGNIN) {
                                     signIn();
                                 } else {
                                     signout();
                                 }

                             }

                             @Override
                             public void onCacheSuccess(Response<UserSignGetTokenResponse> response) {
                                 //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
                                 if (!isInitCache) {
                                     //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                                     onSuccess(response);
                                     isInitCache = true;
                                 }
                                 LogUtil.e(response.body().getResult().getInformation());
//                                         super.onCacheSuccess(response);
                             }

                             @Override
                             public void onError(Response<UserSignGetTokenResponse> response) {
                                 ToastUtils.showToastShort("服务君开小差了，请稍后再试");
                             }
                         }
                );
    }

    /**
     * 用户签退操作
     */
    private void signout() {
        OkGo.<UserSignoutBean>post(API.getSignOutUrl())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .cacheKey("asdjjsad")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(3600 * 1000)  //毫秒
                .params("userId", userId)//
                .params("workPlanId", "1")//
                .params("workPlanName", "1")//
                .params("token", mTokien)//
                .params("gpsX", gpsX)//
                .params("gpsY", gpsY)//
                .params("locationX", locationX)//
                .params("locationY", locationY)//
                .params("locationName", locationName)//
                .execute(new JsonCallback<UserSignoutBean>(UserSignoutBean.class) {

                             @Override
                             public void onSuccess(Response<UserSignoutBean> response) {
                                 UserSignoutBean body = response.body();
                                 if (body.getCode() != 200) {
                                     ToastUtils.showToastShort("参数错误：\n" + body.getMessage());
                                     return;
                                 }
                                 if (body.getResult().getStatus() != 0) {
                                     ToastUtils.showToastShort("错误信息：\n" + body.getResult().getInformation());
                                     return;
                                 }
                                 ToastUtils.showToastLong("签退成功");

                             }

                             @Override
                             public void onCacheSuccess(Response<UserSignoutBean> response) {
                                 //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
                                 if (!isInitCache) {
                                     //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                                     onSuccess(response);
                                     isInitCache = true;
                                 }
                                 LogUtil.e(response.body().getResult().getInformation());
//                                         super.onCacheSuccess(response);
                             }

                             @Override
                             public void onError(Response<UserSignoutBean> response) {
                                 ToastUtils.showToastShort("服务君开小差了，请稍后再试");
                             }
                         }
                );
    }

    /**
     * 用户签到
     */
    private void signIn() {
        OkGo.<UserSignInBean>post(API.getUserSignOutURL())//
                .tag(this)//
//                        .upString("这是要上传的长文本数据！")//
                .cacheKey("asdjjsad")
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .cacheTime(3600 * 1000)  //毫秒
                .params("userId", userId)//
                .params("workPlanId", "1")//
                .params("workPlanName", "1")//
                .params("token", mTokien)//
                .params("gpsX", gpsX)//
                .params("gpsY", gpsY)//
                .params("locationX", locationX)//
                .params("locationY", locationY)//
                .params("locationName", locationName)//
                .execute(new JsonCallback<UserSignInBean>(UserSignInBean.class) {

                             @Override
                             public void onSuccess(Response<UserSignInBean> response) {
                                 UserSignInBean body = response.body();
                                 if (body.getCode() != 200) {
                                     ToastUtils.showToastShort("参数错误：\n" + body.getMessage());
                                     return;
                                 }
                                 if (body.getResult().getStatus() != 0) {
                                     ToastUtils.showToastShort("错误信息：\n" + body.getResult().getInformation());
                                     return;
                                 }
                                 ToastUtils.showToastLong("签退成功");

                             }

                             @Override
                             public void onCacheSuccess(Response<UserSignInBean> response) {
                                 //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
                                 if (!isInitCache) {
                                     //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                                     onSuccess(response);
                                     isInitCache = true;
                                 }
                                 LogUtil.e(response.body().getResult().getInformation());
//                                         super.onCacheSuccess(response);
                             }

                             @Override
                             public void onError(Response<UserSignInBean> response) {
                                 ToastUtils.showToastShort("服务君开小差了，请稍后再试");
                             }
                         }
                );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束时一定要解除绑定
        unbindService(conn);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLocationService = ((LocationService.LocationBinder) service).getService();
            mLocationService.setOnGetLocationListener(new LocationService.OnGetLocationListener() {
                @Override
                public void getLocation(final String lastLatitude, final String lastLongitude, final String latitude, final String longitude, final String country, final String locality, final String street) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("lastLatitude: " + lastLatitude +
                                    "\nlastLongitude: " + lastLongitude +
                                    "\nlatitude: " + latitude +
                                    "\nlongitude: " + longitude +
                                    "\ngetCountryName: " + country +
                                    "\ngetLocality: " + locality +
                                    "\ngetStreet: " + street
                            );
                            tvInfomation.setText("lastLatitude: " + lastLatitude +
                                    "\nlastLongitude: " + lastLongitude +
                                    "\nlatitude: " + latitude +
                                    "\nlongitude: " + longitude +
                                    "\ngetCountryName: " + country +
                                    "\ngetLocality: " + locality +
                                    "\ngetStreet: " + street);

                            if (TextUtils.isEmpty(lastLatitude) || TextUtils.isEmpty(lastLongitude) ||
                                    TextUtils.isEmpty(locality) || TextUtils.isEmpty(street)) {
                                ToastUtils.showToastShort("定位失败请检查位置服务是否开启！");
                                tvInfomation.setText("定位失败请检查位置服务是否开启！");
                                return;
                            }

                            gpsX =  lastLongitude;  //纬度
                            gpsY = lastLatitude;
                            locationX =longitude;
                            locationY =  latitude;
                             locationName=street;

                        }
                    });
                }
            });
        }
    };
}
