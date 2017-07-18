package com.ztsc.house.data;

/**
 * Created by 99691 on 2016/12/30.
 */

public interface  URLS {
    // String BASE_URL = "http://10.0.2.2/house/";
   String BASE_URL = "http://192.168.1.80:9090/house/";

    // GPS转火星坐标
    // http://58.20.55.194:10029/MapServer/Service?server=Offset&x=125.5&y=39.6
    String GIS_SERVICE_GPS_CONVERT_URL = "http://58.20.55.194:10029/MapServer/Service?server=Offset&";

    // GIS搜索引擎（POI搜索）
    String GIS_SERVICE_SEARCH_URL = "http://58.20.55.194:10029/MapServer/Service?server=Search&";

}
