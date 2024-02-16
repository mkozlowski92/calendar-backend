package com.calendarbackend.exception;

/**
 * The class SettingsMissing is extending Exception.
 */
public class SettingsMissing extends Exception {

    /**
     * Constructs a new exception with the specified message "Settings are missing. It is partner account without main account connected.".
     */
    public SettingsMissing(){
        super("Settings are missing. It is partner account without main account connected.");
    }

}