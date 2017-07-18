package com.ztsc.house.Class;

/**
 * Created by Administrator on 2017/2/8.
 */

public class City {
    private String name;
    private String ename;
    private String code;

    private String address;

    private Double x;
    private Double y;
    public City()
    {
        name = "北京市";
        code = "110000";
        x = 116.405285;
        y = 39.904989;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
