package com.example.demo.exception;

import com.example.demo.constant.MessageError;

public class InValidDateException extends RuntimeException{
    public InValidDateException() {
        super(MessageError.INVALID_DATE);
    }
}
