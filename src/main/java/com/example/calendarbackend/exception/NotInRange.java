package com.example.calendarbackend.exception;

public class NotInRange extends Exception {

    public NotInRange(){
        super("Cycle values are not in range.");
    }
    public NotInRange(String message){
        super(message);
    }
}