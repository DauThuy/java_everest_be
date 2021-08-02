package com.example.demo.model.mapper;
import com.example.demo.entity.Account;
import com.example.demo.model.dto.account.UserDto;
import com.example.demo.model.request.accountRequest.ParamCreateUser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;

public class AccountMapper {
    public static UserDto toUserDto (Account account) {
        UserDto userDto = new UserDto();
        userDto.setUserId(account.getAccountId());
        userDto.setUserName(account.getAccountName());
        userDto.setUserPassword(account.getAccountPassword());
        userDto.setUserEmailAddress(account.getEmailAddress());
        userDto.setUserImage(account.getAccountImage());
        userDto.setUserStatus(account.getAccountStatus());
        userDto.setApprovalDate(account.getApprovalDate());
        userDto.setDateCreated(account.getDateCreated());
        userDto.setDateModified(account.getDateModified());
        userDto.setRoleId(account.getRoleId());
        userDto.setIsDelete(account.getIsDelete());
        return userDto;
    }
    public static Account toCreateAccount(ParamCreateUser request) {
        Account account = new Account();
        String avatarDefault = "https://i.pinimg.com/736x/24/3e/03/243e034e3fc44551e39264c60937b8ab.jpg";

        account.setAccountId(account.getAccountId());
        account.setAccountName(request.getUserName());
        String hash = BCrypt.hashpw(request.getUserPassword(), BCrypt.gensalt(12));
        account.setAccountPassword(hash);
        account.setEmailAddress(request.getEmailAddress());
        account.setAccountImage(avatarDefault);
        account.setAccountStatus(0);

        account.setRoleId(request.getRoleId());
        account.setIsDelete(false);

        return account;
    }
}