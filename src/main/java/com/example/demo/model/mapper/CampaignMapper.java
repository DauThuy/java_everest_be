package com.example.demo.model.mapper;

import com.example.demo.entity.Campaign;
import com.example.demo.exception.InValidBidAmountException;
import com.example.demo.exception.InValidDateException;
import com.example.demo.exception.InvalidBudgetBidAmountException;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.util.DateConditional;

public class CampaignMapper {
    public static CampaignDto toCampaignDto(Campaign campaign) {
        CampaignDto campaignMapper = new CampaignDto();
        campaignMapper.setCampaignId(campaign.getCampaignId());
        campaignMapper.setCampaignName(campaign.getCampaignName());
        campaignMapper.setCampaignStatus(campaign.getCampaignStatus());

        campaignMapper.setUsedAmount(campaign.getUsedAmount());
        campaignMapper.setUsageRate((campaign.getUsageRate()));

        campaignMapper.setOveralBudget(campaign.getOveralBudget());
        campaignMapper.setStartDate((campaign.getStartDate()));
        campaignMapper.setEndDate(campaign.getEndDate());
        campaignMapper.setBidAmount(campaign.getBidAmount());

        campaignMapper.setTitle(campaign.getTitle());
        campaignMapper.setDescription(campaign.getDescription());
        campaignMapper.setPreview(campaign.getPreview());
        campaignMapper.setFinalUrl(campaign.getFinalUrl());

        return campaignMapper;
    }

    public static Campaign toCreate (CampaignRequest request) {
        Campaign campaign = new Campaign();

        if (!DateConditional.endDateConditional(request.getStartDate(), request.getEndDate())) {
            throw new InValidDateException();
        }
        if (request.getOveralBudget() < request.getBidAmount()) {
            throw new InValidBidAmountException();
        }

        if (request.getBidAmount() * request.getOveralBudget() <= 0 || request.getOveralBudget() < request.getBidAmount()) {
            throw new InvalidBudgetBidAmountException();
        }
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());

        System.out.println("end date: " + request.getEndDate());
        System.out.println("end date: " + campaign.getEndDate());

        campaign.setCampaignName(request.getCampaignName());
        campaign.setOveralBudget(request.getOveralBudget());
        campaign.setBidAmount(request.getBidAmount());
        campaign.setCampaignStatus(request.getCampaignStatus());

        campaign.setAccountId(request.getAccountId());
        campaign.setIsDelete(campaign.getIsDelete());

        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setPreview(request.getPreview());
        campaign.setFinalUrl(request.getFinalUrl());

        campaign.setClicks(0);
        campaign.setUsedAmount(0);
        campaign.setUsageRate(0f);

        return campaign;
    }
}


