package com.example.demo.exception;

public class InValidEmailException extends RuntimeException{
    public InValidEmailException() {
        super("Invalid email!");
    }
}
