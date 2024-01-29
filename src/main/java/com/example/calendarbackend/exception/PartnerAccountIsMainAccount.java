package com.example.calendarbackend.exception;

public class PartnerAccountIsMainAccount extends Exception {

    public PartnerAccountIsMainAccount(){
        super("User you are trying to add as partner has main account type.");
    }
    public PartnerAccountIsMainAccount(String message){
        super(message);
    }
}