package com.example.calendarbackend.exception;

public class AccountDoesNotExist extends Exception {

    public AccountDoesNotExist(){
        super("Account with this ID doesn't exist.");
    }
    public AccountDoesNotExist(String message){
        super(message);
    }
}