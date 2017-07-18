package com.ztsc.house.bean;

import java.util.List;

/**
 * Created by xuyang on 2017/5/20.
 */

public class MeMyCommendResponseBody {

    /**
     * code : 200
     * message :
     * result : [{"userId":"1890918872220170322150921","villageId":"510033569354","userPhoneNum":"18909188722","createDate":"2017-04-19 00:00:00","villageName":"双园小区","affairCategory":"表扬","imageList":"","affairTitle":"1","affairDiscribe":"回家阿杰","userAddress":"北京海淀","userRealName":"王磊","praiseId":"20170419131436"},{"userId":"1890918872220170322150921","villageId":"510028146687","userPhoneNum":"18909188722","createDate":"2017-05-20 00:00:00","villageName":"五桂园","affairCategory":"表扬","imageList":"","affairTitle":"1","affairDiscribe":"卫生搞的不错","userAddress":"北京海淀","userRealName":"王磊","praiseId":"20170520150646"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * userId : 1890918872220170322150921
         * villageId : 510033569354
         * userPhoneNum : 18909188722
         * createDate : 2017-04-19 00:00:00
         * villageName : 双园小区
         * affairCategory : 表扬
         * imageList :
         * affairTitle : 1
         * affairDiscribe : 回家阿杰
         * userAddress : 北京海淀
         * userRealName : 王磊
         * praiseId : 20170419131436
         */

        private String userId;
        private String villageId;
        private String userPhoneNum;
        private String createDate;
        private String villageName;
        private String affairCategory;
        private String imageList;
        private String affairTitle;
        private String affairDiscribe;
        private String userAddress;
        private String userRealName;
        private String praiseId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVillageId() {
            return villageId;
        }

        public void setVillageId(String villageId) {
            this.villageId = villageId;
        }

        public String getUserPhoneNum() {
            return userPhoneNum;
        }

        public void setUserPhoneNum(String userPhoneNum) {
            this.userPhoneNum = userPhoneNum;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getAffairCategory() {
            return affairCategory;
        }

        public void setAffairCategory(String affairCategory) {
            this.affairCategory = affairCategory;
        }

        public String getImageList() {
            return imageList;
        }

        public void setImageList(String imageList) {
            this.imageList = imageList;
        }

        public String getAffairTitle() {
            return affairTitle;
        }

        public void setAffairTitle(String affairTitle) {
            this.affairTitle = affairTitle;
        }

        public String getAffairDiscribe() {
            return affairDiscribe;
        }

        public void setAffairDiscribe(String affairDiscribe) {
            this.affairDiscribe = affairDiscribe;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getUserRealName() {
            return userRealName;
        }

        public void setUserRealName(String userRealName) {
            this.userRealName = userRealName;
        }

        public String getPraiseId() {
            return praiseId;
        }

        public void setPraiseId(String praiseId) {
            this.praiseId = praiseId;
        }
    }
}
