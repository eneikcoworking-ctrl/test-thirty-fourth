package com.leadgen.bot.repository;

import com.leadgen.bot.model.Campaign;
import com.leadgen.bot.model.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByStatus(CampaignStatus status);
}
