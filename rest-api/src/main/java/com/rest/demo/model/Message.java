package com.rest.demo.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Message {
    private String id;
    private String senderName;
    private String text;

    public Message(String id, String senderName, String text) {
        this.id = id;
        this.senderName = senderName;
        this.text = text;
    }
}
