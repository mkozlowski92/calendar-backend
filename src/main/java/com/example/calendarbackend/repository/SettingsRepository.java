package com.example.calendarbackend.repository;

import com.example.calendarbackend.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing settings of users in the database.
 */
@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

    Settings findByUserId(Long userId);

    Settings findByPartnerUserId(Long partnerUserId);

}
