package com.example.demo.exception;

public class InValidBidAmountException extends RuntimeException{
    public InValidBidAmountException() {
        super("Invalid budget, overal budget must be greater than bid amount");
    }
}
