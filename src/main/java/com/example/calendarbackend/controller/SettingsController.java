package com.example.calendarbackend.controller;

import com.example.calendarbackend.model.Settings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @PutMapping("/updateSettings")
    private ResponseEntity<Void> updateSettings() {
        return null;
    }

    @GetMapping("/getSettings")
    private ResponseEntity<Settings> getSettings(@RequestParam Long userId) {
        //TODO finish this endpoint
        return ResponseEntity.ok().body(new Settings());
    }

}