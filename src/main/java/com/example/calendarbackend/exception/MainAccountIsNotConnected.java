package com.example.calendarbackend.exception;

/**
 * The class MainAccountIsNotConnected is extending Exception.
 */
public class MainAccountIsNotConnected extends Exception {

    /**
     * Constructs a new exception with the specified message "Main account isn't connected to this partner account.".
     */
    public MainAccountIsNotConnected(){
        super("Main account isn't connected to this partner account.");
    }

}