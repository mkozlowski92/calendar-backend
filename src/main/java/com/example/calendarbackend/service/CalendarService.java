package com.example.calendarbackend.service;

import com.example.calendarbackend.model.Calendar;
import com.example.calendarbackend.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    /**
     * Repository for managing calendar days.
     */
    private final CalendarRepository calendarRepository;

    /**
     * Constructor to inject calendar days repository.
     * @param calendarRepository - Calendar repository.
     */
    @Autowired
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public Calendar updateCalendar(Long userId, Calendar calendar) {

        return calendar;
    }

}
