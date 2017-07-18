package com.ztsc.house.bean.friendsbean;

import java.io.Serializable;
import java.util.List;
/**
 * Created by benchengzhou on 2017/7/13  13:44 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： MyFriendsResponseBody
 * 备    注：
 */

public class MyFriendsResponseBody implements Serializable {


    /**
     * code : 200
     * message :
     * result : {"friendList":[{"friendAge":2,"friendGender":"1","friendProfile":"劳资最萌","isOnline":"0","friendId":"005600","friendHuanxinId":"201705231745421","friendImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/005600-20170527151108.png","friendName":"其实我很帅","relationship":2},{"friendAge":0,"friendGender":"0","friendProfile":"","isOnline":"0","friendId":"1369353845420170322173845","friendHuanxinId":"201705231745425","friendImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1369353845420170322173845-20170331135420.png","friendName":"sa许阳1","relationship":0},{"friendAge":0,"friendGender":"0","friendProfile":"","isOnline":"0","friendId":"1833082163320170322110130","friendHuanxinId":"201705231745427","friendImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1833082163320170322110130-20170621144442.png","friendName":"许许阳阳","relationship":0},{"friendAge":2,"friendGender":"1","friendProfile":"劳资最萌","isOnline":"0","friendId":"1890918872220170322150921","friendHuanxinId":"201705231745429","friendImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1890918872220170322150921-20170608125616.png","friendName":"sa磊","relationship":0}]}
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
        private List<FriendListBean> friendList;

        public List<FriendListBean> getFriendList() {
            return friendList;
        }

        public void setFriendList(List<FriendListBean> friendList) {
            this.friendList = friendList;
        }

        public static class FriendListBean {
            /**
             * friendAge : 2
             * friendGender : 1
             * friendProfile : 劳资最萌
             * isOnline : 0
             * friendId : 005600
             * friendHuanxinId : 201705231745421
             * friendImgUrl : http://192.168.1.96:8080/ZtscApp/file/headImage/005600-20170527151108.png
             * friendName : 其实我很帅
             * relationship : 2
             */

            private int friendAge;
            private String friendGender;
            private String friendProfile;
            private String isOnline;
            private String friendId;
            private String friendHuanxinId;
            private String friendImgUrl;
            private String friendName;
            private int relationship;

            public int getFriendAge() {
                return friendAge;
            }

            public void setFriendAge(int friendAge) {
                this.friendAge = friendAge;
            }

            public String getFriendGender() {
                return friendGender;
            }

            public void setFriendGender(String friendGender) {
                this.friendGender = friendGender;
            }

            public String getFriendProfile() {
                return friendProfile;
            }

            public void setFriendProfile(String friendProfile) {
                this.friendProfile = friendProfile;
            }

            public String getIsOnline() {
                return isOnline;
            }

            public void setIsOnline(String isOnline) {
                this.isOnline = isOnline;
            }

            public String getFriendId() {
                return friendId;
            }

            public void setFriendId(String friendId) {
                this.friendId = friendId;
            }

            public String getFriendHuanxinId() {
                return friendHuanxinId;
            }

            public void setFriendHuanxinId(String friendHuanxinId) {
                this.friendHuanxinId = friendHuanxinId;
            }

            public String getFriendImgUrl() {
                return friendImgUrl;
            }

            public void setFriendImgUrl(String friendImgUrl) {
                this.friendImgUrl = friendImgUrl;
            }

            public String getFriendName() {
                return friendName;
            }

            public void setFriendName(String friendName) {
                this.friendName = friendName;
            }

            public int getRelationship() {
                return relationship;
            }

            public void setRelationship(int relationship) {
                this.relationship = relationship;
            }

            @Override
            public String toString() {
                return "FriendListBean{" +
                        "friendAge=" + friendAge +
                        ", friendGender='" + friendGender + '\'' +
                        ", friendProfile='" + friendProfile + '\'' +
                        ", isOnline='" + isOnline + '\'' +
                        ", friendId='" + friendId + '\'' +
                        ", friendHuanxinId='" + friendHuanxinId + '\'' +
                        ", friendImgUrl='" + friendImgUrl + '\'' +
                        ", friendName='" + friendName + '\'' +
                        ", relationship=" + relationship +
                        '}';
            }
        }


    }

    @Override
    public String toString() {
        return "MyFriendsResponseBody{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
