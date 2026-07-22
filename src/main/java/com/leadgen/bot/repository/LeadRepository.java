package com.leadgen.bot.repository;

import com.leadgen.bot.model.Campaign;
import com.leadgen.bot.model.Lead;
import com.leadgen.bot.model.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByCampaignId(Long campaignId);
    List<Lead> findByStatus(LeadStatus status);
    Optional<Lead> findByCampaignIdAndContactIdentifier(Long campaignId, String contactIdentifier);
}
