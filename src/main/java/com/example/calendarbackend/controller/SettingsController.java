package com.example.calendarbackend.controller;

import com.example.calendarbackend.model.Settings;
import com.example.calendarbackend.service.SettingsService;
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

    @GetMapping("/getSettings")
    private ResponseEntity<Settings> getSettings(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok().body(settingsService.getSettingsById(userId));
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

}