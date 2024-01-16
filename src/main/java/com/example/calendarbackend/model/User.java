package com.example.calendarbackend.model;

public class User {
    //unique key
    private long id;

    //email unique
    private String userName;

    private String password;

    //true = main user, false = partner
    private boolean mainAccount;

}