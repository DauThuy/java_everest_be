package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.model.dto.TokenResponse;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.ParamChangePassword;
import com.example.demo.model.request.ParamCreateUser;
import com.example.demo.model.request.ParamAdminUpdateUser;
import com.example.demo.model.request.ParamUserUpdateUser;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@FrameworkEndpoint

public class UserController {
    @Autowired
    private AccountService accountService;

//    ConsumerTokenServices tokenServices;

//    @CrossOrigin(origins = "*")
//    @PostMapping("users/delete")
//    public ResponseEntity<?> revokeToken(@Valid @RequestBody String tokenId) {
//        tokenServices.revokeToken(tokenId);
////        consumerTokenServices().revokeToken(tokenId);
//        return ResponseEntity.ok("success");
//    }

//    ConsumerTokenServices consumerTokenServices() {
//        return new ConsumerTokenServices() {
//            @Override
//            public boolean revokeToken(String tokenId) {
//                tokenServices.revokeToken(tokenId);
//                return true;
//            }
//        };
//    }

    @DeleteMapping(value = "/delete/token/{id}")
    public ResponseEntity<?>  removeToken(@PathVariable int id) {
        accountService.revokeToken(id);
        return ResponseEntity.ok("deleted token");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login")
    public TokenResponse getInfo(@Valid @RequestBody AccountDto dto) {
            return accountService.login(dto);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/user/token")
    public UserDto getInfoUserFromToken(@Valid @RequestBody String token) {
        return accountService.getInfoUserFromToken(token);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value="/users")
    public ResponseEntity<?> getListUsers() {
        List<UserDto> users = accountService.getAllUser();
        return ResponseEntity.ok(users);
    }


    @CrossOrigin(origins = "*")
    @GetMapping(value="users/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable int id) {
        System.out.println(id);
        UserDto user =  accountService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody ParamCreateUser req) {
        UserDto account = accountService.createUser(req);
        return ResponseEntity.ok(account);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserByUser(@Valid @RequestBody ParamUserUpdateUser req, @PathVariable int id) {
        UserDto account = accountService.updateUserByUser(req, id);
        return ResponseEntity.ok(account);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserByAdmin(@Valid @RequestBody ParamAdminUpdateUser req, @PathVariable int id) {
        UserDto account = accountService.updateUserByAdmin(req, id);
        return ResponseEntity.ok(account);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("users/change-password/{id}")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ParamChangePassword req, @PathVariable int id) {
        accountService.changePassword(req, id);
        System.out.println("Password has changed");
        return ResponseEntity.ok("Changed password");
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        accountService.deleteUserById(id);
        System.out.println("Deleted");
        return ResponseEntity.ok("Deleted user");
    }


//    public ResponseEntity<?> revokeToken(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        if (authorization != null && authorization.contains("Bearer")){
//            String tokenId = authorization.substring("Bearer".length()+1);
//            tokenServices.revokeToken(tokenId);
//        }
//        return ResponseEntity.ok("Delete token");
//    }
//    @CrossOrigin(origins = "*")
//    @PostMapping("users/delete")
//    public String revokeToken(@PathVariable String tokenId) {
//        tokenServices.revokeToken(tokenId);
//        return tokenId;
//    }

}
