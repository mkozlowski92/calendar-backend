package com.example.calendarbackend.exception;

public class MainAccountDoesNotExist extends Exception {

    public MainAccountDoesNotExist(){
        super("Main account doesn't exist.");
    }
    public MainAccountDoesNotExist(String message){
        super(message);
    }
}