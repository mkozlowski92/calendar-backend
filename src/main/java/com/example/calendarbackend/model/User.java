package com.example.calendarbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Unique ID of user.
     */
    private long id;

    /**
     * Unique e-mail of user.
     */
    private String userName;

    /**
     * Password of user.
     */
    private String password;

    /**
     * Account type.
     * true = main user
     * false = partner
     */
    private boolean mainAccount;

    /**
     * Settings model association.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Settings settings;

    /**
     * Calendar days model association.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Calendar> calendar;

}