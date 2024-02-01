package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.*;
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
     * @throws MainAccountDoesNotExist - Main account does not exist.
     * @throws SettingsMissing - Settings for user not found.
     */
    public Settings getSettingsByUserId(Long userId) throws MainAccountDoesNotExist, SettingsMissing {
        if (!userService.existsUser(userId)) throw new MainAccountDoesNotExist();
        Settings userSettings = settingsRepository.findByUserId(userId);
        if (userSettings==null) userSettings = settingsRepository.findByPartnerUserId(userId);
        if (userSettings==null) throw new SettingsMissing();
        return userSettings;
    }

    /**
     * Updates settings of user.
     * @param settings - lengths of cycle
     * @param userId - User ID.
     * @param partnerUserName - Username of partner.
     * @return - settings (lengths of cycle)
     * @throws AccountDoesNotExist - Account with this ID doesn't exist.
     * @throws MainAccountDoesNotExist - Main account does not exist.
     * @throws NotInRange - Cycle values are not in range.
     * @throws NotMainAccount - It is partner account.
     * @throws PartnerAlreadyTaken - Partner account is already reserved by another main account.
     * @throws PartnerAccountIsMainAccount - Partner's account is actually a main account.
     * @throws PartnerAccountDoesNotExist - Partner account doesn't exist.
     * @throws SettingsMissing - settings are missing for that user.
     */
    public Settings updateSettings(Settings settings, Long userId, String partnerUserName) throws AccountDoesNotExist, MainAccountDoesNotExist, NotInRange, NotMainAccount, PartnerAlreadyTaken, PartnerAccountIsMainAccount, PartnerAccountDoesNotExist, SettingsMissing {

        if (    settings.getCycleLength() < MIN_CYCLE_LENGTH ||
                settings.getCycleLength() > MAX_CYCLE_LENGTH ||
                settings.getPeriodLength() < MIN_PERIOD_LENGTH ||
                settings.getPeriodLength() > MAX_PERIOD_LENGTH ||
                settings.getLutealPhaseLength() < MIN_LUTEAL_PHASE_LENGTH ||
                settings.getLutealPhaseLength() > MAX_LUTEAL_PHASE_LENGTH) throw new NotInRange();

        if (!userService.isMainAccount(userId)) throw new NotMainAccount();
        if (userService.getUser(partnerUserName)==null && partnerUserName!= null) throw new PartnerAccountDoesNotExist();

        Settings oldSettings = getSettingsByUserId(userId);
        settings.setId(oldSettings.getId());
        settings.setUser(oldSettings.getUser());

        if (partnerUserName!=null) {
            settings.setPartnerUser(userService.getUser(partnerUserName));
            if (settings.getPartnerUser().isMainAccount()) throw new PartnerAccountIsMainAccount();
            if (settingsRepository.findByPartnerUserId(userService.getUser(partnerUserName).getId())!=null && settingsRepository.findByPartnerUserId(userService.getUser(partnerUserName).getId()).getUser().getId()!=userId) throw new PartnerAlreadyTaken();
        }

        return settingsRepository.save(settings);
    }

}