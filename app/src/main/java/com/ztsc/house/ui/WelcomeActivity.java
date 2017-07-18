package com.ztsc.house.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.ztsc.house.BaseActivity;
import com.ztsc.house.R;
import com.ztsc.house.usersetting.UserInformationManager;
import com.ztsc.house.utils.FileUtils;
import com.ztsc.house.utils.LogUtil;
import com.ztsc.house.utils.ToastUtils;



import pub.devrel.easypermissions.PermissionCallBackM;

public class WelcomeActivity extends BaseActivity {
    public static final int GO_GUIDE = 0;
    public static final int GO_MAIN = 1;
    private static final int REQUEST_CODE_READ_PHONE_STATE = 200;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //跳转到主页或登录
                case GO_MAIN:
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    WelcomeActivity.this.finish();
                    break;
                //跳转到导航页
                case GO_GUIDE:
                    Intent intent1 = new Intent(WelcomeActivity.this, GuideActivity.class);
                    WelcomeActivity.this.startActivity(intent1);
                    finish();
                    break;
            }
        }
    };
    private boolean userFristRun;

    @Override
    public void othersInit() {
        super.othersInit();
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
//        ToastUtils.showToastShort("nani");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (UserInformationManager.getInstance().getUserIsLogin()) {
//            signIn();
        } else {
            ToastUtils.showToastShort("用户登录以后登录环信");
        }
        loading();
        writeSDTask();
    }

    /**
     * 执行资源加载和拷贝文件
     */
    private void copyData() {
        try {
            FileUtils.copyAssetsDataToSD("city.zt_life",FileUtils.getSDCardPath4Setting(this)+"/city.zt");
            ToastUtils.showToastShort("资源初始化完成...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_welcome;
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
                    Log.i("aaabb", "欢迎页用户名或密码错误");
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        } else {
            Log.i("aaabb", "下回启动登录环信");
        }
    }

    private void loading() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                  /*  SharedPreferences sharedPreferences = getSharedPreferences("firstrun", MODE_PRIVATE);

                    boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();*/
                    userFristRun = !UserInformationManager.getInstance().getUserNotFristRun();
                    if (userFristRun) {
                        UserInformationManager.getInstance().setUserNotFristRun(true);
                        handler.sendEmptyMessage(WelcomeActivity.GO_GUIDE);
                    } else {
                        handler.sendEmptyMessage(WelcomeActivity.GO_MAIN);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


    /**
     * 色像头权限检测
     */
    public void writeSDTask() {
        requestPermission(REQUEST_CODE_READ_PHONE_STATE, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                getString(R.string.rationale_phone_status), new PermissionCallBackM() {
                    @Override
                    public void onPermissionGrantedM(int requestCode, String... perms) {
                        switch (requestCode) {
                            case REQUEST_CODE_READ_PHONE_STATE:
                                for (int i = 0; i < perms.length; i++) {
                                    String perm = perms[0];
                                    LogUtil.e("权限状态打印" + perms[i]);
                                    if("android.permission.WRITE_EXTERNAL_STORAGE".equals(perms[i])/*&&(!userFristRun)*/){   //检测到读写sd卡权限的时候执行这里
                                        copyData();
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
     * 权限失败统一回调
     * @param requestCode
     * @param perms
     */

    public void onPermissionDenied(int requestCode, String... perms) {
        ToastUtils.showToastShort("onPermissionDenied:权限被拒绝，好残忍" + requestCode + ":" +
                "" + perms.length);
        for (int i = 0; i < perms.length; i++) {
            LogUtil.e(perms[i]);

        }
    }



    @Override
    public void onClick(View view) {

    }
}
