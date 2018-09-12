package org.quickstart.javase.jdk.reflection;

import java.util.Date;

public class EsMessage {

    private String msgId;

    private String node;

    private String ip;

    private String hostName;

    private String serverName;

    private long costTime;

    private String operator;

    private String others;

    private String searchkey;

    private String phone;

    private String content;

    private String userInfo;

    private String common;

    private String msgHead;

    private String destination;

    private String tag;

    private Date createDate;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getMsgHead() {
        return msgHead;
    }

    public void setMsgHead(String msgHead) {
        this.msgHead = msgHead;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "EsMessage [msgId=" + msgId + ", node=" + node + ", ip=" + ip + ", hostName=" + hostName + ", serverName=" + serverName + ", costTime=" + costTime + ", operator=" + operator
                + ", others=" + others + ", searchkey=" + searchkey + ", phone=" + phone + ", content=" + content + ", userInfo=" + userInfo + ", common=" + common + ", msgHead=" + msgHead
                + ", destination=" + destination + ", tag=" + tag + ", createDate=" + createDate + "]";
    }

}
