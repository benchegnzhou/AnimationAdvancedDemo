package com.ztsc.house.network.api;

import com.ztsc.house.BuildConfig;
import com.ztsc.house.application.MApplication;
import com.ztsc.house.utils.DeviceMessageUtils;

import java.util.HashMap;



/**
 * Created by zbc on 2017/3/15.
 */

public class API {

    //服务主机
    public static final String SERVICE_HOST = BuildConfig.HOST_SERVICE;


    //用户注册获取验证码
    public static final String GETCODE_FOR_REGISTER = SERVICE_HOST;

    //用户注册校验验证码
    public static final String CHECKCODE_FOR_REGISTER = SERVICE_HOST;





    public static String getUserLoginByTokenUrl() {

        return SERVICE_HOST + "?service=user&function=tokenLogin";
    }

    /**
     * 获取用户验证码登录的url
     *
     * @return
     */
    public static String getGetCodeForLoginUrl() {
        return SERVICE_HOST + "?service=user&function=getCodeForLogin";
    }

    /**
     * 获取用户密码修改的url
     *
     * @return
     */
    public static String getChangePasswordUrl() {
        return SERVICE_HOST + "?service=user&function=updatePassWord";
    }

    /**
     * 获取用户头像修改的url
     *
     * @return
     */
    public static String getChangeUserIconUrl() {
        return SERVICE_HOST + "?service=user&function=updateHeadImage";
    }






    /**
     * 用户获取好友列表url
     *
     * @return
     */
    public static String getSearchFriendsUrl() {
        return SERVICE_HOST + "?service=friendService&function=myFriends";
    }

    /**
     * 用户获取好友列表url
     *
     * @return
     */
    public static String getGetFriendsListUrl() {
        return SERVICE_HOST + "?service=friendService&function=myFriends";
    }

    /**
     * 用户获取同小区好友url
     *
     * @return
     */
    public static String getGetSameTownFriendsUrl() {
        return SERVICE_HOST + "?service=friendService&function=lookPepoleByVillage";
    }
    /**
     * 根据手机号搜索好友url
     *
     * @return
     */
    public static String getSearchFriendsByPhoneUrl() {
        return SERVICE_HOST + "?service=user&function=findUserInfoByPhone";
    }
    /**
     * 根据userid搜索好友url
     *
     * @return
     */
    public static String getUserInformationUrl() {
        return SERVICE_HOST + "?service=friendService&function=lookUserAll";
    }

    /**
     * 用户获取活跃的好友url
     *
     * @return
     */
    public static String getGetActivFriendsUrl() {
        return SERVICE_HOST + "?service=friendService&function=lookRecentLoginUser";
    }

    /**
     * 用户添加好友url
     *
     * @return
     */
    public static String getAddFriendsUrl() {
        return SERVICE_HOST + "?service=friendService&function=addFriend";
    }

    /**
     * 用户删除好友url
     *
     * @return
     */
    public static String getDeleteFriendsUrl() {
        return SERVICE_HOST + "?service=friendService&function=deleteFriend";
    }




    /**
     * 获取用户修改信息的url
     *
     * @return
     */
    public static String getUserMessageChangeUrl() {
        return SERVICE_HOST + "?service=user&function=updateUser";
    }


    /**
     * 获取用户消息
     *
     * @return
     */
    public static String getMyMessageUrl() {
        return SERVICE_HOST + "?service=userMessageService&function=getUnreadMessage";
    }




    /**
     * 拼接公共的请求参数
     * 所有的公共参数都放在这里
     *
     * @return
     */
    public static HashMap<String, String> getCommonParams() {
        HashMap<String, String> objectHashMap = new HashMap<>();
        objectHashMap.put("machineId", DeviceMessageUtils.getIMEI(MApplication.sAppContext));
        objectHashMap.put("machineName", DeviceMessageUtils.getMobileInfo(MApplication.sAppContext));
        objectHashMap.put("clientType", "1");

        return objectHashMap;
    }

    /**
     * 地理定位，通过地理x,y坐标获取对应的POI
     *
     * @return
     */
    public static String getLoacatonPOIByXYUrl() {
        return "http://192.168.1.57:8080/MapServer/Service?server=RGeocode&distance=5000&page_num=10";
    }
    /**
     * 获取首页将GPS坐标转换物流坐标的url
     *
     * @return
     */
    public static String getGps2LoacatonUrl() {
        return "http://192.168.1.57:8080/MapServer/Service?server=Offset";
    }
    /**
     * GPS位置
     *
     * @return
     */
    public static String getUploadGPSLocationUrl() {
        return SERVICE_HOST + "?service=adPhotoService&function=upUserXY";
    }


    /**
     *
     *  用户激活校验验证码
     *
     * @return
     */
    public static String getUserRegistCheckCodeUrl() {
        return SERVICE_HOST + "?service=PropertyLogin&function=checkActivateCode";
    }

    /**
     *
     *  用户激活获取验证码
     *
     * @return
     */
    public static String getUserRegistGetCodeUrl() {
        return SERVICE_HOST + "?service=PropertyLogin&function=getCodeForActivate";
    }
    /**
     *
     *  用户激活设置密码
     *
     * @return
     */
    public static String getUserRegistSetPasswordUrl() {
        return SERVICE_HOST + "?service=PropertyLogin&function=setAndUpdatePassWord";
    }
    /**
     *
     *  用户使用验证码登录获取验证码
     *
     * @return
     */
    public static String getUserLoginCodeUrl() {
        return SERVICE_HOST + "?service=PropertyLogin&function=getCodeForLogin";
    }
    /**
     *
     *  用户使用验证码登录
     *
     * @return
     */
    public static String getUserLoginByCodeUrl() {
        return SERVICE_HOST + "?service=PropertyLogin&function=checkLoginCode";
    }

    /**
     *
     *  用户使用密码登录
     *
     * @return
     */
    public static String getUserLoginByPasswordUrl() {
        return SERVICE_HOST + "?service=PropertyLogin&function=phoneNumLogin";
    }

    /**
     * 用户签到获取令牌
     * @return
     */
    public static String getSignInTokenUrl() {
        return SERVICE_HOST+"?service=PropertySign&function=getSignToken";
    }

    /**
     * 用户签退
     * @return
     */
    public static String getSignOutUrl() {
        return SERVICE_HOST+"?service=PropertySign&function=getSignOut";
    }

    /**
     * 用户签到
     * @return
     */
    public static String getUserSignOutURL() {
        return SERVICE_HOST+"?service=PropertySign&function=getSignIn";
    }
}
