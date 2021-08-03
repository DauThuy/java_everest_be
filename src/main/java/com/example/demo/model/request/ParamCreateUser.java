package com.example.demo.model.request;

import lombok.*;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParamCreateUser {
    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    @Size(min = 6, message = "Pasword must be at least 6 characters")
    private String userPassword;

    @NotNull
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String emailAddress;

    @NotNull
    @NotEmpty
    private Integer roleId;
}

