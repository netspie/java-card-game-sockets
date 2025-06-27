/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dariuszlusnia.cardgame.server;

/**
 *
 * @author dariu
 */
public class Message {
    private Configure.MessageType messageType;
    private String content;

    public Message(Configure.MessageType messageType, String content) {
        this.messageType = messageType;
        this.content = content;
    }

    public Configure.MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(Configure.MessageType messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String serialize() {
        return messageType.name() + ";" + content;
    }

    public static Message deserialize(String messageString) {
        String[] deserializedMessage = messageString.split(";");
        return new Message(Configure.MessageType.valueOf(deserializedMessage[0]), deserializedMessage[1]);
    }
}