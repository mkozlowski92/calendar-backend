package com.example.calendarbackend.repository;

import com.example.calendarbackend.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repository interface for managing calendar days in the database.
 */
@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Calendar findCalendarByUserIdAndDate(Long userId, LocalDate date);

}