package com.nuc.calvin.headline.model;

public class Message {

    private int messageImgId;
    private String messageName;
    private String messageCount;

    public Message(int messageImgId, String messageName, String messageCount) {
        this.messageImgId = messageImgId;
        this.messageName = messageName;
        this.messageCount = messageCount;
    }

    public int getMessageImgId() {
        return messageImgId;
    }

    public void setMessageImgId(int messageImgId) {
        this.messageImgId = messageImgId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }
}
