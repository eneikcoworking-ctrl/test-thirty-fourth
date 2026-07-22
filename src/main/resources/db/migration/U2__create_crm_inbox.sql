-- Rollback Migration: U2__create_crm_inbox.sql
-- Description: Drop tables for messages, threads, and contacts in correct reverse dependency order

DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS threads CASCADE;
DROP TABLE IF EXISTS contacts CASCADE;
