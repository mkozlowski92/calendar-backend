package com.example.calendarbackend.exception;

public class SettingsMissing extends Exception {

    public SettingsMissing(){
        super("Settings are missing. It is partner account without main account connected.");
    }
    public SettingsMissing(String message){
        super(message);
    }
}