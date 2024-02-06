package com.example.calendarbackend.exception;

/**
 * The class NotInRange is extending Exception.
 */
public class NotInRange extends Exception {

    /**
     * Constructs a new exception with the specified message "Cycle values are not in range.".
     */
    public NotInRange(){
        super("Cycle values are not in range.");
    }

}