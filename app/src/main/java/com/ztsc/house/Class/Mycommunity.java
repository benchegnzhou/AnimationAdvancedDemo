package com.ztsc.house.Class;

/**
 * Created by 99691 on 2017/1/6.
 */

public class Mycommunity {
    private String name;
    private String address;
    private String imageURL;

    public Mycommunity() {
    }

    public String getAddress() {
        return address;
    }

    public Mycommunity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Mycommunity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getName() {
        return name;
    }

    public Mycommunity setName(String name) {
        this.name = name;
        return this;
    }
}
