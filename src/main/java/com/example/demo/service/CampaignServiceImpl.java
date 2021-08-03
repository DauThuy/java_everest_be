package com.example.demo.service;
import com.example.demo.entity.Campaign;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

//    CampaignDto campaignDto = campaignRepository.findAll();
//    CampaignDto campaignDto1 = new CampaignDto(
//            user.getAccountName(),
//            user.getEmailAddress(),
//            user.getRole_id().getRoleId(),
//            user.getRole_id().getRoleName(),
//            token
//    );
    @Override
    public List<Campaign> getListCampaigns() {
        return campaignRepository.getAll();
    }
}
