package com.example.calendarbackend.controller;

import com.example.calendarbackend.exception.IncorrectCredentials;
import com.example.calendarbackend.exception.TooShortCredentials;
import com.example.calendarbackend.exception.UserNameExists;
import com.example.calendarbackend.model.User;
import com.example.calendarbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for managing users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Service for managing users.
     */
    private final UserService userService;

    /**
     * Constructor to inject user service.
     * @param userService - The user service.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Add new user.
     * Requires password at least 5 characters password length.
     * @param user - User to add.
     * @return ResponseEntity with added user.
     */
    @Operation(summary = "Add User")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created user",
                            content = @Content(
                                    mediaType="application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "User e-mail already exists",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server error",
                            content = @Content
                    )
            }
    )
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) throws UserNameExists, TooShortCredentials{
        try {
            if (user.getPassword().length()<5||user.getUserName().length()<5) throw new TooShortCredentials();
            user.setId(0L);
            User addedUser = userService.addUser(user);
            return ResponseEntity.ok(addedUser);
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Validates user.
     * @param userName - User name.
     * @param password Password of user.
     * @return ID of user.
     */
    @Operation(summary = "Login")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Logged in",
                            content = @Content(
                                    mediaType="application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Long.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Validation not successful",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server error",
                            content = @Content
                    )
            }
    )
    @GetMapping("/login")
    public ResponseEntity<Long> loginUser(@RequestParam String userName, String password) throws TooShortCredentials, IncorrectCredentials {
        try {
            if (password.length()<5||userName.length()<5) throw new TooShortCredentials();
            Long userId = userService.validateUser(userName, password);
            return ResponseEntity.ok(userId);
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

}