package com.example.calendarbackend.controller;

import com.example.calendarbackend.exception.AccountDoesNotExist;
import com.example.calendarbackend.exception.MainAccountIsNotConnected;
import com.example.calendarbackend.model.Calendar;
import com.example.calendarbackend.service.CalendarService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for managing calendar.
 */
@CrossOrigin
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    /**
     * Service for managing calendar.
     */
    private final CalendarService calendarService;

    /**
     * Constructor to inject calendar service.
     * @param calendarService - Calendar service.
     */
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PutMapping("/updateCalendar")
    private ResponseEntity<Calendar> updateCalendar(@RequestParam Long userId, @RequestBody Calendar calendar) throws AccountDoesNotExist, MainAccountIsNotConnected {
        try {
            return ResponseEntity.ok(calendarService.updateCalendar(userId, calendar));
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/getCalendar")
    private ResponseEntity<List<Calendar>> getCalendar() {
        try {
            return null;
        } catch (DataAccessException exception){
            return ResponseEntity.internalServerError().build();
        }
    }

}
