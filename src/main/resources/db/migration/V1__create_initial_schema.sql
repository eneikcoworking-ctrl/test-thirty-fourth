-- Migration: V1__create_initial_schema.sql
-- Description: Create initial schema for users, proxies, and tg_accounts with proper constraints and foreign keys

CREATE TABLE proxies (
    id BIGSERIAL PRIMARY KEY,
    host VARCHAR(255) NOT NULL,
    port INTEGER NOT NULL,
    protocol VARCHAR(50) NOT NULL, -- SOCKS5, HTTP
    username VARCHAR(255),
    password VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    default_proxy_id BIGINT REFERENCES proxies(id) ON DELETE SET NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tg_accounts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    proxy_id BIGINT REFERENCES proxies(id) ON DELETE SET NULL,
    phone_number VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(255),
    session_format VARCHAR(50), -- SESSION, TDATA
    session_data TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE', -- ACTIVE, SPAM_BLOCK_TEMP, BANNED, REAUTH_REQUIRED
    daily_dialogue_limit INTEGER NOT NULL DEFAULT 15,
    current_daily_dialogues_count INTEGER NOT NULL DEFAULT 0,
    last_sync_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
