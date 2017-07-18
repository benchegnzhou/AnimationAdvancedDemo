package com.ztsc.house.bean;

import java.io.Serializable;

/**
 * Created by xuyang on 2017/6/21.
 */

public class ChatUserBean implements Serializable{
    private String NickName;
    private String UserPhoto;
    private String UserId;
    private String HuanxinId;

    public String getHuanxinId() {
        return HuanxinId;
    }

    public void setHuanxinId(String huanxinId) {
        HuanxinId = huanxinId;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
