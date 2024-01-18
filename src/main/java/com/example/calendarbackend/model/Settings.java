package com.example.calendarbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents settings.
 * Class only for main user account.
 * It is used to store data about partner account ID (optional), lengths of cycle.
 */
@Getter
@Setter
@Entity(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Unique ID of settings.
     */
    private long id;

    /**
     * User ID.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    @JsonIgnore
    private User user;

    /**
     * Optional ID of partner account.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
    @Nullable
    @JsonIgnore
    private User partnerUser;

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
    private int lutealPhaseLength;

}