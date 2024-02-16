package com.calendarbackend.exception;

/**
 * The class PartnerAccountIsMainAccount is extending Exception.
 */
public class PartnerAccountIsMainAccount extends Exception {

    /**
     * Constructs a new exception with the specified message "User you are trying to add as partner has main account type.".
     */
    public PartnerAccountIsMainAccount(){
        super("User you are trying to add as partner has main account type.");
    }

}