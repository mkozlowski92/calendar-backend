package com.calendarbackend.repository;

import com.calendarbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing users in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query to find User by Username.
     * @param userName - Username.
     * @return User.
     */
    User findUserByUserName(String userName);

    /**
     * Query to find user by User ID.
     * @param userId - User ID.
     * @return User.
     */
    User findUserById(Long userId);

}