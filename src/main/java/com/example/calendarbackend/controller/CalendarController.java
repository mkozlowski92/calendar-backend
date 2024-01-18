package com.example.calendarbackend.controller;

import com.example.calendarbackend.model.Calendar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @PutMapping("/updateCalendar")
    private ResponseEntity<Void> updateCalendar() {
        return null;
    }

    @GetMapping("/getCalendar")
    private ResponseEntity<Calendar> getCalendar() {
        return ResponseEntity.ok().body(new Calendar());
    }

}
