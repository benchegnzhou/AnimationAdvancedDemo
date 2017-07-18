package com.ztsc.house.application;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.leakcanary.LeakCanary;
import com.ztsc.house.huanxin.db.EaseUser;
import com.ztsc.house.huanxin.db.Myinfo;
import com.ztsc.house.huanxin.db.UserDao;
import com.ztsc.house.huanxin.utils.Constant;
import com.ztsc.house.utils.DeviceMessageUtils;
import com.zxy.tiny.Tiny;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


import okhttp3.OkHttpClient;

/**
 * 全局静态方法和变量的初始化类，用于初始全局的上下文，volley 的实体对象呢
 * 包过多的解决办法： 在这里  http://blog.csdn.net/gulihui890411/article/details/48551551
 */
public class MApplication extends android.support.multidex.MultiDexApplication {
    public static MApplication sApplication;
    public static Context sAppContext;
    public static OkHttpClient sOkHttpClient;


    //    环信
    private UserDao userDao;
    private String username = "";
    private Map<String, EaseUser> contactList;

    /**
     * 创建全局单例的对象
     *
     * @return
     */
    public static MApplication getInstance() {
        if (sApplication == null) {
            sApplication = new MApplication();
        }
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MApplication.getInstance();                //获取全局 MApplication 实体对象
        initEasenob();
        initOkGo();
        initPictureCompress();
        sAppContext = this;                             //获取全局上下文
        initImageLoader();
        SDKINIT();
        initLeakCanary();
    }


    /**
     * okgo使用初始化
     * 参考链接; https://github.com/jeasonlzy/okhttp-OkGo/wiki/Init
     */
    private void initOkGo() {

        HttpParams params = new HttpParams();

//        params.put("machineId", DeviceMessageUtils.getIMEI(MApplication.sAppContext));
   /*     params.put("machineName", DeviceMessageUtils.getMobileInfo(MApplication.sAppContext));
        params.put("clientType", "1");*/

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //配置控制台日志打印
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);


        //下面的代码是对okgo的配置

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(3600*30*1000)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数

    }

    /**
     * 图片压缩框架tiny的初始化
     */
    private void initPictureCompress() {
        Tiny.getInstance().debug(true).init(this);
    }

    /**
     * 参考链接：  https://www.liaohuqiu.net/cn/posts/leak-canary-read-me/
     * 内存检测工具
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }

    /**
     * 创建全局OkHttpClient对象
     * <p>
     * OkHttpClient 用于管理所有的请求，内部支持并发，
     * 所以我们不必每次请求都创建一个 OkHttpClient 对象，这是非常耗费资源的。接下来就是创建一个 Request 对象了
     *
     * @return
     */
    public static OkHttpClient getSOkHttpClient() {
        //创建okhttp的请求对象 参考地址  http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html


        if (sOkHttpClient == null) {
            sOkHttpClient =
                    new OkHttpClient.Builder()
                            .readTimeout(20000, TimeUnit.SECONDS)//设置读取超时时间
                            .writeTimeout(20000, TimeUnit.SECONDS)//设置写的超时时间
                            .connectTimeout(20000, TimeUnit.SECONDS)//设置连接超时时间
                            .build();
        }
        return sOkHttpClient;
    }


    /**
     * 三方sdk初始化
     */
    private void SDKINIT() {


        //日志调试工具的初始化
//        SmartToolCore.getInstance().init(this);


        //无论推送是否开启都需要调用此方法   友盟推送的初始化
       /* PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override       //通过设备的唯一标示识别用户身份   device token
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtil.e("myToken:----"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtil.e("myToken获取失败："+s + "---"+s1);
            }
        });*/


    }


    /**
     * 获取全局上下文
     *
     * @return
     */
    public Context getAppContext() {
        if (sAppContext == null) {
            sAppContext = this;
        }
        return sAppContext;
    }

    private void initEasenob() {
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);
        options.setAutoLogin(false);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
// 如果APP启用了远程的service，此application:onCreate会被调用2次
// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
// 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e("TAG", "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMClient.getInstance().init(this, options);
        EMClient.getInstance().setDebugMode(true);
    }

    public void setCurrentUserName(String username) {
        this.username = username;
        Myinfo.getInstance(sApplication).setUserInfo(Constant.KEY_USERNAME, username);
    }

    public String getCurrentUserName() {
        if (TextUtils.isEmpty(username)) {
            username = Myinfo.getInstance(sApplication).getUserInfo(Constant.KEY_USERNAME);

        }
        return username;
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    public Map<String, EaseUser> getContactList() {

        if (contactList == null) {
            contactList = userDao.getContactList();
        }
        return contactList;
    }

    public void setContactList(Map<String, EaseUser> contactList) {
        this.contactList = contactList;
        userDao.saveContactList(new ArrayList<EaseUser>(contactList.values()));

    }

    /**
     * 获取手机的唯一编码
     *
     * @return
     */
    public static String getDevice_Id() {
        TelephonyManager tm = (TelephonyManager) getInstance().getSystemService(getInstance().TELEPHONY_SERVICE);

        return tm.getDeviceId().toString();
    }
}
