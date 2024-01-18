package com.example.calendarbackend.exception;

public class TemplateException extends Exception {

    public TemplateException(){
        super("Exception text");
    }
    public TemplateException(String message){
        super(message);
    }
}