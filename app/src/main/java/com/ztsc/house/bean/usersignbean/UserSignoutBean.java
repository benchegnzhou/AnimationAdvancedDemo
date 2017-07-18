package com.ztsc.house.bean.usersignbean;

/**
 * Created by benchengzhou on 2017/3/28 11:13 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： TestActivityActivity
 * 备    注：
 */

public class UserSignoutBean {


    /**
     * code : 200
     * message :
     * result : {"signTime":"2017-07-17 16:09:03","information":"签退成功","status":"0","isSuccess":"0"}
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
         * signTime : 2017-07-17 16:09:03
         * information : 签退成功
         * status : 0
         * isSuccess : 0
         */

        private String signTime;
        private String information;
        private int status;
        private String isSuccess;

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(String isSuccess) {
            this.isSuccess = isSuccess;
        }
    }
}
