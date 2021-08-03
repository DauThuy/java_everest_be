package com.example.demo.model.dto.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDto {
    private Integer campaignId;
    private String campaignName;
    private Integer campaignStatus;

    private Integer usedAmount;
    private Float usageRate;
    private Integer overalBudget;
    private Date startDate;
    private Date endDate;

    private Integer bidAmount;
    private String title;
    private String description;
    private String preview;
    private String finalUrl;
}
