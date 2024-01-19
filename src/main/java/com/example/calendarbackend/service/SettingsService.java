package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.NotMainAccount;
import com.example.calendarbackend.exception.SettingsMissing;
import com.example.calendarbackend.model.Settings;
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
     * Service for managing users.
     */
    private final UserService userService;

    /**
     * Constructor to inject settings repository.
     *
     * @param settingsRepository - The settings repository.
     * @param userService - User service.
     */
    @Autowired
    public SettingsService(SettingsRepository settingsRepository, UserService userService) {
        this.settingsRepository = settingsRepository;
        this.userService = userService;
    }

    /**
     * Gets settings of user.
     * @param userId - ID of user.
     * @return settings.
     */
    public Settings getSettingsByUserId(Long userId) throws SettingsMissing {
        Settings userSettings = settingsRepository.findByUserId(userId);
        if (userSettings==null) userSettings = settingsRepository.findByPartnerUserId(userId);
        if (userSettings==null) throw new SettingsMissing();
        return userSettings;
    }

    /**
     * Saves settings.
     * @param settings - Settings of user.
     */
    public void saveSettings(Settings settings) {
        settingsRepository.save(settings);
    }

    public Settings updateSettings(Settings settings, Long userId, Long partnerUserId) throws NotMainAccount, SettingsMissing {

        if (!userService.isMainAccount(userId)) throw new NotMainAccount();

        Settings oldSettings = getSettingsByUserId(userId);
        settings.setId(oldSettings.getId());
        settings.setUser(oldSettings.getUser());
        settings.setPartnerUser(userService.getUser(partnerUserId));

        saveSettings(settings);

        return settings;
    }

}