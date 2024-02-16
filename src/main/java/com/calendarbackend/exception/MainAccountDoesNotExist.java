package com.calendarbackend.exception;

/**
 * The class MainAccountDoesNotExist is extending Exception.
 */
public class MainAccountDoesNotExist extends Exception {

    /**
     * Constructs a new exception with the specified message "Main account doesn't exist.".
     */
    public MainAccountDoesNotExist(){
        super("Main account doesn't exist.");
    }

}