package com.ztsc.house.bean.usersignbean;
/**
 * Created by benchengzhou on 2017/7/17  16:19 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： UserSignGetTokenResponse
 * 备    注：
 */


public class UserSignGetTokenResponse {


    /**
     * code : 200
     * message :
     * result : {"workPlanName":"","workPlanId":"","information":"还没到下班时间呢，再等等吧","status":"0","token":"Z2Oiw4HY0nqJJJszYEqXaQ=="}
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
         * workPlanName :
         * workPlanId :
         * information : 还没到下班时间呢，再等等吧
         * status : 0
         * token : Z2Oiw4HY0nqJJJszYEqXaQ==
         */

        private String workPlanName;
        private String workPlanId;
        private String information;
        private int status;
        private String token;

        public String getWorkPlanName() {
            return workPlanName;
        }

        public void setWorkPlanName(String workPlanName) {
            this.workPlanName = workPlanName;
        }

        public String getWorkPlanId() {
            return workPlanId;
        }

        public void setWorkPlanId(String workPlanId) {
            this.workPlanId = workPlanId;
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
