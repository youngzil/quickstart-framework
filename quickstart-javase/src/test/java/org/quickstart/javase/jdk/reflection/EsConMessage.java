package org.quickstart.javase.jdk.reflection;

import java.util.Date;

public class EsConMessage extends EsMessage {
    public EsConMessage() {

    }

    public EsConMessage(String msgId, String node) {
        setMsgId(msgId);
        setNode(node);
    }

    public String isSuccess = "true";
    private String excType;
    private String exception;
    private String cousumeGroup;

    private Date consumeTime;

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getExcType() {
        return excType;
    }

    public void setExcType(String excType) {
        this.excType = excType;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getCousumeGroup() {
        return cousumeGroup;
    }

    public void setCousumeGroup(String cousumeGroup) {
        this.cousumeGroup = cousumeGroup;
    }

    @Override
    public String toString() {
        return "EsConMessage [isSuccess=" + isSuccess + ", excType=" + excType + ", exception=" + exception + ", cousumeGroup=" + cousumeGroup + ", consumeTime=" + consumeTime + ", toString()="
                + super.toString() + "]";
    }

}
