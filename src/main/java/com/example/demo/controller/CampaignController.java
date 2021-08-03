package com.example.demo.controller;

import com.example.demo.entity.Campaign;
import com.example.demo.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @GetMapping(value="/campaign")
    public ResponseEntity<?> getListCampaign() {
        List<Campaign> campaigns = campaignService.getListCampaigns();
        return ResponseEntity.ok(campaigns);
    }
}
