package com.example.demo.user;

public class BadUserInputException extends RuntimeException{

    public BadUserInputException(String s){
        super(s);
    }
}
