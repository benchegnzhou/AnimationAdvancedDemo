package com.ztsc.house.Class;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 99691 on 2017/1/4.
 */

public class Myhouse implements Serializable{
    private String communityname;
    private String address;
    private String imageURL;
    private List<String> imageURLs;
    private String ID;

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public Myhouse setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
        return this;
    }

    public Myhouse() {
    }

    public String getAddress() {
        return address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCommunityname() {
        return communityname;
    }

    public Myhouse setAddress(String address) {
        this.address = address;
        return this;
    }

    public Myhouse setCommunityname(String communityname) {
        this.communityname = communityname;
        return this;
    }

    public Myhouse setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public String getID() {
        return ID;
    }

    public Myhouse setID(String ID) {
        this.ID = ID;
        return this;
    }

}
