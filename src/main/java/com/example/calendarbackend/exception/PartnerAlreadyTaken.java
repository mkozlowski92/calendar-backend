package com.example.calendarbackend.exception;

public class PartnerAlreadyTaken extends Exception {

    public PartnerAlreadyTaken(){
        super("Partner account is already taken by another main user.");
    }
    public PartnerAlreadyTaken(String message){
        super(message);
    }
}