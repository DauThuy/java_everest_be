package com.example.demo.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmailAddress;
    private String userImage;
    private Integer userStatus;
    private Date approvalDate;
    private Date dateCreated;
    private Date dateModified;
    private Integer roleId;
    private Boolean isDelete;
}
