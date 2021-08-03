package com.example.demo.service;

import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.TokenResponse;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.ParamChangePassword;
import com.example.demo.model.request.ParamCreateUser;
import com.example.demo.model.request.ParamAdminUpdateUser;
import com.example.demo.model.request.ParamUserUpdateUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    TokenResponse login(AccountDto dto);
//    public void revokeToken(HttpServletRequest request)

    String revokeToken(int id);
    UserDto getInfoUserFromToken(String token);
//    ConsumerTokenServices consumerTokenServices();
    List<UserDto> getAllUser();
    UserDto getUserById(int id);
    String deleteUserById(int id);
    UserDto createUser(ParamCreateUser req);
    UserDto updateUserByUser(ParamUserUpdateUser req, int id);
    UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id);
    UserDto changePassword(ParamChangePassword req, int id);
}