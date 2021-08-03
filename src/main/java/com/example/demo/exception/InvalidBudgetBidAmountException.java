package com.example.demo.exception;

public class InvalidBudgetBidAmountException extends RuntimeException{
    public InvalidBudgetBidAmountException() {
        super("Invalid budget or bid amount! ");
    }
}
