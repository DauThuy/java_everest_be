package com.example.demo.model.mapper;

import com.example.demo.entity.Campaign;
import com.example.demo.model.dto.CampaignDto;

public class CampaignMapper {
    public static CampaignDto toCampaignDto(Campaign campaign) {
        CampaignDto campaignMapper = new CampaignDto();
        campaignMapper.setCampaignName(campaign.getCampaignName());

        return campaignMapper;
    }
}
