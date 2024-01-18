package com.example.calendarbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents user.
 * It is used to store data about username (e-mail), password, type of account.
 */
@Getter
@Setter
@Entity(name = "users")
public class User {

    /**
     * Unique ID of user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    /**
     * Unique e-mail of user.
     */
    @Column(nullable = false, unique = true)
    private String userName;

    /**
     * Password of user.
     */
    @Column(nullable = false)
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
    @PrimaryKeyJoinColumn(name = "id")
    @JsonIgnore
    private Settings settings;

    /**
     * Calendar days model association.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Calendar> calendar;

}