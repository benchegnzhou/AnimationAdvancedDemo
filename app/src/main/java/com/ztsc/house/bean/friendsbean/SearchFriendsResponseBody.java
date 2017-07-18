package com.ztsc.house.bean.friendsbean;

import java.util.List;

/**
 * Created by xuyang on 2017/3/24.
 */

public class SearchFriendsResponseBody {

    /**
     * code : 200
     * message :
     * result : {"userList":[{"userId":"1833082163320170322110130","relationship":0,"userHuanxinId":"201705161808317","userImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1833082163320170322110130-20170331180058.png","userAge":0,"isOnline":"0","userGender":"0","userProfile":"","userName":"许阳2"},{"userId":"1890918872220170322150921","relationship":0,"userHuanxinId":"201705161808319","userImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1890918872220170322150921-20170414201440.png","userAge":2,"isOnline":"0","userGender":"1","userProfile":"劳资最萌","userName":"王磊"},{"userId":"005600","relationship":3,"userHuanxinId":"201705161808311","userImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/head.jpg","userAge":0,"isOnline":"0","userGender":"1","userProfile":"","userName":"sa11111111111"},{"userId":"071856","relationship":3,"userHuanxinId":"201705161808312","userImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/head.jpg","userAge":0,"isOnline":"0","userGender":"0","userProfile":"","userName":"sa13600000000"},{"userId":"562054","relationship":3,"userHuanxinId":"2017051618083115","userImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/head.jpg","userAge":0,"isOnline":"0","userGender":"0","userProfile":"","userName":"sa119120"},{"userId":"130906","relationship":3,"userHuanxinId":"201705161808313","userImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/head.jpg","userAge":0,"isOnline":"0","userGender":"0","userProfile":"","userName":"sa13600000002"},{"userId":"297130","relationship":3,"userHuanxinId":"2017051618083113","userImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/head.jpg","userAge":0,"isOnline":"0","userGender":"0","userProfile":"","userName":"sa13600000003"}]}
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
        private List<UserListBean> userList;

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class UserListBean {
            /**
             * userId : 1833082163320170322110130
             * relationship : 0
             * userHuanxinId : 201705161808317
             * userImgUrl : http://192.168.1.96:8080/ZtscApp/file/headImage/1833082163320170322110130-20170331180058.png
             * userAge : 0
             * isOnline : 0
             * userGender : 0
             * userProfile :
             * userName : 许阳2
             */

            private String userId;
            private int relationship;
            private String userHuanxinId;
            private String userImgUrl;
            private int userAge;
            private String isOnline;
            private String userGender;
            private String userProfile;
            private String userName;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getRelationship() {
                return relationship;
            }

            public void setRelationship(int relationship) {
                this.relationship = relationship;
            }

            public String getUserHuanxinId() {
                return userHuanxinId;
            }

            public void setUserHuanxinId(String userHuanxinId) {
                this.userHuanxinId = userHuanxinId;
            }

            public String getUserImgUrl() {
                return userImgUrl;
            }

            public void setUserImgUrl(String userImgUrl) {
                this.userImgUrl = userImgUrl;
            }

            public int getUserAge() {
                return userAge;
            }

            public void setUserAge(int userAge) {
                this.userAge = userAge;
            }

            public String getIsOnline() {
                return isOnline;
            }

            public void setIsOnline(String isOnline) {
                this.isOnline = isOnline;
            }

            public String getUserGender() {
                return userGender;
            }

            public void setUserGender(String userGender) {
                this.userGender = userGender;
            }

            public String getUserProfile() {
                return userProfile;
            }

            public void setUserProfile(String userProfile) {
                this.userProfile = userProfile;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
