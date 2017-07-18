package com.ztsc.house.bean.loacation;

/**
 * Created by benchengzhou on 2017/5/2  14:39 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： Gps2LoacatonBeen
 * 备    注：将一个通过硬件定位的坐标转换成物理坐标的实体类
 */


public class Gps2LoacatonBeen {


    /**
     * X : 122.11211444444444
     * Y : 30.014805111111112
     * ver : v2.0.0
     */

    private String X;
    private String Y;
    private String ver;

    public String getX() {
        return X;
    }

    public void setX(String X) {
        this.X = X;
    }

    public String getY() {
        return Y;
    }

    public void setY(String Y) {
        this.Y = Y;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
}
