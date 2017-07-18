package com.ztsc.house.bean.user;

/**
 * Created by xuyang on 2017/3/29.
 */

public class UserMessageChangeResponseBody {


    /**
     * code : 200
     * message : 成功
     * result : {"status":0,"information":""}
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
         * information :
         */

        private int status;
        private String information;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }
    }
}
