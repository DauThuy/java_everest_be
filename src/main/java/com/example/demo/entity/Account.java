package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name="account_name", nullable = false)
    private String accountName;

    @Column(name="account_password", nullable = false)
    private String accountPassword;

    @Column(name="email_address", unique=true, nullable = false)
    private String emailAddress;

    @Column(name="account_image", columnDefinition = "varchar(255) default 'ava.png'", nullable = false)
    private String accountImage;

    @Column(name="account_status", nullable = false)
    private Integer accountStatus;

    @Column(name="approval_date", nullable = false)
    private Date approvalDate;

    @Column(name="date_created", nullable = false)
    private Date dateCreated;

    @Column(name="date_modified", nullable = false)
    private Date dateModified;

    @Column(name="role_id", nullable = false)
    private Integer roleId;

    @Column(name="is_delete", columnDefinition = "boolean default false", nullable = false)
    private Boolean isDelete;
}