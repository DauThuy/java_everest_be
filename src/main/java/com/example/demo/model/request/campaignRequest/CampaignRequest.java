package com.example.demo.model.request.campaignRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CampaignRequest {
    @NotNull
    private String campaignName;

    @NotNull
    private Integer campaignStatus;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private Integer overalBudget;

    @NotNull
    private Integer bidAmount;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String preview;

    @NotNull
    private String finalUrl;

    @NotNull
    private Integer accountId;
}