package com.example.calendarbackend.exception;

public class SettingsMissing extends Exception {

    public SettingsMissing(){
        super("Username or password is too short.");
    }
    public SettingsMissing(String message){
        super(message);
    }
}