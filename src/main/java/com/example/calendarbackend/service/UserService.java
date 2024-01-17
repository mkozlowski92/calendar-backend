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
     * Constructor to inject user repository.
     * @param userRepository -The user repository.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates new user.
     * @param user - Create a new user.
     * @return The created user.
     */
    public User addUser(User user){
        return userRepository.save(user);
    }


}
