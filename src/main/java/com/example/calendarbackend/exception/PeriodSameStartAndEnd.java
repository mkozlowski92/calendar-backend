package com.example.calendarbackend.exception;

public class PeriodSameStartAndEnd extends Exception {

    public PeriodSameStartAndEnd(){
        super("Can't have start and end of period in the same day.");
    }
    public PeriodSameStartAndEnd(String message){
        super(message);
    }
}