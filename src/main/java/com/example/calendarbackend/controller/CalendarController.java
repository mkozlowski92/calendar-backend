package com.example.calendarbackend.controller;

import com.example.calendarbackend.exception.AccountDoesNotExist;
import com.example.calendarbackend.exception.MainAccountIsNotConnected;
import com.example.calendarbackend.exception.PeriodSameStartAndEnd;
import com.example.calendarbackend.model.Calendar;
import com.example.calendarbackend.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    /**
     * Updates single calendar day.
     * @param userId - user ID.
     * @param calendar - values of the calendar.
     * @return calendar.
     * @throws AccountDoesNotExist - Account with this ID doesn't exist.
     * @throws MainAccountIsNotConnected - Main account isn't connected to this partner account.
     * @throws PeriodSameStartAndEnd - Can't have start and end of period in the same day.
     */
    @Operation(summary = "Update calendar")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update calendar",
                            content = @Content(
                                    mediaType="application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Calendar.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Main account isn't connected to this partner account.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server error",
                            content = @Content
                    )
            }
    )
    @PutMapping("/updateCalendar")
    private ResponseEntity<Calendar> updateCalendar(@RequestParam Long userId, @RequestBody Calendar calendar) throws AccountDoesNotExist, MainAccountIsNotConnected, PeriodSameStartAndEnd {
        try {
            return ResponseEntity.ok(calendarService.updateCalendar(userId, calendar));
        } catch (DataAccessException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Gets list of calendars days for whole month.
     * @param userId - user ID.
     * @param year - year.
     * @param month - month.
     * @return List of calendar days.
     * @throws AccountDoesNotExist - Account with this ID doesn't exist.
     * @throws MainAccountIsNotConnected - Main account isn't connected to this partner account.
     */
    @Operation(summary = "Get calendar for whole month")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Got list of days.",
                            content = @Content(
                                    mediaType="application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Calendar.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Main account isn't connected to this partner account.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Server error",
                            content = @Content
                    )
            }
    )
    @GetMapping("/getCalendar")
    private ResponseEntity<List<Calendar>> getCalendar(@RequestParam Long userId, @RequestParam Long year, @RequestParam Long month) throws AccountDoesNotExist, MainAccountIsNotConnected {
        try {
            return ResponseEntity.ok(calendarService.getCalendar(userId, year, month));
        } catch (DataAccessException exception){
            return ResponseEntity.internalServerError().build();
        }
    }

}
