package com.ztsc.house.bean.friendsbean;

import java.util.List;

/**
 * Created by xuyang on 2017/3/24.
 */

public class SameTownFriendsResponseBody {


    /**
     * code : 200
     * message : 成功
     * result : {"userList":[{"userId":"1545454sdasd","userHuanxinId":"1545454sdasd","userName":"小张","userImgUrl":"www.baidu .com"},{"userId":"1545454sdasd","userHuanxinId":"1545454sdasd","userName":"唐宁 one","userImgUrl":"www.baidu .com"}]}
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
             * userId : 1545454sdasd
             * userHuanxinId : 1545454sdasd
             * userName : 小张
             * userImgUrl : www.baidu .com
             */

            private String userId;
            private String userHuanxinId;
            private String userName;
            private String userImgUrl;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserHuanxinId() {
                return userHuanxinId;
            }

            public void setUserHuanxinId(String userHuanxinId) {
                this.userHuanxinId = userHuanxinId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserImgUrl() {
                return userImgUrl;
            }

            public void setUserImgUrl(String userImgUrl) {
                this.userImgUrl = userImgUrl;
            }
        }
    }
}
