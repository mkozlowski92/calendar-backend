package com.example.calendarbackend.exception;

/**
 * The class PartnerAccountDoesNotExist is extending Exception.
 */
public class PartnerAccountDoesNotExist extends Exception {

    /**
     * Constructs a new exception with the specified message "Partner account doesn't exist.".
     */
    public PartnerAccountDoesNotExist(){
        super("Partner account doesn't exist.");
    }

}