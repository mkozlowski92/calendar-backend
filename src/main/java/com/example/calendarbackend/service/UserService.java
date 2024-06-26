package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.AccountDoesNotExist;
import com.example.calendarbackend.exception.IncorrectCredentials;
import com.example.calendarbackend.exception.TooShortCredentials;
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
     * Add new user.
     * @param  user - User to add
     * @throws UserNameExists - username already exists.
     * @throws TooShortCredentials - Credentials are too short.
     */
    public void addUser(User user) throws TooShortCredentials, UserNameExists {
        if (user.getPassword().length()<5||user.getUserName().length()<5) throw new TooShortCredentials();
        if (userRepository.findUserByUserName(user.getUserName())!=null) throw new UserNameExists();
        user.setId(0L);
        userRepository.save(user);
        if (user.isMainAccount()) {
            settingsRepository.save(new Settings(0L,user,null,DEFAULT_CYCLE_LENGTH,DEFAULT_PERIOD_LENGTH,DEFAULT_LUTEAL_PHASE_LENGTH));
        }
    }

    /**
     * Validates user (username and password).
     * @param userName - User name.
     * @param password - Password of user.
     * @return ID of user.
     * @throws IncorrectCredentials - username or password are incorrect.
     * @throws TooShortCredentials - username or password are too short.
     */
    public Long validateUser(String userName, String password) throws IncorrectCredentials, TooShortCredentials {
        if (password.length()<5||userName.length()<5) throw new TooShortCredentials();
        User user = userRepository.findUserByUserName(userName);
        if (user==null) throw new IncorrectCredentials();
        if (Objects.equals(user.getPassword(), password)) return user.getId();
        throw new IncorrectCredentials();
    }

    /**
     * Checks if is main account.
     * @param userId - user ID.
     * @return - boolean: true - is main, false - is not main.
     * @throws AccountDoesNotExist - Account with this ID doesn't exist.
     */
    public boolean isMainAccount(Long userId) throws AccountDoesNotExist {
        if (userRepository.existsById(userId))
            return userRepository.findById(userId).get().isMainAccount();
        throw new AccountDoesNotExist();
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
     * Gets user by user ID.
     *
     * @param userId - username.
     * @return user.
     */
    public User getUser(Long userId) {
        return userRepository.findUserById(userId);
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