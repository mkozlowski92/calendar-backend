package com.example.calendarbackend.exception;

public class UserNameExists extends Exception {

    public UserNameExists(){
        super("Username already exists.");
    }
    public UserNameExists(String message){
        super(message);
    }
}