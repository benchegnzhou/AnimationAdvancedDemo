package com.ztsc.house.bean.checkcode;

/**
 * Created by xuyang on 2017/3/13.
 * 注册验证码校验响应bean
 */

public class RegistCodeCheckResponseBody {

    /**
     * code : 200
     * message : 成功
     * result : {"status":0,"headImage":"www.baidu.com","phoneNum":"15201087720","gender":0,"nickName":"关小虎","userId":"e15181548455","isIdentification":0,"token":"95455b555a55151b1555c"}
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
         * status : 0
         * headImage : www.baidu.com
         * phoneNum : 15201087720
         * gender : 0
         * nickName : 关小虎
         * userId : e15181548455
         * isIdentification : 0
         * token : 95455b555a55151b1555c
         */

        private int status;
        private String headImage;
        private String phoneNum;
        private int gender;
        private String nickName;
        private String userId;
        private int isIdentification;
        private String token;
        private String huanxinUserName;
        private String huanxinUserpassword;

        public String getHuanxinUserName() {
            return huanxinUserName;
        }

        public void setHuanxinUserName(String huanxinUserName) {
            this.huanxinUserName = huanxinUserName;
        }

        public String getHuanxinUserpassword() {
            return huanxinUserpassword;
        }

        public void setHuanxinUserpassword(String huanxinUserpassword) {
            this.huanxinUserpassword = huanxinUserpassword;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getIsIdentification() {
            return isIdentification;
        }

        public void setIsIdentification(int isIdentification) {
            this.isIdentification = isIdentification;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
