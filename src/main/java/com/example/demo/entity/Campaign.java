package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "campaigns")
@Table
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Integer campaignId;

    @Column(name = "campaign_name", nullable = false)
    private String campaignName;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @Column(name = "overal_budget", nullable = false)
    private Integer overalBudget;

    @Column(name = "bid_amount", nullable = false)
    private Integer bidAmount;

    @Column(name = "campaign_status", nullable = false)
    private Integer campaignStatus;

    @Column(name = "date_created")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @Column(name = "date_modified")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateModified;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name="is_delete", nullable = false) // columnDefinition = "boolean default false"
    private Boolean isDelete = false;

    @Column(name="creative_title", nullable = false)
    private String title;

    @Column(name="creative_description", nullable = false)
    private String description;

    @Column(name="creative_preview", nullable = false)
    private String preview;

    @Column(name="final_url", nullable = false)
    private String finalUrl;

    @Column(name="cost", columnDefinition = "integer default 0")
    private Integer cost;

    @Column(name="click", columnDefinition = "integer default 0")
    private Integer click;
}
