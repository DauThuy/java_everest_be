package com.example.demo.exception;

public class InValidPasswordException extends RuntimeException{
    public InValidPasswordException() {
        super("Invalid password!");
    }

}
