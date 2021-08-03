package com.example.demo.repository;
import com.example.demo.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    List<Campaign> findAllBy();
    Campaign findByCampaignId(int id);
}
