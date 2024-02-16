package com.calendarbackend.exception;

/**
 * The class TooShortCredentials is extending Exception.
 */
public class TooShortCredentials extends Exception {

    /**
     * Constructs a new exception with the specified message "Username or password is too short.".
     */
    public TooShortCredentials(){
        super("Username or password is too short.");
    }

}