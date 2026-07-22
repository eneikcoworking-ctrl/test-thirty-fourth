package com.example.leadgen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class LeadGenApplicationTests {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Test
    public void contextLoads() {
        // Simple context loading check
    }

    @Test
    @Transactional
    public void testCreateAndRetrieveCampaign() {
        Campaign campaign = new Campaign();
        campaign.setName("Telegram Cold Outreach 2026");
        campaign.setSystemPrompt("You are a helpful business assistant Olivia Coss...");
        campaign.setStatus(CampaignStatus.ACTIVE);
        campaign.setDailyLimit(20);

        Campaign saved = campaignRepository.save(campaign);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCreatedAt()).isNotNull();
        assertThat(saved.getUpdatedAt()).isNotNull();

        Optional<Campaign> retrieved = campaignRepository.findById(saved.getId());
        assertThat(retrieved).isPresent();
        assertThat(retrieved.get().getName()).isEqualTo("Telegram Cold Outreach 2026");
        assertThat(retrieved.get().getStatus()).isEqualTo(CampaignStatus.ACTIVE);
        assertThat(retrieved.get().getDailyLimit()).isEqualTo(20);
    }

    @Test
    @Transactional
    public void testCreateAndRetrieveLead() {
        Campaign campaign = new Campaign("Campaign A", "System prompt placeholder", CampaignStatus.PAUSED, 15);
        Campaign savedCampaign = campaignRepository.save(campaign);

        Lead lead = new Lead(savedCampaign, "@telegram_user_1", LeadStatus.PENDING);
        Lead savedLead = leadRepository.save(lead);

        assertThat(savedLead.getId()).isNotNull();
        assertThat(savedLead.getCampaign().getId()).isEqualTo(savedCampaign.getId());

        List<Lead> leads = leadRepository.findByCampaignId(savedCampaign.getId());
        assertThat(leads).hasSize(1);
        assertThat(leads.get(0).getContactIdentifier()).isEqualTo("@telegram_user_1");
    }

    @Test
    @Transactional
    public void testCampaignConstraints_dailyLimitNegative() {
        Campaign campaign = new Campaign("Campaign Invalid", "Prompt", CampaignStatus.ACTIVE, -5);
        // H2 in PostgreSQL compatibility mode will trigger constraint violation on insert/update due to chk_campaign_daily_limit
        assertThrows(DataIntegrityViolationException.class, () -> {
            campaignRepository.saveAndFlush(campaign);
        });
    }

    @Test
    @Transactional
    public void testLeadConstraints_uniqueCampaignLead() {
        Campaign campaign = new Campaign("Campaign for Unique", "Prompt", CampaignStatus.ACTIVE, 10);
        Campaign savedCampaign = campaignRepository.save(campaign);

        Lead lead1 = new Lead(savedCampaign, "@duplicate_user", LeadStatus.PENDING);
        leadRepository.saveAndFlush(lead1);

        Lead lead2 = new Lead(savedCampaign, "@duplicate_user", LeadStatus.CONTACTED);
        // Unique constraint uq_campaign_lead (campaign_id, contact_identifier)
        assertThrows(DataIntegrityViolationException.class, () -> {
            leadRepository.saveAndFlush(lead2);
        });
    }
}
