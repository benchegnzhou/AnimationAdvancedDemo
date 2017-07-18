package com.ztsc.house.usersetting;

import android.text.TextUtils;

import com.ztsc.house.application.MApplication;
import com.ztsc.house.common.ConstantValue;
import com.ztsc.house.utils.SharedPreferencesUtil;


/**
 * Created by john on 2017/3/13.
 * 保存用户的个人信息的实体类
 */

public class UserInformationManager {

    private static UserInformationManager userInformationManager = null;


    //更改login状态可以控制后面的值生效后者失效
    public static final String USER_FRIST_RUN = "userFristRun";
    public static final String USER_ISLOGIN = "isLogin";
    public static final String USER_HEADIMAGE = "headImage";
    public static final String USER_PHONENUM = "phoneNum";
    public static final String USER_TOKEN = "token";
    public static final String USER_GENDER = "gender";
    public static final String USER_NICKNAME = "nickName";
    public static final String USER_ID = "userId";
    public static final String USER_ISIDENTIFICATION = "isIdentification";
    public static final String USER_HUANXINUSERNAME = "huanxinUserName";
    public static final String USER_HUANXINUSERPASSWORD = "huanxinUserpassword";


    /**
     * 用户头像本地存储的路径,用户头像的实现策略是每次登陆实现从网络加载，
     * 在所有的加载的时候使用的都是本地的头像
     * 在本地没有的情况下直接的从网络获取
     */

    /**
     * 用户当前设置的小区的名称
     */
    public static String USER_CURRENT_VILLAGE_NAME = "userCurrentVillageName";
    /**
     * 用户当前设置的小区的x坐标
     */
    public static String USER_CURRENT_VILLAGE_LOACTIONX = "userCurrentVillageLoactionX";
    /**
     * 用户当前设置的小区的y坐标
     */
    public static String USER_CURRENT_VILLAGE_LOACTIONY = "userCurrentVillageLoactionY";
    /**
     * 用户当前设置的小区的id
     */
    public static String USER_CURRENT_VILLAGE_ID = "userCurrentVillageId";
    /**
     * 用户当前设置的房屋的id
     */
    public static String USER_CURRENT_HOUSE_ID = "userCurrentHouseId";

    /**
     * 用户当前设置的房屋的名称
     */
    public static String USER_CURRENT_HOUSE_NAME = "userCurrentHouseName";
    /**
     * 用户当前设置的房屋所在小区的ID
     */
    public static String USER_CURRENT_HOUSE_VILLAGE_ID = "userCurrentHouseVillageId";

    /**
     * 用户当前设置的房屋所在小区的名称
     */
    public static String USER_CURRENT_HOUSE_VILLAGE_NAME = "userCurrentHouseVillageName";


    /**
     * 用户当前设置的房屋所在小区的轮廓点坐标
     */
    public static String USER_CURRENT_HOUSE_VILLAGE_COORDS = "userCurrentHouseVillageCoords";

    /**
     * 当前定位的GPS经度坐标
     */
    public static String USER_CURRENT_GPS_X = "userCurrentGpsX";


    /**
     * 当前定位的GPS经度坐标
     */
    public static String USER_CURRENT_GPS_Y = "userCurrentGpsY";


    /**
     * 当前定位坐标转换地理坐标后的经度坐标
     */
    public static String USER_CURRENT_LOCATION_X = "userCurrentLocationX";

    /**
     * 当前定位坐标转换地理坐标后的纬度度坐标
     */
    public static String USER_CURRENT_LOCATION_Y = "userCurrentLocationY";



    /**
     * 用户当前使用的城市的名称
     */
    public static String USER_CURRENT_CITY_NAME_SELECTED = "userCurrentCityNameSelected";

    /**
     * 用户当前使用的城市的X坐标
     */
    public static String USER_CURRENT_CITY_LOACTIONX_SELECTED = "userCurrentCityLocationXSelected";
    /**
     * 用户当前使用的城市Y坐标
     */
    public static String USER_CURRENT_CITY_LOACTIONY_SELECTED = "userCurrentCityLocationYSelected";
    /**
     * 用户当前使用的城市的12位代码， 2      2     2     3     3
     *                                省    市    县   乡     村
     */
    public static String USER_CURRENT_CITY_CODE_SELECTED = "userCurrentCityCodeSelected";
    /**
     * 用户当前定位到的城市
     */
    public static String USER_CURRENT_CITY_LOCATION = "userCurrentCityLocation";

