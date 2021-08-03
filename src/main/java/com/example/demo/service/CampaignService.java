package com.example.demo.service;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.dto.campaign.ResponseForBannerDto;
import com.example.demo.model.dto.campaign.ResponseForClickDto;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CampaignService {
    List<CampaignDto> getListCampaigns();
    CampaignDto getCampaignById(int id);
    CampaignDto createCampaign(CampaignRequest request);
    CampaignDto updateCampaign(CampaignRequest request, int id);
    void deleteCampaignById(int id);
    ResponseForClickDto getViews(int id);
    List<ResponseForBannerDto> getBanners();
}
