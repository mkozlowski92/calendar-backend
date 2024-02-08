package com.example.calendarbackend.exception;

/**
 * The class NotMainAccount is extending Exception.
 */
public class NotMainAccount extends Exception {

    /**
     * Constructs a new exception with the specified message "It is not main account. No access.".
     */
    public NotMainAccount(){
        super("It is not main account. No access.");
    }

}