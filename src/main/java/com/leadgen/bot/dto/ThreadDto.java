package com.leadgen.bot.dto;

import java.util.UUID;

public class ThreadDto {
    private UUID id;
    private ContactDto contact;
    private String status; // human_attention, ai_assistant, pending
    private String tag;
    private Integer unreadCount;
    private MessageDto lastMessage;
    private String createdAt;
    private String updatedAt;

    public ThreadDto() {}

    public ThreadDto(UUID id, ContactDto contact, String status, String tag, Integer unreadCount, MessageDto lastMessage, String createdAt, String updatedAt) {
        this.id = id;
        this.contact = contact;
        this.status = status;
        this.tag = tag;
        this.unreadCount = unreadCount;
        this.lastMessage = lastMessage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public MessageDto getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageDto lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
