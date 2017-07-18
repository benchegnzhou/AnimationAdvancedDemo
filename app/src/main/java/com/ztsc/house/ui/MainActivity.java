package com.ztsc.house.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.ztsc.house.BaseSlidingMenuActivity;
import com.ztsc.house.Class.City;
import com.ztsc.house.R;
import com.ztsc.house.bean.HomeMenuBean;
import com.ztsc.house.bean.loacation.Gps2LoacatonBeen;
import com.ztsc.house.bean.loacation.LocationSelectionBeen;
import com.ztsc.house.customview.NoScrollAndShowAllGridView;
import com.ztsc.house.data.GlobalData;
import com.ztsc.house.eventbusbody.ChangeGps2LocationEvent;
import com.ztsc.house.eventbusbody.SelectLocationEvent;
import com.ztsc.house.eventbusbody.ZTAnyEventType;
import com.ztsc.house.fragment.leftfragment.LeftFragment;
import com.ztsc.house.model.Convert;
import com.ztsc.house.network.api.doParams.ZTHouseHttpClient;
import com.ztsc.house.service.LocationService;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Headers;
import pub.devrel.easypermissions.PermissionCallBackM;


public class MainActivity extends BaseSlidingMenuActivity {


    private static final int REQUEST_CODE_READ_PHONE_STATE = 200;
    @Bind(R.id.iv_head_icon)
    ImageView ivHeadIcon;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.btn_regist)
    Button btnRegist;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_sign)
    Button btnSign;
    @Bind(R.id.fragmentPager)
    AutoFrameLayout fragmentPager;
    @Bind(R.id.activity_main)
    AutoLinearLayout activityMain;
    @Bind(R.id.rv_menu)
    NoScrollAndShowAllGridView rvMenu;
    private List<HomeMenuBean> menuList = new ArrayList<>();
    
    private LocationManager locationManager;
    private AlertDialog.Builder cityChangeDialog;
    private boolean isChangeCityDialogShowFrist = true;
    private MenuAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initMenuList();

    }

    private void initMenuList() {
        menuList.add(new HomeMenuBean("待办", R.drawable.home_pending_items));
        menuList.add(new HomeMenuBean("工作日志", R.drawable.home_dailylog));
        menuList.add(new HomeMenuBean("消息", R.drawable.home_message));
        menuList.add(new HomeMenuBean("签到", R.drawable.home_sign));
        menuList.add(new HomeMenuBean("请假", R.drawable.home_askforleave));
        menuList.add(new HomeMenuBean("员工动态", R.drawable.home_worker_dynamics));
        menuList.add(new HomeMenuBean("月度考勤统计", R.drawable.home_monthstatistics));
        menuList.add(new HomeMenuBean("今日考勤统计", R.drawable.home_daystatistics));
        menuList.add(new HomeMenuBean("公共报事", R.drawable.home_public_report));
        menuList.add(new HomeMenuBean("投诉", R.drawable.home_complaint));
        menuList.add(new HomeMenuBean("表扬", R.drawable.home_praise));
        menuList.add(new HomeMenuBean("来访统计", R.drawable.home_come_to_visit));
        menuList.add(new HomeMenuBean("物资进出统计", R.drawable.home_material_import_and_export_statistics));
        menuList.add(new HomeMenuBean("车辆出入统计", R.drawable.home_traffic_statistics));

        mAdapter = new MenuAdapter();
        rvMenu.setAdapter(mAdapter);

        rvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void initListener() {
        btnRegist.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSign.setOnClickListener(this);

        //这一句话一定添加在所有的网络请求以前
        phoneStatusTask();
//        initSlidingMenu(savedInstanceState);
        initSlidingMenu(null);
//        initialization();
//        setButtonStatus(0);
    }

    @Override
    protected void initData() {
        signIn();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    /*环信的登录操作*/
    private void signIn() {
        if (UserInformationManager.getInstance().getUserIsLogin()) {
            String huanxinUserName = UserInformationManager.getInstance().getHuanxinUserName();
            String huanxinUserpassword = UserInformationManager.getInstance().getHuanxinUserpassword();
            if (TextUtils.isEmpty(huanxinUserName) || TextUtils.isEmpty(huanxinUserpassword)) {
                return;
            }
            EMClient.getInstance().login(huanxinUserName, huanxinUserpassword, new EMCallBack() {
                @Override
                public void onSuccess() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.i("aaabb", "欢迎页登录成功");
                }

                @Override
                public void onError(int i, String s) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                        Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.i("aaabb", "环信用户名或密码错误");
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        } else {
            Log.i("aaabb", "下回启动登录环信");
        }
    }

    /**
     * 初始化侧边栏
     */
    private void initSlidingMenu(Bundle savedInstanceState) {
        // 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment
       /* if (savedInstanceState != null) {
            mContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, "mContent");
        }

        if (mContent == null) {
            mContent = new TodayFragment();
        }*/

        // 设置左侧滑动菜单
        setBehindContentView(R.layout.menu_frame_left);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_frame, new LeftFragment()).commit();

        // 实例化滑动菜单对象
        SlidingMenu sm = getSlidingMenu();
        // 设置可以左右滑动的菜单
        sm.setMode(SlidingMenu.LEFT);
        // 设置滑动阴影的宽度
        sm.setShadowWidthRes(R.dimen.shadow_width);
        // 设置滑动菜单阴影的图像资源
        sm.setShadowDrawable(null);
        // 设置滑动菜单视图的宽度
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        sm.setFadeDegree(0.39f);
        // 设置触摸屏幕的模式,这里设置为全屏
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置下方视图的在滚动时的缩放比例
        sm.setBehindScrollScale(0.2f);

    }


    //    @Override
    public void onClick(View view) {

        switch (view.getId()) {


        }
    }


    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 色像头权限检测
     */
    public void phoneStatusTask() {
        requestPermission(REQUEST_CODE_READ_PHONE_STATE, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION},
                getString(R.string.rationale_phone_status), new PermissionCallBackM() {
                    @Override
                    public void onPermissionGrantedM(int requestCode, String... perms) {
                        switch (requestCode) {
                            case REQUEST_CODE_READ_PHONE_STATE:
                                for (int i = 0; i < perms.length; i++) {
                                    String perm = perms[0];
                                    LogUtil.e("权限状态打印" + perms[i]);
                                    if ("android.permission.ACCESS_COARSE_LOCATION".equals(perms[i])) {
                                        //执行定位，提示用户改变自己当前的城市
                                        initLocation();
                                    }
                                }

                                break;

                        }
                    }


                    @Override
                    public void onPermissionDeniedM(int requestCode, String... perms) {
                        onPermissionDenied(requestCode, perms);
                    }
                });
    }

    /**
     * 执行定位操作
     */
    private void doLocation() {
        bindService(new Intent(this, LocationService.class), conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //一定要解绑，解决内存泄漏
        unbindService(conn);
    }

    public void onPermissionDenied(int requestCode, String... perms) {
        ToastUtils.showToastShort("onPermissionDenied:权限被拒绝，好残忍" + requestCode + ":" +
                "" + perms.length);
        for (int i = 0; i < perms.length; i++) {
            LogUtil.e(perms[i]);
        }
    }

    ServiceConnection conn = new ServiceConnection() {

        private LocationService mLocationService;

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.e("断开链接：onServiceDisconnected");
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
                            ToastUtils.showDebugToastLong("lastLatitude: " + lastLatitude +
                                    "\nlastLongitude: " + lastLongitude +
                                    "\nlatitude: " + latitude +
                                    "\nlongitude: " + longitude +
                                    "\ngetCountryName: " + country +
                                    "\ngetLocality: " + locality +
                                    "\ngetStreet: " + street
                            );
                            LogUtil.e("lastLatitude: " + lastLatitude +
                                    "\nlastLongitude: " + lastLongitude +
                                    "\nlatitude: " + latitude +
                                    "\nlongitude: " + longitude +
                                    "\ngetCountryName: " + country +
                                    "\ngetLocality: " + locality +
                                    "\ngetStreet: " + street);
                        }
                    });
                }
            });
        }
    };
    private String locationProvider = LocationManager.GPS_PROVIDER;

    /*
    * 缓存GPS坐标，并执行坐标偏转
     */
    private void cacheGPSLocation(Location location) {

        ToastUtils.showToastLong("定位结果：\n经度：" + location.getLongitude() + "\n纬度：" + location.getLatitude() + "\n海拔：" + location.getAltitude());

        //将定位信息保存本地
        GlobalData.setCurrentGpsX(location.getLongitude());  //经度
        GlobalData.setCurrentGpsY(location.getLatitude());  //纬度
        GlobalData.saveGlobalData();

        //执行网络请求,将GPS坐标偏转为火星坐标
        ZTHouseHttpClient.doChangeGPS2Map(location.getLongitude(), location.getLatitude());
    }


    /***
     * 定位x y 坐标坐标
     */
    private void initLocation() {
        //获取地理位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);

        String realLocationProvider = "";

        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
            realLocationProvider = LocationManager.GPS_PROVIDER;
            //监视地理位置变化
            locationManager.requestLocationUpdates(realLocationProvider, 3000, 1, locationListener);
        }

        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            realLocationProvider = LocationManager.NETWORK_PROVIDER;
            //监视地理位置变化
            locationManager.requestLocationUpdates(realLocationProvider, 3000, 1, locationListener);
        }

        if (realLocationProvider.isEmpty()) {
            ToastUtils.showToastShort("没有可用的位置提供器");

        }
        if (locationProvider != null) {
            //获取Location

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ToastUtils.showToastLong("定位权限未获取，请在设置中开启。。");
                return;
            }
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {
                cacheGPSLocation(location);
            }
        }
    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {
            LogUtil.e("定位  onStatusChanged");
        }

        @Override
        public void onProviderEnabled(String provider) {
            LogUtil.e("定位  onProviderEnabled");
        }

        @Override
        public void onProviderDisabled(String provider) {
            LogUtil.e("定位  onProviderDisabled");
        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            LogUtil.e("时间：" + location.getTime());
            LogUtil.e("经度：" + location.getLongitude());
            LogUtil.e("纬度：" + location.getLatitude());
            LogUtil.e("海拔：" + location.getAltitude());

            Double x = location.getLongitude();
            Double y = location.getLatitude();
            ZTHouseHttpClient.doUploadGPSLocation(UserInformationManager.getInstance().getUserId(), UserInformationManager.getInstance().getToken(), x.toString(), y.toString());

            cacheGPSLocation(location);
        }
    };


    /**
     * 当前定位坐标转换成地理坐标的结果
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangeGps2LocationEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            Gps2LoacatonBeen gps2LoacatonBeen = event.parseResult();
            LogUtil.e(gps2LoacatonBeen.toString());
            String locationX = gps2LoacatonBeen.getX();
            String locationY = gps2LoacatonBeen.getY();
            GlobalData.setCurrentLocationX(Double.parseDouble(locationX));
            GlobalData.setCurrentLocationY(Double.parseDouble(locationY));
            ZTHouseHttpClient.doGetTheXYPoi(locationX, locationY);
            //存储结果
            GlobalData.saveGlobalData();
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    /**
     * 获取当前定位城市
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SelectLocationEvent event) {
        LogUtil.e(event.getCode() + "-------" + event.getResult());
        if (event.getCode() == ZTAnyEventType.SUCCESS) {
            LocationSelectionBeen selectionBeen = event.parseResult();
            LogUtil.e(selectionBeen.toString());
            String locationCityName = "";

            LocationSelectionBeen.ListBean.ProvinceBean province = selectionBeen.getList().get(0).getProvince();
            LocationSelectionBeen.ListBean.CityBean city = selectionBeen.getList().get(0).getCity();
            LocationSelectionBeen.ListBean.DistrictBean district = selectionBeen.getList().get(0).getDistrict();

            if (province.getName().equals("北京市") || province.getName().equals("天津市") || province.getName().equals("上海市") || province.getName().equals("重庆市")) {
                locationCityName = province.getName();
            } else {
                locationCityName = city.getName();
            }

            String currentCityNameSelected = GlobalData.selected_city.getName();


            if (TextUtils.isEmpty(currentCityNameSelected) || (!currentCityNameSelected.equals(locationCityName))) {
                LogUtil.d("选择的城市： " + currentCityNameSelected + ", 定位的城市： " + locationCityName);
                showChangeCityDialog(locationCityName);
            } else {
                ToastUtils.showToastShort("定位城市和当前选择城市一样，不用切换了，这只是个测试！！");
            }
        } else {
            ToastUtils.showToastShort("网络不通，请稍后重试");
        }
    }

    /**
     * 切换城市的对话框，对首页和周边模块的城市进行切换
     *
     * @param locationCity
     */
    private void showChangeCityDialog(final String locationCity) {

        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        if (cityChangeDialog == null) {
            cityChangeDialog = new AlertDialog.Builder(MainActivity.this);
        }
        if (!isChangeCityDialogShowFrist || TextUtils.isEmpty(locationCity.trim())) {  //尽在第一次显示
            return;
        }
        isChangeCityDialogShowFrist = false;
        cityChangeDialog.setTitle("提示");
        cityChangeDialog.setMessage("系统定位到您在" + locationCity + ",需要切换到" + locationCity + "吗？");
        cityChangeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        ToastUtils.showToastShort("城市切换中..");

                        City location_city = GlobalData.location_city;
                        GlobalData.selected_city.setName(location_city.getName());
                        GlobalData.selected_city.setX(location_city.getX());
                        GlobalData.selected_city.setY(location_city.getY());

                        GlobalData.saveGlobalData();
                    }
                });
        cityChangeDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        ToastUtils.showToastShort("已取消..");
                    }
                });
        // 显示

        cityChangeDialog.show();

    }


    protected <T> void handleResponse(Response<T> response) {
        StringBuilder sb;
        Call call = response.getRawCall();
        if (call != null) {
            LogUtil.e("请求成功  请求方式：" + call.request().method() + "\n" + "url：" + call.request().url());

            Headers requestHeadersString = call.request().headers();
            Set<String> requestNames = requestHeadersString.names();
            sb = new StringBuilder();
            for (String name : requestNames) {
                sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
            }
            LogUtil.e("我是请求头：--------" + sb.toString());
        } else {
            LogUtil.e("空数据---");
        }
        T body = response.body();
        if (body == null) {
            LogUtil.e("空数据---");
        } else {
            if (body instanceof String) {
                LogUtil.e("数据---(String) body-----" + (String) body);
            } else if (body instanceof List) {
                sb = new StringBuilder();
                List list = (List) body;
                for (Object obj : list) {
                    sb.append(obj.toString()).append("\n");
                }
                LogUtil.e("数据---sb.toString()-----" + sb.toString());

            } else if (body instanceof Set) {
                sb = new StringBuilder();
                Set set = (Set) body;
                for (Object obj : set) {
                    sb.append(obj.toString()).append("\n");
                }
                LogUtil.e("数据---sb.toString()-----" + sb.toString());

            } else if (body instanceof Map) {
                sb = new StringBuilder();
                Map map = (Map) body;
                Set keySet = map.keySet();
                for (Object key : keySet) {
                    sb.append(key.toString()).append(" ： ").append(map.get(key)).append("\n");
                }
                LogUtil.e("数据---sb.toString()-----" + sb.toString());

            } else if (body instanceof File) {
                File file = (File) body;
                LogUtil.e("数据-- 数据内容即为文件内容\n下载文件路径：" + file.getAbsolutePath());

            } else if (body instanceof Bitmap) {
                LogUtil.e("图片的内容即为数据");


                LogUtil.e("图片的内容即为数据");
            } else {

                LogUtil.e("图片的内容即为数据" + Convert.formatJson(body));
            }
            okhttp3.Response rawResponse = response.getRawResponse();
            if (rawResponse != null) {
                Headers responseHeadersString = rawResponse.headers();
                Set<String> names = responseHeadersString.names();
                sb = new StringBuilder();
                sb.append("url ： ").append(rawResponse.request().url()).append("\n\n");
                sb.append("stateCode ： ").append(rawResponse.code()).append("\n");
                for (String name : names) {
                    sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
                }
                LogUtil.e("收到数据" + sb.toString());

            } else {

                LogUtil.e("----------");
            }
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }

        return false;

    }


    /**
     * 切换Fragment
     *
     * @param fragment
     */
    public void switchConent(Fragment fragment, String title) {
     /*   mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        getSlidingMenu().showContent();
        topTextView.setText(title);*/
    }

    public class MenuAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return menuList.size();
        }

        @Override
        public Object getItem(int position) {
            return menuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_home_menu, null);
                holder.menuIcon = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
                holder.menuText = (TextView) convertView.findViewById(R.id.tv_menu_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            HomeMenuBean homeMenuBean = menuList.get(position);
            Picasso.with(MainActivity.this).load(homeMenuBean.getItemIcon()).into(holder.menuIcon);
            holder.menuText.setText(homeMenuBean.getClassifyName());
            return convertView;
        }

        public class ViewHolder {
            private ImageView menuIcon;
            private TextView menuText;
        }
    }
}
