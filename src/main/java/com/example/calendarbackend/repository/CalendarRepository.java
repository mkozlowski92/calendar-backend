package com.example.calendarbackend.repository;

import com.example.calendarbackend.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing calendar days in the database.
 */
@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}