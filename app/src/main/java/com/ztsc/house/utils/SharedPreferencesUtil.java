package com.ztsc.house.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * SharedPreferencesUtil
 * author ： zbc
 * createData： 2017/2/23
 */
public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    /**
     * @param prefencePath 文件的存储名称
     * @param context
     * @return
     */
    public static SharedPreferencesUtil getInstance(String prefencePath, Context context) {
        if (instance == null && context != null) {
            instance = new SharedPreferencesUtil(prefencePath, context);
        }
        return instance;
    }

    private SharedPreferencesUtil(String prefencePath, Context context) {
        sp = context.getSharedPreferences(prefencePath, Context.MODE_PRIVATE);   //私有权限存储数据
        editor = sp.edit();
    }

    public long getLongValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getLong(key, 0);
        }
        return 0;
    }

    public String getStringValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getString(key, "");
        }
        return "";
    }

    /**
     * 指定默认值
     *
     * @param key
     * @param defaultUrl
     * @return
     */
    public String getStringValue(String key, String defaultUrl) {
        if (key != null && !key.equals("")) {
            return sp.getString(key, defaultUrl);
        }
        return null;
    }

    public int getIntValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getInt(key, 0);
        }
        return 0;
    }

    public int getIntValueByDefault(String key) {
        if (key != null && !key.equals("")) {
            return sp.getInt(key, 0);
        }
        return 0;
    }

    public boolean getBooleanValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getBoolean(key, false);
        }
        return true;
    }

    public float getFloatValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getFloat(key, 0);
        }
        return 0;
    }

    public void putStringValue(String key, String value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public void putIntValue(String key, int value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public void putBooleanValue(String key, boolean value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void putLongValue(String key, long value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public void putFloatValue(String key, Float value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }

    /**
     * 清除掉所有的数据，这个在需要将当前的sharePrefence所有的数据清空的时候使用
     */
    public void clearAllData() {

        editor.clear().commit();

    }


    /**
     * 将对用的键值的数据从文件中移除
     */
    public void removeData(String key) {
        if (TextUtils.isEmpty(key)) {    //键不能为空
            return;
        }
        editor.remove(key).commit();
    }


}
