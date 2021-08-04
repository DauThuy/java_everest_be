package com.example.demo.model.mapper;
import com.example.demo.constant.Url;
import com.example.demo.entity.Account;
import com.example.demo.model.dto.account.UserDto;
import com.example.demo.model.request.accountRequest.ParamCreateUser;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
        String avatarDefault = Url.ACCOUNT_IMAGE;

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