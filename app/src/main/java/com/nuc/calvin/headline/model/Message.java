package com.nuc.calvin.headline.model;

public class Message {

    private int messageImgId;
    private String messageName;

    public Message(int messageImgId, String messageName) {
        this.messageImgId = messageImgId;
        this.messageName = messageName;
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



}
