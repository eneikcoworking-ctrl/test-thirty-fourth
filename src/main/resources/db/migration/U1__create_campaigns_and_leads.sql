-- U1__create_campaigns_and_leads.sql
-- Drop indexes
DROP INDEX IF EXISTS idx_leads_status;
DROP INDEX IF EXISTS idx_leads_campaign_id;
DROP INDEX IF EXISTS idx_campaigns_status;

-- Drop tables
DROP TABLE IF EXISTS leads;
DROP TABLE IF EXISTS campaigns;
