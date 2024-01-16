package com.example.calendarbackend.model;

import java.time.LocalDate;

public class Calendar {

    /**
     * Unique ID.
     */
    private long id;

    /**
     * User ID.
     */
    private long userId;

    /**
     * Date of a day.
     */
    private LocalDate date;

    /**
     * Boolean if period start this day.
     */
    private boolean periodStart;

    /**
     * Boolean if period end this day.
     */
    private boolean periodEnd;

    /**
     * Boolean, mood1 of User.
     */
    private boolean moodType1;

    /**
     * Boolean, mood2 of User.
     */
    private boolean moodType2;

    /**
     * Boolean, mood3 of User.
     */
    private boolean moodType3;

    /**
     * Boolean, mood4 of User.
     */
    private boolean moodType4;

    /**
     * Boolean, mood5 of User.
     */
    private boolean moodType5;

    /**
     * Optional notes.
     */
    private String notes;

}