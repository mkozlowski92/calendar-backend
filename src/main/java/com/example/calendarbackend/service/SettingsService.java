package com.example.calendarbackend.service;

import com.example.calendarbackend.model.Settings;
import com.example.calendarbackend.model.User;
import com.example.calendarbackend.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    /**
     * Declared default values of cycle.
     */
    private final int DEFAULT_CYCLE_LENGTH = 28;
    private final int DEFAULT_PERIOD_LENGTH = 5;
    private final int DEFAULT_LUTEAL_PHASE_LENGTH = 14;

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
        addedSettings.setCycleLength(DEFAULT_CYCLE_LENGTH);
        addedSettings.setPeriodLength(DEFAULT_PERIOD_LENGTH);
        addedSettings.setLutealPhaseLength(DEFAULT_LUTEAL_PHASE_LENGTH);
        settingsRepository.save(addedSettings);
    }

}