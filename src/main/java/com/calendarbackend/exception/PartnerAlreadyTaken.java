package com.calendarbackend.exception;

/**
 * The class PartnerAlreadyTaken is extending Exception.
 */
public class PartnerAlreadyTaken extends Exception {

    /**
     * Constructs a new exception with the specified message "Partner account is already taken by another main user.".
     */
    public PartnerAlreadyTaken(){
        super("Partner account is already taken by another main user.");
    }

}