package com.ztsc.house.bean.getcode;

/**
 * Created by xuyang on 2017/7/13.
 */

public class UserRegistCodeCheckResponseBody {

    /**
     * code : 200
     * message :
     * result : {"gender":"0","huanxin_pass_word":"","headImage":"","phoneNum":"18330821633","huanxinId":"","userName":"车匙","userId":"20170601101830","status":"0","token":"Kzt/BA1Ezdz0YbvxF287pw=="}
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
         * gender : 0
         * huanxin_pass_word :
         * headImage :
         * phoneNum : 18330821633
         * huanxinId :
         * userName : 车匙
         * userId : 20170601101830
         * status : 0
         * token : Kzt/BA1Ezdz0YbvxF287pw==
         */

        private int gender;
        private String huanxin_pass_word;
        private String headImage;
        private String phoneNum;
        private String huanxinId;
        private String userName;
        private String userId;
        private String status;
        private String token;

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getHuanxin_pass_word() {
            return huanxin_pass_word;
        }

        public void setHuanxin_pass_word(String huanxin_pass_word) {
            this.huanxin_pass_word = huanxin_pass_word;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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
