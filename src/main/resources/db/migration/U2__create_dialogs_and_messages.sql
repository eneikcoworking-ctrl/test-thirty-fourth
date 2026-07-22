-- ==========================================
-- Flyway Undo Migration: U2__create_dialogs_and_messages.sql
-- Description: Drops the tables created in V2__create_dialogs_and_messages.sql.
-- Owner: BARCAN-TAG-08 Data Engineering
-- ==========================================

DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS dialogs CASCADE;
