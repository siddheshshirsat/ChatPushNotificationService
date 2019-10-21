package com.chat.pushnotification.model;

import lombok.Value;

@Value
public class ServerMessage {
    private String from;
    private String to;
    private String content;
}
