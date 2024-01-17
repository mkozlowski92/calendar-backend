package com.example.calendarbackend.model;

import com.example.calendarbackend.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "calendar_days")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Unique ID.
     */
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    /**
     * User ID.
     */
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