package com.calendarbackend.exception;

/**
 * The class IncorrectCredentials is extending Exception.
 */
public class IncorrectCredentials extends Exception {

    /**
     * Constructs a new exception with the specified message "Incorrect credentials.".
     */
    public IncorrectCredentials(){
        super("Incorrect credentials.");
    }

}