package com.example.demo.model.mapper;
import com.example.demo.entity.Account;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.ParamCreateUser;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;

public class AccountMapper {
    @Autowired
    private AccountRepository accountRepository;

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
    public static Account toCreateAccount(ParamCreateUser req) {
        Account account = new Account();
        Date now = new Date();

        account.setAccountId(account.getAccountId());
        account.setAccountName(req.getUserName());
        String hash = BCrypt.hashpw(req.getUserPassword(), BCrypt.gensalt(12));
        account.setAccountPassword(hash);
        account.setEmailAddress(req.getEmailAddress());
        account.setAccountImage("ava.png");
        account.setAccountStatus(0);
        account.setApprovalDate(now);
        account.setDateCreated(now);
        account.setDateModified(now);
        account.setRoleId(req.getRoleId());
        account.setIsDelete(false);

        return account;
    }
}