package com.example.calendarbackend.exception;

public class AccountDoesNotExist extends Exception {

    public AccountDoesNotExist(){
        super("Partner account doesn't exist.");
    }
    public AccountDoesNotExist(String message){
        super(message);
    }
}