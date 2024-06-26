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
@CrossOrigin
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
     * @return ResponseEntity ok.
     * @throws UserNameExists - username already exists.
     * @throws TooShortCredentials - Credentials are too short.
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
                            description = "User e-mail already exists or too short credentials",
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
    public ResponseEntity<Void> addUser(@RequestBody User user) throws UserNameExists, TooShortCredentials{
        try {
            userService.addUser(user);
            return ResponseEntity.ok().build();
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Validates user (username and password).
     * @param userName - User name.
     * @param password - Password of user.
     * @return ResponseEntity with user ID.
     * @throws IncorrectCredentials - username or password are incorrect.
     * @throws TooShortCredentials - username or password are too short.
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
                            description = "Validation not successful. Incorrect or too short credentials.",
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
            return ResponseEntity.ok(userService.validateUser(userName, password));
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

}