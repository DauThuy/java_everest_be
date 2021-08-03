package com.example.demo.util;

import com.example.demo.model.dto.campaign.CampaignDto;
import java.util.Comparator;

public class CampaignUtils implements Comparator <CampaignDto> {

    @Override
    public int compare(CampaignDto campaignDto1, CampaignDto campaignDto2) {
        return campaignDto2.getBidAmount() - campaignDto1.getBidAmount();
    }
}