    private UserInformationManager() {
    }

    //单例获取个人信息,使用双重单例创建 参考链接： https://juejin.im/entry/58e507372f301e0062295732
    public static UserInformationManager getInstance() {
        if (userInformationManager == null) {
            synchronized (UserInformationManager.class) {
                if (userInformationManager == null) {
                    userInformationManager = new UserInformationManager();
                }
            }
        }
        return userInformationManager;
    }


    //--------------------------------存储用户登陆后的基本信息----------------------------------//
    public void logout() {
        setUserIsLogin(false);
//        setUserId("");
//        setToken("");
    }

    /**
     * @param headImage
     * @param phoneNum
     * @param token
     * @param gender
     * @param nickName
     * @param userId
     * @param
     */
    public void setUserInformation(boolean isLogin,
                                   String headImage,
                                   String phoneNum,
                                   String token,
                                   int gender,
                                   String nickName,
                                   String userId,

                                   String huanxinUserName,
                                   String huanxinUserpassword) {




        SharedPreferencesUtil instance = SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext);
        instance.putBooleanValue(USER_ISLOGIN, isLogin);
        instance.putStringValue(USER_HEADIMAGE, headImage);
        instance.putStringValue(USER_PHONENUM, phoneNum);
        instance.putStringValue(USER_TOKEN, token);
        instance.putIntValue(USER_GENDER, gender);
        instance.putStringValue(USER_NICKNAME, nickName);
        instance.putStringValue(USER_ID, userId);
//        instance.putIntValue(USER_ISIDENTIFICATION, isIdentification);
        instance.putStringValue(USER_HUANXINUSERNAME, huanxinUserName);
        instance.putStringValue(USER_HUANXINUSERPASSWORD, huanxinUserpassword);
    }

    /**
     * 获取用户是不是第一次运行app
     * @return  false 用户第一次运行 true:确实不是第一次
     */
    public  boolean getUserNotFristRun() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getBooleanValue(USER_FRIST_RUN);
    }
    /**
     * 记录用户是不是第一次运行app
     * @return
     */
    public void setUserNotFristRun(boolean user_isFrist_run) {
        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putBooleanValue(USER_FRIST_RUN, user_isFrist_run);
    }


    /**
     * @return true 登录生效; false: 登录失效
     */
    public boolean getUserIsLogin() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getBooleanValue(USER_ISLOGIN);
    }

    /**
     * @param user_login 用户登录是否有效控制
     */
    public void setUserIsLogin(boolean user_login) {
        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putBooleanValue(USER_ISLOGIN, user_login);
    }

    public String getHeadImage() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_HEADIMAGE);
    }

    public void setHeadImage(String headImage) {
        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_HEADIMAGE, headImage);
    }

    public String getPhoneNum() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_PHONENUM);
    }

    public void setPhoneNum(String phoneNum) {

        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_PHONENUM, phoneNum);
    }

    public String getToken() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_TOKEN);
    }

    public void setToken(String token) {

        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_TOKEN, token);
    }

    public int getGender() {

        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getIntValue(USER_GENDER);

    }

    public void setGender(int gender) {

        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putIntValue(USER_GENDER, gender);
    }

    public String getNickName() {

        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_NICKNAME);
    }

    public void setNickName(String nickName) {

        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_NICKNAME, nickName);
    }

    public String getUserId() {

        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_ID);
    }

    public void setUserId(String userId) {
        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_ID, userId);
    }

    public int getIsIdentification() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getIntValue(USER_ISIDENTIFICATION);
    }

    public void setIsIdentification(int isIdentification) {
        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putIntValue(USER_ISIDENTIFICATION, isIdentification);
    }

    public String getHuanxinUserpassword() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_HUANXINUSERPASSWORD);
    }

    public void setHuanxinUserpassword(String huanxinUserpassword) {
        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_HUANXINUSERPASSWORD, huanxinUserpassword);
    }

    public String getHuanxinUserName() {
        return SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_HUANXINUSERNAME);
    }

    public void setHuanxinUserName(String huanxinUserName) {
        SharedPreferencesUtil.getInstance(ConstantValue.USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue( USER_HUANXINUSERNAME, huanxinUserName);
    }


    //------------------------------------------------------------------------   用户配置

    /**
     * 获取指定ID用户的本地头像存储路径
     *
     * @return 对应ID头像在本地的绝对存储路径
     */
    public String getUserNativeIconPath() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(getUserId());
    }

    /**
     * 存储指定ID用户头像在本地的存储路径
     *
     * @param userNativeIconPath
     */
    public void setUserNativeIconPath(String userNativeIconPath) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(getUserId(), userNativeIconPath);
    }

    /**
     * 用户物业当前设置的小区的名称
     *
     * @return
     */
    public String getUserCurrentVillageName() {
        String userCurrentVillageName = SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_VILLAGE_NAME);
        if(TextUtils.isEmpty(userCurrentVillageName)){

        }
        return userCurrentVillageName;
    }

    /**
     * 用户物业当前设置的小区的名称
     *
     * @param userCurrentVillageName
     */
    public void setUserCurrentVillageName(String userCurrentVillageName) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_VILLAGE_NAME, userCurrentVillageName);
    }

    /**
     * 用户物业当前设置的小区的ID
     *
     * @return
     */
    public String getUserCurrentVillageID() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_VILLAGE_ID);
    }

    /**
     * 用户物业当前设置的小区的ID
     *
     * @param userCurrentVillageId
     */
    public void setUserCurrentVillageID(String userCurrentVillageId) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_VILLAGE_ID, userCurrentVillageId);
    }


    /**
     * 用户物业当前设置的小区的x坐标
     *
     * @return
     */
    public String getUserCurrentVillageLocationX() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_VILLAGE_LOACTIONX);
    }

    /**
     * 用户物业当前设置的小区的x坐标
     *
     * @param userCurrentVillageLocationX
     */
    public void setUserCurrentVillageLocationX(String userCurrentVillageLocationX) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_VILLAGE_LOACTIONX, userCurrentVillageLocationX);
    }

    /**
     * 用户物业当前设置的小区的y坐标
     *
     * @return
     */
    public String getUserCurrentVillageLocationY() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_VILLAGE_LOACTIONY);
    }

    /**
     * 用户物业当前设置的小区的y坐标
     *
     * @param userCurrentVillageLocationY
     */
    public void setUserCurrentVillageLocationY(String userCurrentVillageLocationY) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_VILLAGE_LOACTIONY, userCurrentVillageLocationY);
    }


    /**
     * 用户物业当前设置的房屋所在小区的轮廓
     *
     * @return
     */
    public String getUserCurrentVillageCoords() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_HOUSE_VILLAGE_COORDS);
    }

    /**
     * 用户物业当前设置的房屋所在小区的轮廓
     *
     * @param userCurrentHouseVillageName
     */
    public void setUserCurrentVillageCoords(String userCurrentHouseVillageName) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_HOUSE_VILLAGE_COORDS, userCurrentHouseVillageName);
    }








    //-------------下面的和物业缴费有关------------

    /**
     * 用户物业当前设置的房屋的ID
     *
     * @return
     */
    public String getUserCurrentHouseID() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_HOUSE_ID);
    }

    /**
     * 用户物业当前设置的房屋的ID
     *
     * @param userCurrentHouseId
     */
    public void setUserCurrentHouseID(String userCurrentHouseId) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_HOUSE_ID, userCurrentHouseId);
    }

    /**
     * 用户物业当前设置的房屋的ID
     *
     * @return
     */
    public String getUserCurrentHouseName() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_HOUSE_NAME);
    }

    /**
     * 用户物业当前设置的小区的ID
     *
     * @param userCurrentHouseName
     */
    public void setUserCurrentHouseName(String userCurrentHouseName) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_HOUSE_NAME, userCurrentHouseName);
    }

    /**
     * 用户物业当前设置的房屋所在小区的id
     *
     * @return
     */
    public String getUserCurrentHouseVillageId() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_HOUSE_VILLAGE_ID);
    }

    /**
     * 用户物业当前设置的房屋所在小区的id
     *
     * @param userCurrentHouseVillageId
     */
    public void setUserCurrentHouseVillageId(String userCurrentHouseVillageId) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_HOUSE_VILLAGE_ID, userCurrentHouseVillageId);
    }

    /**
     * 用户物业当前设置的房屋所在小区的id
     *
     * @return
     */
    public String getUserCurrentHouseVillageName() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_HOUSE_VILLAGE_NAME);
    }

    /**
     * 用户物业当前设置的房屋所在小区的id
     *
     * @param userCurrentHouseVillageName
     */
    public void setUserCurrentHouseVillageName(String userCurrentHouseVillageName) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_HOUSE_VILLAGE_NAME, userCurrentHouseVillageName);
    }




    //---------------------------------------------城市定位相关----------

    /**
     * 获取卫星使用经度坐标
     * @return
     */
    public  String getUserCurrentGpsX() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_GPS_X);
    }

    /**
     * 设置卫星使用经度坐标
     * @return
     */
    public  void setUserCurrentGpsX(String userCurrentGpsX) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_GPS_X, userCurrentGpsX);
    }

    /**
     * 获取卫星使用纬度坐标
     * @return
     */
    public  String getUserCurrentGpsY() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_GPS_Y);
    }

    /**
     * 设置卫星使用纬度坐标
     * @return
     */
    public  void setUserCurrentGpsY(String userCurrentGpsY) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_GPS_Y, userCurrentGpsY);
    }

    /**
     * 获取转换后经度坐标
     * @return
     */
    public  String getUserCurrentLocationX() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_LOCATION_X);
    }

    /**
     * 设置转换后经度坐标
     * @return
     */
    public  void setUserCurrentLocationX(String userCurrentLocationX) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_LOCATION_X, userCurrentLocationX);
    }
    /**
     * 获取转换后纬度坐标
     * @return
     */
    public  String getUserCurrentLocationY() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_LOCATION_Y);
    }
    /**
     * 设置转换后纬度坐标
     * @return
     */
    public  void setUserCurrentLocationY(String userCurrentLocationY) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_LOCATION_Y, userCurrentLocationY);
    }

