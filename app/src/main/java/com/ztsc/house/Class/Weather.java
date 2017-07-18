package com.ztsc.house.Class;

/**
 * Created by 99691 on 2017/1/18.
 */

public class Weather {
    String temperature, weather, airquality, place, turnovertime,imgweatherurl;

    public String getAirquality() {
        return airquality;
    }

    public Weather setAirquality(String airquality) {
        this.airquality = airquality;
        return this;
    }

    public String getImgweatherurl() {
        return imgweatherurl;
    }

    public Weather setImgweatherurl(String imgweatherurl) {
        this.imgweatherurl = imgweatherurl;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public Weather setPlace(String place) {
        this.place = place;
        return this;
    }

    public String getTemperature() {
        return temperature;
    }

    public Weather setTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getTurnovertime() {
        return turnovertime;
    }

    public Weather setTurnovertime(String turnovertime) {
        this.turnovertime = turnovertime;
        return this;
    }

    public String getWeather() {
        return weather;
    }

    public Weather setWeather(String weather) {
        this.weather = weather;
        return this;
    }
}
