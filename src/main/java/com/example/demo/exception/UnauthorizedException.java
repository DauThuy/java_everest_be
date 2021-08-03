package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("Your username or password is not right, please check again");
    }
//    public UnauthorizedException(String msg) {
//        super(msg);
//    }
}
