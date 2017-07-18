package com.ztsc.house.bean.login;

/**
 * Created by xuyang on 2017/3/13.
 * 密码登录响应bean
 */

public class PwLoginResponseBody {


    /**
     * code : 200
     * message :
     * result : {"gender":"1","huanxinPassWord":"","headImage":"","phoneNum":"13621114221","huanxinId":"","userName":"666","userId":"20170502145814","status":"0","token":"KrxTu38uIJYYSIItIn0Dcg=="}
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
         * gender : 1
         * huanxinPassWord :
         * headImage :
         * phoneNum : 13621114221
         * huanxinId :
         * userName : 666
         * userId : 20170502145814
         * status : 0
         * token : KrxTu38uIJYYSIItIn0Dcg==
         */

        private int gender;
        private String huanxinPassWord;
        private String headImage;
        private String phoneNum;
        private String huanxinId;
        private String userName;
        private String userId;
        private int status;
        private String token;

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getHuanxinPassWord() {
            return huanxinPassWord;
        }

        public void setHuanxinPassWord(String huanxinPassWord) {
            this.huanxinPassWord = huanxinPassWord;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getHuanxinId() {
            return huanxinId;
        }

        public void setHuanxinId(String huanxinId) {
            this.huanxinId = huanxinId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
