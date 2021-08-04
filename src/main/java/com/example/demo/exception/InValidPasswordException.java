package com.example.demo.exception;

import com.example.demo.constant.MessageError;

public class InValidPasswordException extends RuntimeException{
    public InValidPasswordException() {
        super(MessageError.INVALID_PASSWORD);
    }

}
