package com.example.calendarbackend.exception;

public class MainAccountIsNotConnected extends Exception {

    public MainAccountIsNotConnected(){
        super("Main account isn't connected to this partner account.");
    }
    public MainAccountIsNotConnected(String message){
        super(message);
    }
}