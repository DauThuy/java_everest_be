package com.example.demo.service;

import com.example.demo.constant.MessageError;
import com.example.demo.constant.Quantitative;
import com.example.demo.entity.Campaign;
import com.example.demo.enums.Status;
import com.example.demo.exception.InValidDateException;
import com.example.demo.exception.InvalidBudgetBidAmountException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.dto.campaign.ResponseForBannerDto;
import com.example.demo.model.dto.campaign.ResponseForClickDto;
import com.example.demo.model.mapper.CampaignMapper;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.utils.CampaignUtils;
import com.example.demo.utils.DateConditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public List<CampaignDto> getListCampaigns() {
        List<CampaignDto> campaignDtos = new ArrayList<>();
        List<Campaign> campaigns = campaignRepository.findAllBy();
        if (campaigns == null) {
            throw new NotFoundException(MessageError.NO_CAMPAIGN_EXIST);
        }
        for (Campaign campaign : campaigns) {
            if (!campaign.getIsDelete()) {
                campaignDtos.add(CampaignMapper.toCampaignDto(campaign));
            }
        }
        Collections.sort(campaignDtos, new CampaignUtils());
        return campaignDtos;
    }

    @Override
    public CampaignDto getCampaignById(int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        if (!campaignRepository.existsById(id) || campaign.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_CAMPAIGN);
        }
        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public CampaignDto createCampaign(CampaignRequest request) {
        Campaign campaign = CampaignMapper.toCreate(request);
        campaignRepository.save(campaign);
        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public CampaignDto updateCampaign(CampaignRequest request, int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        if (!campaignRepository.existsById(id) || campaign.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_CAMPAIGN);
        }
        if (!DateConditional.endDateConditional(request.getStartDate(), request.getEndDate())) {
            throw new InValidDateException();
        }
        if (request.getBidAmount() * request.getOveralBudget() <= 0 || request.getOveralBudget() < request.getBidAmount()) {
            throw new InvalidBudgetBidAmountException();
        }
        campaign.setCampaignName(request.getCampaignName());
        campaign.setCampaignStatus(request.getCampaignStatus());

        if (!DateConditional.endDateConditional(request.getStartDate(), request.getEndDate())) {
            throw new InValidDateException();
        }
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());

        campaign.setOveralBudget(request.getOveralBudget());
        campaign.setBidAmount(request.getBidAmount());

        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setPreview(request.getPreview());
        campaign.setFinalUrl(request.getFinalUrl());

        campaignRepository.save(campaign);
        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public void deleteCampaignById(int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        if (campaign == null || campaign.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_CAMPAIGN);
        }
        campaign.setIsDelete(true);
        campaignRepository.save(campaign);
    }

    @Override
    public ResponseForClickDto getViews(int id) {
        ResponseForClickDto responseForClickDto = new ResponseForClickDto();
        Campaign campaign = campaignRepository.findByCampaignId(id);
        if (campaign == null || campaign.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_CAMPAIGN);
        }
        int bidAmount = campaign.getBidAmount();
        int budget = campaign.getOveralBudget();
        int clicks = campaign.getClicks();
        int usedAmount = campaign.getUsedAmount();

        clicks = clicks + 1;
        usedAmount = usedAmount + clicks * bidAmount;

        responseForClickDto.setFinalUrl(campaign.getFinalUrl());

        float usageRate = (usedAmount * 100.0f) / budget;
        usageRate = (float) Math.floor(usageRate * 100) / 100;

        campaign.setUsedAmount(usedAmount);
        campaign.setUsageRate(usageRate);

        campaignRepository.save(campaign);

        return responseForClickDto;
    }

    @Override
    public List<ResponseForBannerDto> getBanners() {
        List<Campaign> campaigns = campaignRepository.findAllBy();

        if (campaigns == null) {
            throw new NotFoundException(MessageError.NO_CAMPAIGN_EXIST);
        }
        List<Campaign> campaignSortedByBidAmounts = new ArrayList<>();
        List<ResponseForBannerDto> banners = new ArrayList<>();

        for (Campaign campaign : campaigns) {
            int overalBudget = campaign.getOveralBudget();
            int usedAmount = campaign.getUsedAmount();
            int bidAmount = campaign.getBidAmount();
            int ACTIVE = Status.ACTIVE.getValueActive();

            if (overalBudget - usedAmount >= bidAmount && campaign.getCampaignStatus() == ACTIVE) {
                campaignSortedByBidAmounts.add(campaign);
            }
        }

        Comparator<Campaign> comparator = Comparator.comparing(Campaign::getBidAmount);
        Collections.sort(campaignSortedByBidAmounts, comparator.reversed());

        int countBanner = 0;
        for (Campaign campaign : campaignSortedByBidAmounts) {
            if (countBanner >= Quantitative.numberOfBanners) {
                break;
            }
            banners.add(new ResponseForBannerDto(campaign.getCampaignId(), campaign.getPreview()));
            countBanner++;
        }

        return banners;
    }
}
