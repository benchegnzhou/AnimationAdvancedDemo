package com.ztsc.house.bean.message;

import java.util.List;

/**
 * Created by benchengzhou on 2017/6/28  14:36 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： MyMessageResponseBody
 * 备    注： 系统消息
 */

public class MyMessageResponseBody {

    /**
     * code : 200
     * message :
     * result : {"messageList":[{"createTime":"2017-06-28 12:31:44.0","msgId":"11","userId":"005600","msgTitle":"新订单","msgContent":"接收到新的求助订单","msgTypeCode":"1002","msgTypeDesc":"求助订单","targetId":"happy","huanxinId":"asd","targetUrl":"www.baidu.com","action":"1","userName":"www.baidu.com"},{"createTime":"2017-06-28 16:10:50.0","msgId":"10","userId":"3659859","msgTitle":"新订单","msgContent":"接收到新的服务订单","msgTypeCode":"1002","msgTypeDesc":"服务订单","targetId":"happy","huanxinId":"asd","targetUrl":"www.baidu.com","action":"1","userName":"www.baidu.com"}]}
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
        private List<MessageListBean> messageList;

        public List<MessageListBean> getMessageList() {
            return messageList;
        }

        public void setMessageList(List<MessageListBean> messageList) {
            this.messageList = messageList;
        }



        public static class MessageListBean {
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

            private String createTime;

            private String msgId;

            private String userId;


            private String msgTitle;

            private String msgContent;

            private String msgTypeCode;

            private String msgTypeDesc;

            private String targetId;

            private String huanxinId;

            private String targetUrl;

            private String action;

            private String userName;
            //是不是已读

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
        }
    }
}
