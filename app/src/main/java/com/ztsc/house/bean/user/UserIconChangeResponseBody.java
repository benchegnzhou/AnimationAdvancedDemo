package com.ztsc.house.bean.user;

import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;

/**
 * Created by xuyang on 2017/3/29.
 */

public class UserIconChangeResponseBody {
    private ArrayList<LocalMedia> localMedias = new ArrayList<>();   //用于存放选中后的照片

    /**
     * code : 200
     * message :
     * result : {"status":"0","information":"修改成功!"}
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
         * information : 修改成功!
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
