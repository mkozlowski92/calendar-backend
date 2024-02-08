package com.example.calendarbackend.repository;

import com.example.calendarbackend.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing settings of users in the database.
 */
@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    /**
     * Query to find Settings by User ID.
     * @param userId - User ID.
     * @return Settings.
     */
    Settings findByUserId(Long userId);

    /**
     * Query to find Settings by Partner User ID.
     * @param partnerUserId - Partner User ID.
     * @return Settings.
     */
    Settings findByPartnerUserId(Long partnerUserId);

}
