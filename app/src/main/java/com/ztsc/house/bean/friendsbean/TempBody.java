package com.ztsc.house.bean.friendsbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuyang on 2017/3/24.
 */

public class TempBody implements Serializable {

    /**
     * code : 200
     * message :
     * result : {"friendList":[{"relationship":2,"friendAge":0,"friendGender":"0","friendProfile":"","isOnline":"0","friendId":"1833082163320170322110130","friendHuanxinId":"201705161808317","friendImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1833082163320170322110130-20170331180058.png","friendName":"许阳2"},{"relationship":2,"friendAge":2,"friendGender":"1","friendProfile":"劳资最萌","isOnline":"0","friendId":"1890918872220170322150921","friendHuanxinId":"201705161808319","friendImgUrl":"http://192.168.1.96:8080/ZtscApp/file/headImage/1890918872220170322150921-20170414201440.png","friendName":"王磊"}]}
     */


        private List<FriendListBean> friendList;

        public List<FriendListBean> getFriendList() {
            return friendList;
        }

        public void setFriendList(List<FriendListBean> friendList) {
            this.friendList = friendList;
        }

        public static class FriendListBean {
            /**
             * relationship : 2
             * friendAge : 0
             * friendGender : 0
             * friendProfile :
             * isOnline : 0
             * friendId : 1833082163320170322110130
             * friendHuanxinId : 201705161808317
             * friendImgUrl : http://192.168.1.96:8080/ZtscApp/file/headImage/1833082163320170322110130-20170331180058.png
             * friendName : 许阳2
             */

            private int relationship;
            private int friendAge;
            private String friendGender;
            private String friendProfile;
            private String isOnline;
            private String friendId;
            private String friendHuanxinId;
            private String friendImgUrl;
            private String friendName;

            public int getRelationship() {
                return relationship;
            }

            public void setRelationship(int relationship) {
                this.relationship = relationship;
            }

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
        }
    }

