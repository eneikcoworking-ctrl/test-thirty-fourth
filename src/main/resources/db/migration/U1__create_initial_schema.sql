-- Rollback Migration: U1__create_initial_schema.sql
-- Description: Drop initial schema for users, proxies, and tg_accounts in correct reverse dependency order

DROP TABLE IF EXISTS tg_accounts CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS proxies CASCADE;
