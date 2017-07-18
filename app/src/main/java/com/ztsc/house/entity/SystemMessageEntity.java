package com.ztsc.house.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by benchengzhou on 2017/6/28  12:22 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： SystemMessageEntity
 * 备    注： 自动建表
 * 首先在User类上添加@DatabaseTable(tableName = "tb_user")，标明这是数据库中的一张表，标明为tb_user
 * 然后分别在属性上添加@DatabaseField(columnName = "name") ，columnName的值为该字段在数据中的列名
 *
 * @DatabaseField(generatedId = true) ，generatedId 表示id为主键且自动生成
 */


@DatabaseTable(tableName = "tb_system_message")
public class SystemMessageEntity {



        /**
         * createTime : 2017-06-28 12:31:44.0
         * msgId : 11
         * userId : 005600
         * msgTitle : 新订单
         * msgContent : 接收到新的求助订单
         * msgTypeCode : 1002
         * msgTypeDesc : 求助订单
         * targetId : happy
         * huanxinId : asd
         * targetUrl : www.baidu.com
         * action : 1
         * userName : www.baidu.com
         */
//        @DatabaseField(columnName = "msgId")
        @DatabaseField(generatedId = true)
        private int id;

        @DatabaseField(columnName = "msgId")
        private String msgId;

        @DatabaseField(columnName = "createTime")
        private String createTime;

        @DatabaseField(columnName = "userId")
        private String userId;

        @DatabaseField(columnName = "msgTitle")
        private String msgTitle;
        @DatabaseField(columnName = "msgContent")
        private String msgContent;
        @DatabaseField(columnName = "msgTypeCode")
        private String msgTypeCode;
        @DatabaseField(columnName = "msgTypeDesc")
        private String msgTypeDesc;
        @DatabaseField(columnName = "targetId")
        private String targetId;
        @DatabaseField(columnName = "huanxinId")
        private String huanxinId;
        @DatabaseField(columnName = "targetUrl")
        private String targetUrl;
        @DatabaseField(columnName = "action")
        private String action;
        @DatabaseField(columnName = "userName")
        private String userName;
        //是不是已读
        @DatabaseField(columnName = "isRead")
        private boolean isRead;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getMsgTypeCode() {
            return msgTypeCode;
        }

        public void setMsgTypeCode(String msgTypeCode) {
            this.msgTypeCode = msgTypeCode;
        }

        public String getMsgTypeDesc() {
            return msgTypeDesc;
        }

        public void setMsgTypeDesc(String msgTypeDesc) {
            this.msgTypeDesc = msgTypeDesc;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getHuanxinId() {
            return huanxinId;
        }

        public void setHuanxinId(String huanxinId) {
            this.huanxinId = huanxinId;
        }

        public String getTargetUrl() {
            return targetUrl;
        }

        public void setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }




    public SystemMessageEntity() {
    }


    @Override
    public String toString() {
        return "SystemMessageEntity{" +
                "msgId='" + msgId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userId='" + userId + '\'' +
                ", msgTitle='" + msgTitle + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", msgTypeCode='" + msgTypeCode + '\'' +
                ", msgTypeDesc='" + msgTypeDesc + '\'' +
                ", targetId='" + targetId + '\'' +
                ", huanxinId='" + huanxinId + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", action='" + action + '\'' +
                ", userName='" + userName + '\'' +
                ", isRead=" + isRead +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
