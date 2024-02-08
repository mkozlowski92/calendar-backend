package com.example.calendarbackend.exception;

/**
 * The class UserNameExists is extending Exception.
 */
public class UserNameExists extends Exception {

    /**
     * Constructs a new exception with the specified message "Username already exists.".
     */
    public UserNameExists(){
        super("Username already exists.");
    }

}