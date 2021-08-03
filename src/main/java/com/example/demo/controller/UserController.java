package com.example.demo.controller;

import com.example.demo.model.dto.account.AccountDto;
import com.example.demo.model.dto.token.TokenResponse;
import com.example.demo.model.dto.account.UserDto;
import com.example.demo.model.request.accountRequest.ParamChangePassword;
import com.example.demo.model.request.accountRequest.ParamCreateUser;
import com.example.demo.model.request.accountRequest.ParamAdminUpdateUser;
import com.example.demo.model.request.accountRequest.ParamUserUpdateUser;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public TokenResponse getInfo(@Valid @RequestBody AccountDto dto) {
            return accountService.login(dto);
    }

    @PostMapping(value = "/user/token")
    public UserDto getInfoUserFromToken(@Valid @RequestBody String token) {
        return accountService.getInfoUserFromToken(token);
    }

    @GetMapping(value="/users")
    public ResponseEntity<?> getListUsers() {
        List<UserDto> users = accountService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value="users/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable int id) {
        System.out.println(id);
        UserDto user =  accountService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody ParamCreateUser req) {
        UserDto account = accountService.createUser(req);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserByUser(@Valid @RequestBody ParamUserUpdateUser req, @PathVariable int id) {
        UserDto account = accountService.updateUserByUser(req, id);
        return ResponseEntity.ok(account);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserByAdmin(@Valid @RequestBody ParamAdminUpdateUser req, @PathVariable int id) {
        UserDto account = accountService.updateUserByAdmin(req, id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("users/change-password/{id}")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ParamChangePassword req, @PathVariable int id) {
        accountService.changePassword(req, id);
        System.out.println("Password has changed");
        return ResponseEntity.ok("Changed password");
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        accountService.deleteUserById(id);
        System.out.println("Deleted");
        return ResponseEntity.ok("Deleted user");
    }
}
