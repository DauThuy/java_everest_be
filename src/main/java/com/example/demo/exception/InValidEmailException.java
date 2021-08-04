package com.example.demo.exception;

import com.example.demo.constant.MessageError;

public class InValidEmailException extends RuntimeException{
    public InValidEmailException() {
        super(MessageError.INVALID_EMAIL);
    }
}
