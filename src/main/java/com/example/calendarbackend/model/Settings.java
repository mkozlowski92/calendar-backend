package com.example.calendarbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    /**
     * Unique ID of settings.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    /**
     * User entity.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    /**
     * Optional entity of partner account.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
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

    public Settings(){}

    /**
     * Settings constructor.
     * @param id Settings ID.
     * @param user User entity.
     * @param partnerUser Optional entity of partner account.
     * @param cycleLength Length of period.
     * @param periodLength Length of period.
     * @param lutealPhaseLength Length of luteal phase.
     */
    public Settings(long id, User user, User partnerUser, int cycleLength, int periodLength, int lutealPhaseLength) {
        this.id = id;
        this.user = user;
        this.partnerUser = partnerUser;
        this.cycleLength = cycleLength;
        this.periodLength = periodLength;
        this.lutealPhaseLength = lutealPhaseLength;
    }

}