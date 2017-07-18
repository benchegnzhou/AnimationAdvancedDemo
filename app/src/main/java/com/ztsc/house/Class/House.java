package com.ztsc.house.Class;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 99691 on 2017/1/25.
 */

public class House implements Serializable {
    private String head, price, housetyp, area, avgprice, listingtime, down_payment, use, floor, fitment
            , buildingtyp, orientation, buildingtime, district, appointmenttime, houseid, subway, community
            , sellingpoint, housetypemsg, floorplan, floorplan_remarks, attentioncount, browsecount, havelookcount;
    private List<String>ImageURLs;

    public String getAppointmenttime() {
        return appointmenttime;
    }

    public House setAppointmenttime(String appointmenttime) {
        this.appointmenttime = appointmenttime;
        return this;
    }

    public String getArea() {
        return area;
    }

    public House setArea(String area) {
        this.area = area;
        return this;
    }

    public String getAttentioncount() {
        return attentioncount;
    }

    public House setAttentioncount(String attentioncount) {
        this.attentioncount = attentioncount;
        return this;
    }

    public String getAvgprice() {
        return avgprice;
    }

    public House setAvgprice(String avgprice) {
        this.avgprice = avgprice;
        return this;
    }

    public String getBrowsecount() {
        return browsecount;
    }

    public House setBrowsecount(String browsecount) {
        this.browsecount = browsecount;
        return this;
    }

    public String getBuildingtime() {
        return buildingtime;
    }

    public House setBuildingtime(String buildingtime) {
        this.buildingtime = buildingtime;
        return this;
    }

    public String getBuildingtyp() {
        return buildingtyp;
    }

    public House setBuildingtyp(String buildingtyp) {
        this.buildingtyp = buildingtyp;
        return this;
    }

    public String getCommunity() {
        return community;
    }

    public House setCommunity(String community) {
        this.community = community;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public House setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getDown_payment() {
        return down_payment;
    }

    public House setDown_payment(String down_payment) {
        this.down_payment = down_payment;
        return this;
    }

    public String getFitment() {
        return fitment;
    }

    public House setFitment(String fitment) {
        this.fitment = fitment;
        return this;
    }

    public String getFloor() {
        return floor;
    }

    public House setFloor(String floor) {
        this.floor = floor;
        return this;
    }

    public String getFloorplan() {
        return floorplan;
    }

    public House setFloorplan(String floorplan) {
        this.floorplan = floorplan;
        return this;
    }

    public String getFloorplan_remarks() {
        return floorplan_remarks;
    }

    public House setFloorplan_remarks(String floorplan_remarks) {
        this.floorplan_remarks = floorplan_remarks;
        return this;
    }

    public String getHavelookcount() {
        return havelookcount;
    }

    public House setHavelookcount(String havelookcount) {
        this.havelookcount = havelookcount;
        return this;
    }

    public String getHead() {
        return head;
    }

    public House setHead(String head) {
        this.head = head;
        return this;
    }

    public String getHouseid() {
        return houseid;
    }

    public House setHouseid(String houseid) {
        this.houseid = houseid;
        return this;
    }

    public String getHousetyp() {
        return housetyp;
    }

    public House setHousetyp(String housetyp) {
        this.housetyp = housetyp;
        return this;
    }

    public String getHousetypemsg() {
        return housetypemsg;
    }

    public House setHousetypemsg(String housetypemsg) {
        this.housetypemsg = housetypemsg;
        return this;
    }

    public List<String> getImageURLs() {
        return ImageURLs;
    }

    public House setImageURLs(List<String> imageURLs) {
        ImageURLs = imageURLs;
        return this;
    }

    public String getListingtime() {
        return listingtime;
    }

    public House setListingtime(String listingtime) {
        this.listingtime = listingtime;
        return this;
    }

    public String getOrientation() {
        return orientation;
    }

    public House setOrientation(String orientation) {
        this.orientation = orientation;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public House setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getSellingpoint() {
        return sellingpoint;
    }

    public House setSellingpoint(String sellingpoint) {
        this.sellingpoint = sellingpoint;
        return this;
    }

    public String getSubway() {
        return subway;
    }

    public House setSubway(String subway) {
        this.subway = subway;
        return this;
    }

    public String getUse() {
        return use;
    }

    public House setUse(String use) {
        this.use = use;
        return this;
    }
}
