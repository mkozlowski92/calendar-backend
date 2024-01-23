package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.AccountDoesNotExist;
import com.example.calendarbackend.exception.NotInRange;
import com.example.calendarbackend.exception.NotMainAccount;
import com.example.calendarbackend.exception.SettingsMissing;
import com.example.calendarbackend.model.Settings;
import com.example.calendarbackend.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    /**
     * Declared minimal and maximal values of cycle.
     */
    private final int MIN_CYCLE_LENGTH = 15;
    private final int MAX_CYCLE_LENGTH = 60;
    private final int MIN_PERIOD_LENGTH = 1;
    private final int MAX_PERIOD_LENGTH = 15;
    private final int MIN_LUTEAL_PHASE_LENGTH = 7;
    private final int MAX_LUTEAL_PHASE_LENGTH = 30;

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

    public Settings updateSettings(Settings settings, Long userId, String partnerUserName) throws AccountDoesNotExist, NotInRange, NotMainAccount, SettingsMissing {

        if (    settings.getCycleLength() < MIN_CYCLE_LENGTH ||
                settings.getCycleLength() > MAX_CYCLE_LENGTH ||
                settings.getPeriodLength() < MIN_PERIOD_LENGTH ||
                settings.getPeriodLength() > MAX_PERIOD_LENGTH ||
                settings.getLutealPhaseLength() < MIN_LUTEAL_PHASE_LENGTH ||
                settings.getLutealPhaseLength() > MAX_LUTEAL_PHASE_LENGTH) throw new NotInRange();

        if (!userService.isMainAccount(userId)) throw new NotMainAccount();
        if (userService.getUser(partnerUserName)==null) throw new AccountDoesNotExist();

        Settings oldSettings = getSettingsByUserId(userId);
        settings.setId(oldSettings.getId());
        settings.setUser(oldSettings.getUser());

        settings.setPartnerUser(userService.getUser(partnerUserName));
        if (settings.getPartnerUser().isMainAccount()) throw new NotMainAccount();

        if (settingsRepository.findByPartnerUserId(userService.getUser(partnerUserName).getId())!=null)
            System.out.println("istnieje settings po partnerUserName");
        if (settingsRepository.findByPartnerUserId(userService.getUser(partnerUserName).getId()).getId()!=userId)
            System.out.println("w tym settings userID != userID czyli jest zajęty już");

        return settingsRepository.save(settings);
    }

}