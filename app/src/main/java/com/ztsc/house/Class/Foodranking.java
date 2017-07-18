package com.ztsc.house.Class;

/**
 * Created by 99691 on 2017/1/11.
 */

public class Foodranking {
    private String name, label, personpay, distance, photourl;

    public Foodranking() {
    }

    public String getDistance() {
        return distance;
    }

    public Foodranking setDistance(String distance) {
        this.distance = distance;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Foodranking setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getName() {
        return name;
    }

    public Foodranking setName(String name) {
        this.name = name;
        return this;
    }

    public String getPersonpay() {
        return personpay;
    }

    public Foodranking setPersonpay(String personpay) {
        this.personpay = personpay;
        return this;
    }

    public String getPhotourl() {
        return photourl;
    }

    public Foodranking setPhotourl(String photourl) {
        this.photourl = photourl;
        return this;
    }
}
