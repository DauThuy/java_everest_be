package com.example.demo.exception;

public class InValidDateException extends RuntimeException{
    public InValidDateException() {
        super("Invalid date!");
    }
}
