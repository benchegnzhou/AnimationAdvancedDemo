package com.ztsc.house.bean.getcode;

/**
 * Created by xuyang on 2017/7/13.
 */

public class UserSerPasswordResponseBody {

    /**
     * code : 200
     * message :
     * result : {"information":"手机号码不存在","status":"1"}
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
         * information : 手机号码不存在
         * status : 1
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
