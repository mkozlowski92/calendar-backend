package com.calendarbackend.repository;

import com.calendarbackend.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for managing calendar days in the database.
 */
@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    /**
     * Query to find Calendar by UserId and Date.
     * @param userId - ID of user.
     * @param date - date.
     * @return Calendar.
     */
    Calendar findCalendarByUserIdAndDate(Long userId, LocalDate date);

    /**
     * Query to find all Calendar for User ID.
     * @param userId - User ID.
     * @return List of Calendar.
     */
    List<Calendar> findAllByUserId(Long userId);

}