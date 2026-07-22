package com.leadgen.bot.dto;

import java.util.UUID;

public class MessageDto {
    private UUID id;
    private UUID threadId;
    private String senderType; // contact, human_operator, ai_assistant
    private String senderName;
    private String text;
    private String timestamp;

    public MessageDto() {}

    public MessageDto(UUID id, UUID threadId, String senderType, String senderName, String text, String timestamp) {
        this.id = id;
        this.threadId = threadId;
        this.senderType = senderType;
        this.senderName = senderName;
        this.text = text;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getThreadId() {
        return threadId;
    }

    public void setThreadId(UUID threadId) {
        this.threadId = threadId;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
