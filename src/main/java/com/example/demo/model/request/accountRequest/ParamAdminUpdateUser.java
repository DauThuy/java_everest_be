package com.example.demo.model.request.accountRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamAdminUpdateUser {
    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private Integer roleId;
}

