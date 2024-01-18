package com.example.calendarbackend.exception;

public class TooShortCredentials extends Exception {

    public TooShortCredentials(){
        super("Username or password is too short.");
    }
    public TooShortCredentials(String message){
        super(message);
    }
}