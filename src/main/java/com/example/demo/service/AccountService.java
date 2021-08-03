package com.example.demo.service;

import com.example.demo.model.dto.account.AccountDto;
import com.example.demo.model.dto.token.TokenResponse;
import com.example.demo.model.dto.account.UserDto;
import com.example.demo.model.request.accountRequest.ParamChangePassword;
import com.example.demo.model.request.accountRequest.ParamCreateUser;
import com.example.demo.model.request.accountRequest.ParamAdminUpdateUser;
import com.example.demo.model.request.accountRequest.ParamUserUpdateUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    TokenResponse login(AccountDto dto);
    UserDto getInfoUserFromToken(String token);
    List<UserDto> getAllUser();
    UserDto getUserById(int id);
    String deleteUserById(int id);
    UserDto createUser(ParamCreateUser req);
    UserDto updateUserByUser(ParamUserUpdateUser req, int id);
    UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id);
    UserDto changePassword(ParamChangePassword req, int id);

}