package com.example.calendarbackend.exception;

/**
 * The class AccountDoesNotExist is extending Exception.
 */
public class AccountDoesNotExist extends Exception {

    /**
     * Constructs a new exception with the specified message "Account with this ID doesn't exist.".
     */
    public AccountDoesNotExist(){
        super("Account with this ID doesn't exist.");
    }

}