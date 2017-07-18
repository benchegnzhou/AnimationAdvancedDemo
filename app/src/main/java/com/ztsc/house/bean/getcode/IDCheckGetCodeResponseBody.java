package com.ztsc.house.bean.getcode;

/**
 * Created by xuyang on 2017/3/13.
 * 身份验证获取验证码（用户处于登录状态时使用）响应bean
 */

public class IDCheckGetCodeResponseBody {
    //    状态码
    private int code;
    //    状态说明
    private String message;
    //    结果集
    private String result;
    //    请求成功还是失败 0成功1失败
    private int status;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
