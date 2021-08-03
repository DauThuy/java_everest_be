package com.example.demo.model.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoDto {
    private Integer accountId;
    private String nameAccount;
    private String email;
    private Integer roleId;
    private String token;
}
