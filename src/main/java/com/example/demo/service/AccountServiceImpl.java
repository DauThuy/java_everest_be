package com.example.demo.service;

import com.example.demo.entity.Token;
import com.example.demo.exception.InValidPasswordException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.TokenResponse;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.AccountMapper;
import com.example.demo.model.request.ParamChangePassword;
import com.example.demo.model.request.ParamCreateUser;
import com.example.demo.model.request.ParamAdminUpdateUser;
import com.example.demo.model.request.ParamUserUpdateUser;
import com.example.demo.repository.TokenRepository;
import com.example.demo.util.EmailValidate;
import com.example.demo.exception.InValidEmailException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.ProvideJwt;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    public String deleteToken(String token) {
//        int idUser = jwtProvider.getUserIdFromJWT(token);
//        Account account = accountRepository.getById(idUser);
//        infoDto.setToken("");
       return "deleted token";
    }

    @Override
    public TokenResponse login(AccountDto dto) {
        if(!EmailValidate.validateEmail(dto.getEmail())) {
            throw new InValidEmailException();
        }
        Account user = accountRepository.findByEmailAddress(dto.getEmail());
        if (user == null || !encoder.matches(dto.getPassword(), user.getAccountPassword())) {
            throw new UnauthorizedException();
        }
        String token = jwtProvider.generateTokenForEmployee(user);

        Token tokenUser = tokenRepository.findByAccountId(user.getAccountId());
        if (tokenUser != null) {
            System.out.println("Test xem user da dang nhap lan nao chua");
            tokenUser.setToken(token);
            System.out.println("Test xem da update duoc token user");
            tokenRepository.save(tokenUser);
            System.out.println("Test xem co luu vao database duoc chua");
        } else {
            System.out.println("Vo day roi ha");
            Token newUserToken = new Token();
            newUserToken.setAccountId(user.getAccountId());
            newUserToken.setToken(token);
//            newUserToken.setTokenId(tokenRepository.findAll().size() + 1);
            System.out.println("Chan chan la vo day roi");
            tokenRepository.save(newUserToken);
        }

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(tokenUser.getToken());
        tokenResponse.setAccount_id(tokenUser.getAccountId());
        tokenResponse.setRole_id(user.getRoleId());

        return tokenResponse;
    }

    @Override
    public String revokeToken(int id) {
        Account account = accountRepository.findByAccountId(id);
//        Token userToken = tokenRepository.removeTokensByAccountId(account.getAccountId());
        Token userToken = tokenRepository.findByAccountId(account.getAccountId());
        userToken.setToken("");
        tokenRepository.save(userToken);
        return "removed token";
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtos = new ArrayList<>();
        List<Account> accounts = accountRepository.findAllBy();

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
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException("Not found user");
        }
        return AccountMapper.toUserDto(account);
    }

    @Override
    public String deleteUserById(int id) {
        Account account = accountRepository.findByAccountId(id);
        account.setIsDelete(true);
        accountRepository.save(account);

        return "account removed" + id + ": " + account.getAccountName();
    }

    @Override
    public UserDto createUser(ParamCreateUser req) {
        List<Account> accounts = accountRepository.findAllBy();
        for (Account account: accounts) {
            if (account.getEmailAddress().equals(req.getEmailAddress())) {
                throw new DuplicateKeyException("Email already exists");
            }
        }
        Account account = AccountMapper.toCreateAccount(req);
        account.setAccountId(accounts.size() + 1);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto updateUserByUser(ParamUserUpdateUser req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException("Not found user");
        }
        account.setAccountName(req.getUserName());
        account.setDateModified(now);
        accountRepository.save(account);

        return AccountMapper.toUserDto(account);
    }

    @Override
    public UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id) {
        Account account = accountRepository.findByAccountId(id);
        if (!accountRepository.existsById(id) || account.getIsDelete()) {
            throw new NotFoundException("Not found user");
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
            throw new NotFoundException("Not found user");
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

//    @Override
//    public InfoDto login(AccountDto dto) {
//        if(!EmailValidate.validateEmail(dto.getEmail())) {
//            throw new InValidEmailException();
//        }
//        Account user = accountRepository.findByEmailAddress(dto.getEmail());
//        if (user == null || !encoder.matches(dto.getPassword(), user.getAccountPassword())) {
//            throw new UnauthorizedException();
//        }
//        String token = jwtProvider.generateTokenForEmployee(user);
//
//        InfoDto info = new InfoDto(
//                user.getAccountId(),
//                user.getAccountName(),
//                user.getEmailAddress(),
//                user.getRoleId(),
//                token
//        );
//
//        Token tokenUser = tokenRepository.findByAccountId(info.getAccountId());
//        if (tokenUser != null) {
//            tokenUser.setToken(token);
//            tokenRepository.save(tokenUser);
//        } else {
//            Token newUserToken = new Token();
//            newUserToken.setAccountId(info.getAccountId());
//            newUserToken.setToken(token);
////            newUserToken.setTokenId(tokenRepository.findAll().size() + 1);
//            tokenRepository.save(newUserToken);
//        }
//
//        return info;
//    }


//    @Override
//    public void revokeToken(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        if (authorization != null && authorization.contains("Bearer")){
//            String tokenId = authorization.substring("Bearer".length()+1);
//            UserDto userDto = getInfoUserFromToken(tokenId);
//
//            Account account = accountRepository.findByAccountId(userDto.getUserId());
//            Token userToken = tokenRepository.removeTokensByAccountId(account.getAccountId());
//            tokenRepository.save(userToken);
//        }
//
//    }

}