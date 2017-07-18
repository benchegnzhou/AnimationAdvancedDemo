package com.ztsc.house.data;

import android.os.Handler;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataLayer implements  URLS {

//    public static User user =new User();
    public static Handler mhandler;

    public static void login(final String LOGIN_URL) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(LOGIN_URL)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
//                    DataLayer.user = pareJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }





}
