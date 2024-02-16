package com.calendarbackend.controller;

import com.calendarbackend.exception.IncorrectCredentials;
import com.calendarbackend.exception.TooShortCredentials;
import com.calendarbackend.model.User;
import com.calendarbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;


    @Test
    void addUser() {
    }

    @Test
    void loginUserOK() throws TooShortCredentials, IncorrectCredentials {
        User user = new User(2,"name","pass",true,null,null);

        when(userService.validateUser(user.getUserName(), user.getPassword())).thenReturn(user.getId());

        ResponseEntity<Long> response = userController.loginUser(user.getUserName(),user.getPassword());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user.getId(), response.getBody());
    }
}