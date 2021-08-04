package com.example.demo.exception;

import com.example.demo.constant.MessageError;

public class InvalidBudgetBidAmountException extends RuntimeException{
    public InvalidBudgetBidAmountException() {
        super(MessageError.INVALID_BUDGET_OR_BID_AMOUNT);
    }
}
