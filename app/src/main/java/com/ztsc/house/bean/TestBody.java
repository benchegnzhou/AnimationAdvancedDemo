package com.ztsc.house.bean;

/**
 * Created by Jhon on 2017/3/22.
 * 功能描述：
 * 备    注：
 */

public class TestBody {


    /**
     * code : 200
     * message :
     * result : {}
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
    }

    @Override
    public String toString() {
        return "TestBody{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
