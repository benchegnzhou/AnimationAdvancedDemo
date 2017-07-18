package com.ztsc.house.eventbusbody;


/**
 * Created by Jhon on 2017/3/21.
 * 功能描述：
 * 备    注：
 */

public abstract class ZTAnyEventType {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    int code;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    String tag;
    String result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     *
     * @return
     */
    public abstract Object parseResult() ;

    public void setSuccessCode() {
        this.code = SUCCESS;
    }

    public void setFailCode() {
        this.code = FAIL;
    }


}
