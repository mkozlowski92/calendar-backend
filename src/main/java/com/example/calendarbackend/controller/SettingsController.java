package com.example.calendarbackend.controller;

import com.example.calendarbackend.exception.*;
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

    /**
     * Saves settings of user.
     * @param settings - Settings of user.
     * @param userId - User ID.
     * @param partnerUserName - Username of partner.
     * @return - Settings.
     * @throws AccountDoesNotExist - Account with this ID doesn't exist.
     * @throws MainAccountDoesNotExist - Main account does not exist.
     * @throws NotInRange - Cycle values are not in range.
     * @throws NotMainAccount - It is partner account.
     * @throws PartnerAlreadyTaken - Partner account is already reserved by another main account.
     * @throws PartnerAccountIsMainAccount - Partner's account is actually a main account.
     * @throws PartnerAccountDoesNotExist - Partner account doesn't exist.
     * @throws SettingsMissing - settings are missing for that user.
     */
    @Operation(summary = "Update settings")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated",
                            content = @Content(
                                    mediaType="application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Settings.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Failed to save settings.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server error",
                            content = @Content
                    )
            }
    )
    @PutMapping("/updateSettings")
    private ResponseEntity<Settings> updateSettings(@RequestBody Settings settings, @RequestParam Long userId, @RequestParam(required = false) String partnerUserName) throws AccountDoesNotExist, MainAccountDoesNotExist, NotInRange, NotMainAccount, PartnerAlreadyTaken, PartnerAccountIsMainAccount, PartnerAccountDoesNotExist, SettingsMissing {
        try {
            return ResponseEntity.ok(settingsService.updateSettings(settings, userId, partnerUserName));
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Gets settings values of user.
     * @param userId - User ID.
     * @return - settings of user.
     * @throws MainAccountDoesNotExist - Main account does not exist.
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
                                    array = @ArraySchema(schema = @Schema(implementation = Settings.class))
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
    private ResponseEntity<Settings> getSettings(@RequestParam Long userId) throws MainAccountDoesNotExist, SettingsMissing {
        try {
            return ResponseEntity.ok().body(settingsService.getSettingsByUserId(userId));
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

}