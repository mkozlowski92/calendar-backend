package com.example.calendarbackend.service;

import com.example.calendarbackend.model.User;
import com.example.calendarbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users and related operations.
 */
@Service
public class UserService {

    /**
     * Repository for managing users.
     */
    private final UserRepository userRepository;

    /**
     * Repository for managing settings.
     */
    private final SettingsService settingsService;

    /**
     * Constructor to inject user repository and settings service.
     * @param userRepository  - User repository.
     * @param settingsService - Settings service.
     */
    @Autowired
    public UserService(UserRepository userRepository, SettingsService settingsService) {
        this.userRepository = userRepository;
        this.settingsService = settingsService;
    }

    /**
     * Creates new user.
     * @param user - Create a new user.
     * @return The created user.
     */
    public User addUser(User user){

        if (existsUserByUserName(user.getUserName())) {
            User addedUSer = userRepository.save(user);
            if (user.isMainAccount()) settingsService.CreateSettingsForUser(addedUSer);
            return addedUSer;
        }
        return null;
    }

    /**
     * Checks if user exists.
     * @param userName - Username of a user.
     * @return Returns boolean - True if exists, false if it doesn't exist.
     */
    public boolean existsUserByUserName(String userName) {
        if (userRepository.findUserByUserName(userName)==null)
        return true;
        return false;
    }

}