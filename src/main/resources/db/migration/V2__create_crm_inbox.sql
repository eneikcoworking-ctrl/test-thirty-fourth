-- Migration: V2__create_crm_inbox.sql
-- Description: Create tables for contacts, threads, and messages with proper constraints, foreign keys, and indexes.

CREATE TABLE contacts (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    telegram_username VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50),
    avatar_url TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE threads (
    id UUID PRIMARY KEY,
    contact_id UUID NOT NULL REFERENCES contacts(id) ON DELETE CASCADE,
    status VARCHAR(50) NOT NULL, -- human_attention, ai_assistant, pending
    tag VARCHAR(50), -- subscription_issue, sales_inquiry, technical_support, new_lead, critical_incident
    unread_count INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE messages (
    id UUID PRIMARY KEY,
    thread_id UUID NOT NULL REFERENCES threads(id) ON DELETE CASCADE,
    sender_type VARCHAR(50) NOT NULL, -- contact, human_operator, ai_assistant
    sender_name VARCHAR(255),
    text TEXT NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Indices for performance
CREATE INDEX idx_threads_contact_id ON threads(contact_id);
CREATE INDEX idx_threads_status ON threads(status);
CREATE INDEX idx_threads_tag ON threads(tag);
CREATE INDEX idx_messages_thread_id ON messages(thread_id);
CREATE INDEX idx_messages_timestamp ON messages(timestamp DESC);

-- SQL Comments for Data Catalog Metadata
COMMENT ON TABLE contacts IS 'Stores information about Telegram contacts who interact with the bot. Owner: BARCAN-TAG-02. Lineage: Telegram Client Core integration.';
COMMENT ON COLUMN contacts.id IS 'Primary key. Unique UUID identifier for the contact.';
COMMENT ON COLUMN contacts.name IS 'Display name of the Telegram contact.';
COMMENT ON COLUMN contacts.telegram_username IS 'Telegram username of the contact, without the @ symbol.';
COMMENT ON COLUMN contacts.phone_number IS 'Phone number of the Telegram contact.';
COMMENT ON COLUMN contacts.avatar_url IS 'URL pointing to the contact''s Telegram profile picture.';
COMMENT ON COLUMN contacts.created_at IS 'Timestamp when the contact record was created.';

COMMENT ON TABLE threads IS 'Represents chat conversation threads with Telegram contacts. Owner: BARCAN-TAG-02. Lineage: CRM Live Chat & AI Override Engine.';
COMMENT ON COLUMN threads.id IS 'Primary key. Unique UUID identifier for the thread.';
COMMENT ON COLUMN threads.contact_id IS 'Foreign key referencing the associated contact.';
COMMENT ON COLUMN threads.status IS 'The current handling status: human_attention, ai_assistant, pending.';
COMMENT ON COLUMN threads.tag IS 'The tag associated with the thread (e.g. sales_inquiry, technical_support).';
COMMENT ON COLUMN threads.unread_count IS 'The number of unread messages in the thread.';
COMMENT ON COLUMN threads.created_at IS 'Timestamp when the thread was created.';
COMMENT ON COLUMN threads.updated_at IS 'Timestamp when the thread was last updated.';

COMMENT ON TABLE messages IS 'Stores message history within threads. Owner: BARCAN-TAG-02. Lineage: CRM Live Chat & Operator Console.';
COMMENT ON COLUMN messages.id IS 'Primary key. Unique UUID identifier for the message.';
COMMENT ON COLUMN messages.thread_id IS 'Foreign key referencing the associated thread.';
COMMENT ON COLUMN messages.sender_type IS 'The type of sender: contact, human_operator, ai_assistant.';
COMMENT ON COLUMN messages.sender_name IS 'The name of the sender (either contact name, operator name, or AI Agent''s name).';
COMMENT ON COLUMN messages.text IS 'The content of the message.';
COMMENT ON COLUMN messages.timestamp IS 'Timestamp when the message was sent.';
