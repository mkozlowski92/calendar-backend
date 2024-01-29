package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.IncorrectCredentials;
import com.example.calendarbackend.exception.UserNameExists;
import com.example.calendarbackend.model.Settings;
import com.example.calendarbackend.model.User;
import com.example.calendarbackend.repository.SettingsRepository;
import com.example.calendarbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service class for managing users and related operations.
 */
@Service
public class UserService {

    /**
     * Declared default values of cycle.
     */
    private final int DEFAULT_CYCLE_LENGTH = 28;
    private final int DEFAULT_PERIOD_LENGTH = 5;
    private final int DEFAULT_LUTEAL_PHASE_LENGTH = 14;

    /**
     * Repository for managing users.
     */
    private final UserRepository userRepository;

    /**
     * Repository for managing settings.
     */
    private final SettingsRepository settingsRepository;

    /**
     * Constructor to inject user repository and settings service.
     *
     * @param userRepository     - User repository.
     * @param settingsRepository - Settings repository.
     */
    @Autowired
    public UserService(UserRepository userRepository, SettingsRepository settingsRepository) {
        this.userRepository = userRepository;
        this.settingsRepository = settingsRepository;
    }

    /**
     * Creates new user.
     * @param user - Create a new user.
     * @return The created user.
     */
    public User addUser(User user) throws UserNameExists {

        if (userRepository.findUserByUserName(user.getUserName())!=null) throw new UserNameExists();
        User addedUSer = userRepository.save(user);
        if (user.isMainAccount()) {
            Settings addedSettings = new Settings();
            addedSettings.setId(0L);
            addedSettings.setUser(user);
            addedSettings.setCycleLength(DEFAULT_CYCLE_LENGTH);
            addedSettings.setPeriodLength(DEFAULT_PERIOD_LENGTH);
            addedSettings.setLutealPhaseLength(DEFAULT_LUTEAL_PHASE_LENGTH);
            settingsRepository.save(addedSettings);
        }
        return addedUSer;
    }

    /**
     * Validates username and password.
     * @param userName - User name.
     * @param password - Password of user.
     * @return ID of user.
     */
    public Long validateUser(String userName, String password) throws IncorrectCredentials {
        User user = userRepository.findUserByUserName(userName);
        if (user==null) throw new IncorrectCredentials();
        if (Objects.equals(user.getPassword(), password)) return user.getId();
        throw new IncorrectCredentials();
    }

    /**
     * Checks if is main account.
     * @param userId - user ID.
     * @return - boolean: true - is main, false - is not main.
     */
    public boolean isMainAccount(Long userId) {
        if (userRepository.existsById(userId))
            return userRepository.findById(userId).get().isMainAccount();
        throw new RuntimeException("no account (isMainAccount");
    }

    /**
     * Gets user by username.
     *
     * @param userName - username.
     * @return user.
     */
    public User getUser(String userName) {
          return userRepository.findUserByUserName(userName);
    }

    /**
     * Checks if user exists.
     * @param userId - user ID.
     * @return true if exists, false if it doesn't exist.
     */
    public boolean existsUser(Long userId) {
        return userRepository.existsById(userId);
    }

}