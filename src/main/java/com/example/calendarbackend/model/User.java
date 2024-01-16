package com.example.calendarbackend.model;

public class User {
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

}