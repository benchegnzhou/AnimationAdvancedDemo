package com.ztsc.house.bean.getcode;

/**
 * Created by xuyang on 2017/3/13.
 * 登录获取验证码响应bean
 */

public class LoginGetCodeResponseBody {

    /**
     * code : 200
     * message :
     * result : {"information":"手机号可用，请接收验证码","status":"0"}
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
         * information : 手机号可用，请接收验证码
         * status : 0
         */

        private String information;
        private String status;

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
