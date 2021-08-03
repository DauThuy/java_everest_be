package com.example.demo.service;
import com.example.demo.entity.Campaign;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CampaignService {
    List<Campaign> getListCampaigns();
}
