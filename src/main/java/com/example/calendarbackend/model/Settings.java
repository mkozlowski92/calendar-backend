package com.example.calendarbackend.model;

/**
 * Settings class only for main user account.
 */
public class Settings {

    /**
     * Optional ID of partner account.
     */
    private long partnerId;

    /**
     * Length of cycle.
     * default value is 28.
     */
    private int cycleLength;

    /**
     * Length of period.
     * default value is 6.
     */
    private int periodLength;

    /**
     * Length of luteal phase.
     * default value is 14.
     */
    private int luthealPhaseLength;

}