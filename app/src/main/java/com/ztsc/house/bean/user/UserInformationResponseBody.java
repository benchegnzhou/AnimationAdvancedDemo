package com.ztsc.house.bean.user;

import java.util.List;

/**
 * Created by xuyang on 2017/5/17.
 */

public class UserInformationResponseBody {

    /**
     * code : 200
     * message :
     * result : {"imageUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1890918872220170322150921-20170414201440.png","userPictrueList":"1890918872220170322150921-0-20170518115756.jpg","userId":"1890918872220170322150921","nickName":"王磊","phone":"","gender":1,"isIdentification":0,"huanxinUserName":"201705231745429","birth":"","hometown":"成都市","hometownCode":"","selfIntroduction":"劳资最萌","relationship":2,"registerDate":"2017-05-03","lastLoginDate":"2017-05-23 18:50:01.0","likeCount":1,"likeFlag":0,"topicCount":681,"topicImageUrlList":[{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/15210982030_20170321164201_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/15210982030_20170321165912_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/15210982030_20170322092338_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/123_20170322102944_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/123_20170322103235_01.png"}],"serviceCount":7,"serviceImageUrlList":[{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170521125417.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170520152053.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170519155110.JPEG"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170518170319.JPEG"}],"appealCount":6,"appealImageUrlList":[{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170521125635.JPEG"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170518170954.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170518170638.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170512195122.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170512181410.png"}],"level":""}
     */

    private int code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * imageUrl : http://192.168.1.96:8080/ZtscApp/file/headImage/1890918872220170322150921-20170414201440.png
         * userPictrueList : 1890918872220170322150921-0-20170518115756.jpg
         * userId : 1890918872220170322150921
         * nickName : 王磊
         * phone :
         * gender : 1
         * isIdentification : 0
         * huanxinUserName : 201705231745429
         * birth :
         * hometown : 成都市
         * hometownCode :
         * selfIntroduction : 劳资最萌
         * relationship : 2
         * registerDate : 2017-05-03
         * lastLoginDate : 2017-05-23 18:50:01.0
         * likeCount : 1
         * likeFlag : 0
         * topicCount : 681
         * topicImageUrlList : [{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/15210982030_20170321164201_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/15210982030_20170321165912_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/15210982030_20170322092338_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/123_20170322102944_01.png"},{"url":"http://192.168.1.96:8080/ZtscApp/file/topic/123_20170322103235_01.png"}]
         * serviceCount : 7
         * serviceImageUrlList : [{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170521125417.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170520152053.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170519155110.JPEG"},{"url":"http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170518170319.JPEG"}]
         * appealCount : 6
         * appealImageUrlList : [{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170521125635.JPEG"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170518170954.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170518170638.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170512195122.jpg"},{"url":"http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170512181410.png"}]
         * level :
         */

        private String imageUrl;
        private String userPictrueList;
        private String userId;
        private String nickName;
        private String phone;
        private int gender;
        private int isIdentification;
        private String huanxinUserName;
        private String birth;
        private String hometown;
        private String hometownCode;
        private String selfIntroduction;
        private int relationship;
        private String registerDate;
        private String lastLoginDate;
        private int likeCount;
        private int likeFlag;
        private int topicCount;
        private int serviceCount;
        private int appealCount;
        private String level;
        private List<TopicImageUrlListBean> topicImageUrlList;
        private List<ServiceImageUrlListBean> serviceImageUrlList;
        private List<AppealImageUrlListBean> appealImageUrlList;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getUserPictrueList() {
            return userPictrueList;
        }

        public void setUserPictrueList(String userPictrueList) {
            this.userPictrueList = userPictrueList;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getIsIdentification() {
            return isIdentification;
        }

        public void setIsIdentification(int isIdentification) {
            this.isIdentification = isIdentification;
        }

        public String getHuanxinUserName() {
            return huanxinUserName;
        }

        public void setHuanxinUserName(String huanxinUserName) {
            this.huanxinUserName = huanxinUserName;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public String getHometownCode() {
            return hometownCode;
        }

        public void setHometownCode(String hometownCode) {
            this.hometownCode = hometownCode;
        }

        public String getSelfIntroduction() {
            return selfIntroduction;
        }

        public void setSelfIntroduction(String selfIntroduction) {
            this.selfIntroduction = selfIntroduction;
        }

        public int getRelationship() {
            return relationship;
        }

        public void setRelationship(int relationship) {
            this.relationship = relationship;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(String lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getLikeFlag() {
            return likeFlag;
        }

        public void setLikeFlag(int likeFlag) {
            this.likeFlag = likeFlag;
        }

        public int getTopicCount() {
            return topicCount;
        }

        public void setTopicCount(int topicCount) {
            this.topicCount = topicCount;
        }

        public int getServiceCount() {
            return serviceCount;
        }

        public void setServiceCount(int serviceCount) {
            this.serviceCount = serviceCount;
        }

        public int getAppealCount() {
            return appealCount;
        }

        public void setAppealCount(int appealCount) {
            this.appealCount = appealCount;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public List<TopicImageUrlListBean> getTopicImageUrlList() {
            return topicImageUrlList;
        }

        public void setTopicImageUrlList(List<TopicImageUrlListBean> topicImageUrlList) {
            this.topicImageUrlList = topicImageUrlList;
        }

        public List<ServiceImageUrlListBean> getServiceImageUrlList() {
            return serviceImageUrlList;
        }

        public void setServiceImageUrlList(List<ServiceImageUrlListBean> serviceImageUrlList) {
            this.serviceImageUrlList = serviceImageUrlList;
        }

        public List<AppealImageUrlListBean> getAppealImageUrlList() {
            return appealImageUrlList;
        }

        public void setAppealImageUrlList(List<AppealImageUrlListBean> appealImageUrlList) {
            this.appealImageUrlList = appealImageUrlList;
        }

        public static class TopicImageUrlListBean {
            /**
             * url : http://192.168.1.96:8080/ZtscApp/file/topic/15210982030_20170321164201_01.png
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ServiceImageUrlListBean {
            /**
             * url : http://192.168.1.96:8080/ZtscApp/file/service/1890918872220170322150921-0-20170521125417.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class AppealImageUrlListBean {
            /**
             * url : http://192.168.1.96:8080/ZtscApp/file/appeal/1890918872220170322150921-0-20170521125635.JPEG
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
