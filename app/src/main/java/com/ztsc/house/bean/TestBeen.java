package com.ztsc.house.bean;

/**
 * Created by benchengzhou on 2017/3/28 11:13 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： TestActivityActivity
 * 备    注：
 */

public class TestBeen {


    /**
     * code : 200
     * message :
     * zoneName : 1 zasa
     * zoneId : xianmianhuanhang asd
     * commentCount : 0
     * delTime : sada asdsad
     * topicStatus : 1
     * y : 30.614921364166698
     * x : 104.094772033889
     * address : 四川省; 成都市; 锦江区;
     */

    private int code;
    private String message;
    private String zoneName;
    private String zoneId;
    private int commentCount;
    private String delTime;
    private int topicStatus;
    private double y;
    private double x;
    private String address;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public int getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(int topicStatus) {
        this.topicStatus = topicStatus;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TestBeen{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", zoneId='" + zoneId + '\'' +
                ", commentCount=" + commentCount +
                ", delTime='" + delTime + '\'' +
                ", topicStatus=" + topicStatus +
                ", y=" + y +
                ", x=" + x +
                ", address='" + address + '\'' +
                '}';
    }
}
