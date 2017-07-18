package com.ztsc.house.network.api.doParams;

import com.ztsc.house.eventbusbody.AddFriendsEvent;
import com.ztsc.house.eventbusbody.ChangeGps2LocationEvent;
import com.ztsc.house.eventbusbody.DeleteFriendsEvent;
import com.ztsc.house.eventbusbody.GetActiveFriendsListEvent;
import com.ztsc.house.eventbusbody.GetFriendsListEvent;
import com.ztsc.house.eventbusbody.MeMyMessageEvent;
import com.ztsc.house.eventbusbody.SearchFriendsByPhoneEvent;
import com.ztsc.house.eventbusbody.SearchFriendsEvent;
import com.ztsc.house.eventbusbody.SelectLocationEvent;
import com.ztsc.house.eventbusbody.TestEvent;
import com.ztsc.house.eventbusbody.UploadGPSLocationEvent;
import com.ztsc.house.eventbusbody.UserChangePasswordEvent;
import com.ztsc.house.eventbusbody.UserIconChangeEvent;
import com.ztsc.house.eventbusbody.UserInformationEvent;
import com.ztsc.house.eventbusbody.UserLoginByCodeEvent;
import com.ztsc.house.eventbusbody.UserLoginByPasswordEvent;
import com.ztsc.house.eventbusbody.UserLoginByTokenEvent;
import com.ztsc.house.eventbusbody.UserLoginGetCodeEvent;
import com.ztsc.house.eventbusbody.UserMessageChangeEvent;
import com.ztsc.house.network.api.API;
import com.ztsc.house.network.api.net.PostKeyVule;

import java.util.HashMap;


