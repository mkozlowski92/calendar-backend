package com.example.calendarbackend.controller;

import com.example.calendarbackend.model.Settings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @PutMapping("/updateSettings")
    private ResponseEntity<Void> updateSettings() {
        return null;
    }

    @GetMapping("/getSettings")
    private ResponseEntity<Settings> getSettings() {
        return ResponseEntity.ok().body(new Settings());
    }

}