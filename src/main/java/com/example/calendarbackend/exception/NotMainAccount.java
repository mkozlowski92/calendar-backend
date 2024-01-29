package com.example.calendarbackend.exception;

public class NotMainAccount extends Exception {

    public NotMainAccount(){
        super("It is not main account. No access.");
    }
    public NotMainAccount(String message){
        super(message);
    }
}