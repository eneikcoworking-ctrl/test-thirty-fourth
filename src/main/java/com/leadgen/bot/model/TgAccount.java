package com.leadgen.bot.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tg_accounts")
public class TgAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proxy_id")
    private Proxy proxy;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    private String username;

    @Column(name = "session_format")
    private String sessionFormat;

    @Column(name = "session_data", columnDefinition = "TEXT")
    private String sessionData;

    @Column(nullable = false)
    private String status = "ACTIVE";

    @Column(name = "daily_dialogue_limit", nullable = false)
    private Integer dailyDialogueLimit = 15;

    @Column(name = "current_daily_dialogues_count", nullable = false)
    private Integer currentDailyDialoguesCount = 0;

    @Column(name = "last_sync_at")
    private Instant lastSyncAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Instant updatedAt;

    // Default Constructor
    public TgAccount() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionFormat() {
        return sessionFormat;
    }

    public void setSessionFormat(String sessionFormat) {
        this.sessionFormat = sessionFormat;
    }

    public String getSessionData() {
        return sessionData;
    }

    public void setSessionData(String sessionData) {
        this.sessionData = sessionData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDailyDialogueLimit() {
        return dailyDialogueLimit;
    }

    public void setDailyDialogueLimit(Integer dailyDialogueLimit) {
        this.dailyDialogueLimit = dailyDialogueLimit;
    }

    public Integer getCurrentDailyDialoguesCount() {
        return currentDailyDialoguesCount;
    }

    public void setCurrentDailyDialoguesCount(Integer currentDailyDialoguesCount) {
        this.currentDailyDialoguesCount = currentDailyDialoguesCount;
    }

    public Instant getLastSyncAt() {
        return lastSyncAt;
    }

    public void setLastSyncAt(Instant lastSyncAt) {
        this.lastSyncAt = lastSyncAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
