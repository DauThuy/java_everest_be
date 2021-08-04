package com.example.demo.exception;

import com.example.demo.constant.MessageError;

public class InValidBidAmountException extends RuntimeException{
    public InValidBidAmountException() {
        super(MessageError.INVALID_BID_AMOUNT);
    }
}
