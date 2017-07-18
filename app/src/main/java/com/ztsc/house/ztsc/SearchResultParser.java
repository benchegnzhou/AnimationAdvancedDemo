package com.ztsc.house.ztsc;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/23.
 */

public class SearchResultParser {

    public  String result_code = "EE";
    public int total;

   public ArrayList poi_arr = new ArrayList();

    /// result 必须是JSON串
    public void parseSearchResult(String result)
    {
        try {
            JSONObject obj = new JSONObject(result);
            result_code = obj.getString("ret_code");
            total = obj.getInt("total");

            JSONArray arr = obj.getJSONArray("list");

            for (int i = 0; i < arr.length(); i++){
                POI poi = new POI();

                JSONObject poiObj = arr.getJSONObject(i);
                poi.setPoiid(poiObj.getString("poiid"));
                poi.setName(poiObj.getString("name"));
                poi.setX(poiObj.getDouble("x"));
                poi.setY(poiObj.getDouble("y"));
                poi.setAddress(poiObj.getString("address"));

                poi_arr.add(poi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