/**
 * Created by zbc on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public class ZTHouseHttpClient {

    //    密码登录
    public static void doLoginByPassword(String phoneNum, String passWord) {
        String url =  API.getUserLoginByPasswordUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("phoneNum", phoneNum);
        params.put("passWord", passWord);

       PostKeyVule.doPost(url, params, new UserLoginByPasswordEvent());
    }

    //    验证码登录校验验证码（登录）
    public static void doLoginByCode(String phoneNum, String code) {
        String url = API.getUserLoginByCodeUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("phoneNum", phoneNum);
        params.put("code", code);
        PostKeyVule.doPost(url, params, new UserLoginByCodeEvent());
    }

    //令牌登录
    public static void doLoginByToken(String token) {
        String url = API.getUserLoginByTokenUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("token", token);
        PostKeyVule.doPost(url, params, new UserLoginByTokenEvent());
    }

    //  验证码登录获取验证码
    public static void doLoginGetCode(String phoneNum) {
        String url = API.getGetCodeForLoginUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("phoneNum", phoneNum);
        PostKeyVule.doPost(url, params, new UserLoginGetCodeEvent());
    }

    //  用户修改密码
    public static void doUserChangePassword(String phoneNum, String passWord, String token) {
        String url = API.getChangePasswordUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("phoneNum", phoneNum);
        params.put("newPassWord", passWord);
        params.put("token", token);
        PostKeyVule.doPost(url, params, new UserChangePasswordEvent());
    }

    /**
     * 用户修改头像
     *
     * @param userId
     * @param token
     * @param image
     */
    public static void doUserChangeIcon(String userId, String token, String image) {
        String url = API.getChangeUserIconUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("image", image);
        PostKeyVule.doPost(url, params, new UserIconChangeEvent());}




    /**
     * 用户信息修改
     *
     * @param userId
     * @param token
     * @param props  对象 用户属性
     */
    public static void doUserMessageChange(String userId, String token, String props) {
        String url = API.getUserMessageChangeUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("props", props);
        PostKeyVule.doPost(url, params, new UserMessageChangeEvent());
    }


    /**
     * 搜索好友列表
     *
     * @param userId 用户id
     * @param token  令牌
     */
    public static void doSearchFriends(String userId, String token) {
        String url = API.getSearchFriendsUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        PostKeyVule.doPost(url, params, new SearchFriendsEvent());
    }

    /**
     * 获取我的好友列表
     *
     * @param userId 用户id
     * @param token  令牌
     */
    public static void doGetFriendsList(String userId, String token) {
        String url = API.getGetFriendsListUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        PostKeyVule.doPost(url, params, new GetFriendsListEvent());
    }

    /**
     * 我的同小区好友
     *
     * @param userId 用户id
     * @param zoneId 小区唯一id
     * @param token  令牌
     */
    public static void doGetSameZoneUser(String userId, String zoneId, String token) {
        String url = API.getGetSameTownFriendsUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("zoneId", zoneId);
        params.put("token", token);
        PostKeyVule.doPost(url, params, new SearchFriendsEvent());
    }

    /**
     * 根据手机号搜索好友
     *
     * @param userId 用户id
     * @param phone  电话
     * @param token  令牌
     */
    public static void doSearchFriendsByPhone(String userId, String phone, String token) {
        String url = API.getSearchFriendsByPhoneUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("phone", phone);
        params.put("token", token);
        PostKeyVule.doPost(url, params, new SearchFriendsByPhoneEvent());
    }

    /**
     * 根据userid查询用户信息
     *
     * @param userId       用户id
     * @param targetUserId 目标人的id
     * @param token        令牌
     */
    public static void doGetUserInformation(String userId, String token, String targetUserId) {
        String url = API.getUserInformationUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("targetUserId", targetUserId);
        PostKeyVule.doPost(url, params, new UserInformationEvent());
    }

    /**
     * 我的活跃的好友
     *
     * @param userId 用户id
     * @param token  令牌
     */
    public static void doGetActiveFriends(String userId, String token, String days) {
        String url = API.getGetActivFriendsUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("days", days);
        PostKeyVule.doPost(url, params, new GetActiveFriendsListEvent());
    }

    /**
     * 添加好友
     *
     * @param userId       用户id
     * @param token        令牌
     * @param friendUserId 好友id
     */
    public static void doAddFriends(String userId, String token, String friendUserId) {
        String url = API.getAddFriendsUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("friendUserId", friendUserId);
        PostKeyVule.doPost(url, params, new AddFriendsEvent());
    }

    /**
     * 删除好友
     *
     * @param userId       用户id
     * @param token        令牌
     * @param friendUserId 好友id
     */
    public static void doDeleteFriends(String userId, String token, String friendUserId) {
        String url = API.getDeleteFriendsUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("friendUserId", friendUserId);
        PostKeyVule.doPost(url, params, new DeleteFriendsEvent());
    }





    /**
     * 获取我收到的消息
     *
     * @param userId
     */

    public static void doGetMyMessage(String userId) {
        String url = API.getMyMessageUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        PostKeyVule.doPost(url, params, new MeMyMessageEvent());
    }








    /**
     * 邻里圈发帖地图获取坐标或者位置
     *
     * @param locationX
     * @param locationY
     */
    public static void doGetTheXYPoi(String locationX, String locationY) {
        String url = API.getLoacatonPOIByXYUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("x", locationX);
        params.put("y", locationY);
        PostKeyVule.doPost(url, params, new SelectLocationEvent());
    }

    /**
     * 首页将获取到的定位坐标转换成物理坐标
     *
     * @param locationX 定位坐标的X
     * @param locationY 定位坐标点的Y
     */
    public static void doChangeGPS2Map(double locationX, double locationY) {
        String url = API.getGps2LoacatonUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("x", locationX + "");
        params.put("y", locationY + "");
        PostKeyVule.doPost(url, params, new ChangeGps2LocationEvent());
    }






    /**
     * 上传GPS位置
     *
     * @param userId
     * @param token
     * @param x      经度（原始GPS）
     * @param y      纬度（原始GPS）
     */
    public static void doUploadGPSLocation(String userId, String token, String x, String y) {
        String url = API.getUploadGPSLocationUrl();
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", userId);
        params.put("token", token);
        params.put("x", x);
        params.put("y", y);
        PostKeyVule.doPost(url, params, new UploadGPSLocationEvent());
    }





    /**
     * 测试
     */
    public static void doTest() {
        String url = "http://192.168.1.57/android_app/test.json";
        HashMap<String, String> params = API.getCommonParams();
        params.put("userId", "1256465");

        PostKeyVule.doPost(url, params, new TestEvent());
    }

}
