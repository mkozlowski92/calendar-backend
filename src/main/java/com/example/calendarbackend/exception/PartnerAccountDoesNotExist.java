package com.example.calendarbackend.exception;

public class PartnerAccountDoesNotExist extends Exception {

    public PartnerAccountDoesNotExist(){
        super("Partner account doesn't exist.");
    }
    public PartnerAccountDoesNotExist(String message){
        super(message);
    }
}