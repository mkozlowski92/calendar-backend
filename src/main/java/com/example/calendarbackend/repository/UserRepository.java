package com.example.calendarbackend.repository;

import com.example.calendarbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing users in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String userName);

    User findUserById(Long userId);

    boolean existsByUserName(String userName);

}