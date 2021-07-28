package com.example.demo.controller;


import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @GetMapping(value="/campaigns")
    public ResponseEntity<?> getListCampaign() {
        List<CampaignDto> campaigns = campaignService.getListCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity<?> getCampaignById(@PathVariable int id) {
        CampaignDto campaignDto = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaignDto);
    }

    @PostMapping("/campaign")
    public ResponseEntity<?> createCampaign(@Valid @RequestBody CampaignRequest req) {
        CampaignDto campaignDto = campaignService.createCampaign(req);
        return ResponseEntity.ok(campaignDto);
    }

    @PutMapping("/campaign/{id}")
    public ResponseEntity<?> updateCampaign(@Valid @RequestBody CampaignRequest req, @PathVariable int id) {
        CampaignDto campaignDto = campaignService.updateCampaign(req, id);
        return ResponseEntity.ok(campaignDto);
    }

    @DeleteMapping("/campaign/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable int id) {
        campaignService.deleteCampaignById(id);
        return ResponseEntity.ok("deleted campaign " + id);
    }

    @GetMapping("/campaigns/{id}/click")
    public ResponseEntity<?> getViews(@PathVariable int id) {
        campaignService.getViews(id);
        return ResponseEntity.ok("clicked");
    }

    @GetMapping("/campaigns/banners")
    public ResponseEntity<?> getBanner(@PathVariable int id) {
        List<String> banners = campaignService.getBanners(id);
        return ResponseEntity.ok("get banner successfull" + getCampaignById(id));
    }
}
