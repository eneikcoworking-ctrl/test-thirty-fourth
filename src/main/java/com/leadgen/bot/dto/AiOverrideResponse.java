package com.leadgen.bot.dto;

import java.util.UUID;

public class AiOverrideResponse {
    private UUID threadId;
    private String previousStatus;
    private String currentStatus;
    private String updatedAt;

    public AiOverrideResponse() {}

    public AiOverrideResponse(UUID threadId, String previousStatus, String currentStatus, String updatedAt) {
        this.threadId = threadId;
        this.previousStatus = previousStatus;
        this.currentStatus = currentStatus;
        this.updatedAt = updatedAt;
    }

    public UUID getThreadId() {
        return threadId;
    }

    public void setThreadId(UUID threadId) {
        this.threadId = threadId;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
