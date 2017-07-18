package com.ztsc.house.data;

import com.ztsc.house.Class.City;
import com.ztsc.house.model.User;
import com.ztsc.house.usersetting.UserInformationManager;

import java.util.HashMap;


/**
 * Created by Administrator on 2017/2/9.
 */

public class GlobalData {

    // 1.默认在北京
    public static City location_city = new City();
    public static City selected_city = new City();

    // 2.GPS定位坐标  首页启动直接定位 ---> 定位成功  -----> 调用偏转算法(这个值不能直接使用)
    public static Double currentGpsX = 106.00;
    public static Double currentGpsY = 39.00;
    public static Double currentLocationX = 116.405285;
    public static Double currentLocationY = 39.904989;

    // 3.用户环信ID和用户名的对应关系
    public static HashMap<String, User> huanxin2UserNameMap = new HashMap<>();


    public static Double getCurrentGpsX() {
        return currentGpsX;
    }

    public static void setCurrentGpsX(Double currentGpsX) {
        GlobalData.currentGpsX = currentGpsX;
    }

    public static Double getCurrentGpsY() {
        return currentGpsY;
    }

    public static void setCurrentGpsY(Double currentGpsY) {
        GlobalData.currentGpsY = currentGpsY;
    }

    public static Double getCurrentLocationX() {
        return currentLocationX;
    }

    public static void setCurrentLocationX(Double currentLocationX) {
        GlobalData.currentLocationX = currentLocationX;
    }

    public static Double getCurrentLocationY() {
        return currentLocationY;
    }

    public static void setCurrentLocationY(Double currentLocationY) {
        GlobalData.currentLocationY = currentLocationY;
    }


    public static void setUserSelectionCity(String name, String code, Double x, Double y) {
        selected_city.setName(name);
        selected_city.setCode(code);
        selected_city.setX(x);
        selected_city.setY(y);
    }

    public static void setUserLocationCity(String name, String code, Double x, Double y) {
        location_city.setName(name);
        location_city.setCode(code);
        location_city.setX(x);
        location_city.setY(y);
    }


    // 3.1根据环信ID取用户名
    public static  User getUserNameByHuanxinId(String huanxinId)
    {
        return huanxin2UserNameMap.get(huanxinId);
    }
    public static void addHuanxinIdAndUserName(String huanxinId,  User user)
    {
        huanxin2UserNameMap.put(huanxinId, user);
    }




    /**
     * 位置全局存储
     */
    public static void saveGlobalData() {
        UserInformationManager.getInstance().setUserCurrentGpsX(currentGpsX.toString());
        UserInformationManager.getInstance().setUserCurrentGpsY(currentGpsY.toString());
        UserInformationManager.getInstance().setUserCurrentLocationX(currentLocationX.toString());
        UserInformationManager.getInstance().setUserCurrentLocationY(currentLocationY.toString());

        UserInformationManager.getInstance().setUserCurrentCityNameSelected(selected_city.getName());
        UserInformationManager.getInstance().setUserCurrentCityLocationXSelected(selected_city.getX().toString());
        UserInformationManager.getInstance().setUserCurrentCityLocationYSelected(selected_city.getY().toString());
        UserInformationManager.getInstance().setUserCurrentCityCodeSelected(selected_city.getCode());
    }

}
