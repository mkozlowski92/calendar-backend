package com.example.calendarbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents days in a calendar.
 * It is used to store data about days of a cycle, moods, additional notes.
 */
@Getter
@Setter
@Entity(name = "calendar_days")
public class Calendar {

    /**
     * Unique ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    /**
     * User ID.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

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