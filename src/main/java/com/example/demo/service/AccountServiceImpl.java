package com.example.demo.service;

import com.example.demo.constant.MessageError;
import com.example.demo.entity.Token;
import com.example.demo.exception.InValidPasswordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.token.TokenResponse;
import com.example.demo.model.dto.account.UserDto;
import com.example.demo.model.mapper.AccountMapper;
import com.example.demo.model.request.accountRequest.ParamChangePassword;
import com.example.demo.model.request.accountRequest.ParamCreateUser;
import com.example.demo.model.request.accountRequest.ParamAdminUpdateUser;
import com.example.demo.model.request.accountRequest.ParamUserUpdateUser;
import com.example.demo.repository.TokenRepository;
import com.example.demo.utils.EmailValidate;
import com.example.demo.exception.InValidEmailException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.ProvideJwt;
import com.example.demo.model.dto.account.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ProvideJwt jwtProvider;

    Date now = new Date();

    @Override
    public UserDto getInfoUserFromToken(String token) {
        if (jwtProvider.validateToken(token) == null) {
            throw new UnauthorizedException();
        }
        return getUserById(jwtProvider.getUserIdFromJWT(token));
    }

    @Override
    public TokenResponse login(AccountDto dto) {
        if(!EmailValidate.validateEmail(dto.getEmail())) {
            throw new InValidEmailException();
        }
        Account user = accountRepository.findByEmailAddress(dto.getEmail());
        if (user == null || !encoder.matches(dto.getPassword(), user.getAccountPassword()) || user.getIsDelete()) {
            throw new UnauthorizedException();
        }
        String token = jwtProvider.generateTokenForEmployee(user);

        Token tokenUser = tokenRepository.findByAccountId(user.getAccountId());
        if (tokenUser != null) {
            tokenUser.setToken(token);
            tokenRepository.save(tokenUser);
        } else {
            Token newUserToken = new Token();
            newUserToken.setAccountId(user.getAccountId());
            newUserToken.setToken(token);
            tokenRepository.save(newUserToken);
        }

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(tokenUser.getToken());
        tokenResponse.setAccount_id(tokenUser.getAccountId());
        tokenResponse.setRole_id(user.getRoleId());

        return tokenResponse;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtos = new ArrayList<>();
        List<Account> accounts = accountRepository.findAllBy();

        if (accounts == null) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }

        for (Account account: accounts) {
            if (!account.getIsDelete()) {
                userDtos.add(AccountMapper.toUserDto(account));
            }
        }
        Collections.sort(userDtos, new UserUtils());

        return userDtos;
    }

    @Override
    public UserDto getUserById(int id) {
        Account account = accountRepository.findByAccountId(id);
        if (account == null || account.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        return AccountMapper.toUserDto(account);
    }

    @Override
    public void deleteUserById(int id) {
        Account account = accountRepository.findByAccountId(id);
        if (account == null || account.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        account.setIsDelete(true);
        accountRepository.save(account);
    }

    @Override
    public UserDto createUser(ParamCreateUser req) {
        List<Account> accounts = accountRepository.findAllBy();
        for (Account account: accounts) {
            if (account.getEmailAddress().equals(req.getEmailAddress())) {
                throw new DuplicateKeyException(MessageError.EMAIL_ALREADY_EXIST);
            }
            if (account == null) {
                break;
            }
        }
        Account account = AccountMapper.toCreateAccount(req);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto updateUserByUser(ParamUserUpdateUser req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        account.setAccountName(req.getUserName());
        account.setAccountImage(req.getUserImage());
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        account.setAccountName(req.getUserName());
        account.setRoleId(req.getRoleId());
        account.setDateModified(now);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto changePassword(ParamChangePassword req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        if (!encoder.matches(req.getOldPassword(), account.getAccountPassword()) ||
                !req.getNewPassWord().equals(req.getConfirmNewPassWord())) {
            throw new InValidPasswordException();
        }
        String hash = BCrypt.hashpw(req.getNewPassWord(), BCrypt.gensalt(12));
        account.setAccountPassword(hash);
        account.setDateModified(now);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }
}