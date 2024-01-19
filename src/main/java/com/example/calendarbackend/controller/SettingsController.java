package com.example.calendarbackend.controller;

import com.example.calendarbackend.exception.SettingsMissing;
import com.example.calendarbackend.model.Settings;
import com.example.calendarbackend.service.SettingsService;
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
 * Rest controller for managing settings of users.
 */
@CrossOrigin
@RestController
@RequestMapping("/settings")
public class SettingsController {

    /**
     * Service for managing settings of users.
     */
    private final SettingsService settingsService;

    /**
     * Constructor to inject settings service.
     * @param settingsService - Settings service.
     */
    @Autowired
    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PutMapping("/updateSettings")
    private ResponseEntity<Void> updateSettings() {
        return null;
    }

    /**
     * Gets settings values of user.
     * @param userId - User ID.
     * @return - settings of user.
     * @throws SettingsMissing - Settings for user not found.
     */
    @Operation(summary = "Get settings")
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
                            description = "Settings for user not found.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server error",
                            content = @Content
                    )
            }
    )
    @GetMapping("/getSettings")
    private ResponseEntity<Settings> getSettings(@RequestParam Long userId) throws SettingsMissing {
        try {
            return ResponseEntity.ok().body(settingsService.getSettingsById(userId));
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

}