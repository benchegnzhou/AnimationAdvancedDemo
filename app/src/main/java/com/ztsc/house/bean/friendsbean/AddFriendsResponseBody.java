package com.ztsc.house.bean.friendsbean;

/**
 * Created by xuyang on 2017/3/24.
 */

public class AddFriendsResponseBody {

    /**
     * code : 200
     * message : 成功
     * result : {"statusCode":0}
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
         * statusCode : 0
         */

        private int statusCode;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }
    }
}
