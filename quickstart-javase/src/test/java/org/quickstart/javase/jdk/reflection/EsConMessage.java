package org.quickstart.javase.jdk.reflection;

import lombok.Data;

import java.util.Date;

@Data
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

}
