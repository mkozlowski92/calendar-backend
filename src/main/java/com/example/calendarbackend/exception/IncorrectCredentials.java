package com.example.calendarbackend.exception;

public class IncorrectCredentials extends Exception {

    public IncorrectCredentials(){
        super("Incorrect credentials.");
    }
    public IncorrectCredentials(String message){
        super(message);
    }
}