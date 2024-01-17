package com.example.calendarbackend.service;

import com.example.calendarbackend.model.Settings;
import com.example.calendarbackend.model.User;
import com.example.calendarbackend.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    /**
     * Repository for managing settings.
     */
    private final SettingsRepository settingsRepository;

    /**
     * Constructor to inject settings repository.
     * @param settingsRepository - The settings repository.
     */
    @Autowired
    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    /**
     * Creates new settings.
     * @param user - user whos settings are created.
     */
    public void CreateSettingsForUser(User user) {
        Settings addedSettings = new Settings();
        addedSettings.setId(0L);
        addedSettings.setUser(user);
//        addedSettings.setPartnerId(0L);
        addedSettings.setCycleLength(28);
        addedSettings.setPeriodLength(6);
        addedSettings.setLutealPhaseLength(14);
        settingsRepository.save(addedSettings);
    }

}