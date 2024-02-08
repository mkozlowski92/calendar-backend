package com.example.calendarbackend.exception;

/**
 * The class PeriodSameStartAndEnd is extending Exception.
 */
public class PeriodSameStartAndEnd extends Exception {

    /**
     * Constructs a new exception with the specified message "Can't have start and end of period in the same day.".
     */
    public PeriodSameStartAndEnd(){
        super("Can't have start and end of period in the same day.");
    }

}