//---------------用户当前选择的城市相关--------

    /**
     * 获取用户当前选中的城市
     * @return
     */
    public  String getUserCurrentCityNameSelected() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_CITY_NAME_SELECTED);
    }
    /**
     * 设置用户当前选中的城市
     * @return
     */
    public  void setUserCurrentCityNameSelected(String userCurrentCitySelected) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_CITY_NAME_SELECTED, userCurrentCitySelected);
    }

    /**
     * 获取用户当前选中的城市的X坐标
     * @return
     */
    public  String getUserCurrentCityLocationXSelected() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_CITY_LOACTIONX_SELECTED);
    }
    /**
     * 设置用户当前选中的城市的X坐标
     * @return
     */
    public  void setUserCurrentCityLocationXSelected(String userCurrentCityXSelected) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_CITY_LOACTIONX_SELECTED, userCurrentCityXSelected);
    }
    /**
     * 获取用户当前选中的城市的Y坐标
     * @return
     */
    public  String getUserCurrentCityLocationYSelected() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_CITY_LOACTIONY_SELECTED);
    }
    /**
     * 设置用户当前选中的城市的Y坐标
     * @return
     */
    public  void setUserCurrentCityLocationYSelected(String userCurrentCityYSelected) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_CITY_LOACTIONY_SELECTED, userCurrentCityYSelected);
    }


    /**
     * 获取用户当前选中的城市的编码
     * @return
     */
    public  String getUserCurrentCityCodeSelected() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_CITY_CODE_SELECTED);
    }
    /**
     * 设置用户当前选中的城市的编码
     * @return
     */
    public  void setUserCurrentCityCodeSelected(String userCurrentCityCodeSelected) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_CITY_CODE_SELECTED, userCurrentCityCodeSelected);
    }






    /**
     * 获取用户当前定位的城市
     * @return
     */
    public  String getUserCurrentCityLocation() {
        return SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .getStringValue(USER_CURRENT_CITY_LOCATION);
    }



    /**USER_CURRENT_CITY_CODE_SELECTED
     * 设置用户当前定位的城市
     * @return
     */
    public  void setUserCurrentCityLocation(String userCurrentCityLocation) {
        SharedPreferencesUtil.getInstance(ConstantValue.ABOUT_USER_INFORMATION_PATH, MApplication.sAppContext)
                .putStringValue(USER_CURRENT_CITY_LOCATION, userCurrentCityLocation);
    }

}
