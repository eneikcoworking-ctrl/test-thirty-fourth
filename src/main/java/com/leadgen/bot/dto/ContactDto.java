package com.leadgen.bot.dto;

import java.util.UUID;

public class ContactDto {
    private UUID id;
    private String name;
    private String telegramUsername;
    private String phoneNumber;
    private String avatarUrl;
    private String createdAt;

    public ContactDto() {}

    public ContactDto(UUID id, String name, String telegramUsername, String phoneNumber, String avatarUrl, String createdAt) {
        this.id = id;
        this.name = name;
        this.telegramUsername = telegramUsername;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    public void setTelegramUsername(String telegramUsername) {
        this.telegramUsername = telegramUsername;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
