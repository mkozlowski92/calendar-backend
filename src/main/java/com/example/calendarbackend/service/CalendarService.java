package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.AccountDoesNotExist;
import com.example.calendarbackend.exception.MainAccountIsNotConnected;
import com.example.calendarbackend.model.Calendar;
import com.example.calendarbackend.repository.CalendarRepository;
import com.example.calendarbackend.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    /**
     * Repository for managing calendar days.
     */
    private final CalendarRepository calendarRepository;

    /**
     * Service for managing users.
     */
    private final SettingsRepository settingsRepository;

    /**
     * Service for managing users.
     */
    private final UserService userService;

    /**
     * Constructor to inject calendar days repository.
     *
     * @param calendarRepository - Calendar repository.
     * @param settingsRepository - Settings repository.
     * @param userService        - User service.
     */
    @Autowired
    public CalendarService(CalendarRepository calendarRepository, SettingsRepository settingsRepository, UserService userService) {
        this.calendarRepository = calendarRepository;
        this.settingsRepository = settingsRepository;
        this.userService = userService;
    }

    public Calendar updateCalendar(Long userId, Calendar calendar) throws AccountDoesNotExist, MainAccountIsNotConnected{

        if (!userService.isMainAccount(userId)) {
            if (settingsRepository.findByPartnerUserId(userId)!=null)
                userId = settingsRepository.findByPartnerUserId(userId).getId();
            else throw new MainAccountIsNotConnected();
        }

        //TODO: if  calendar for date and user id exists then get calendar id and set calendar.setId to that ID






        calendar.setUser(userService.getUser(userId));

        return calendarRepository.save(calendar);
    }

}
