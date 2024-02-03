package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.AccountDoesNotExist;
import com.example.calendarbackend.exception.MainAccountIsNotConnected;
import com.example.calendarbackend.exception.PeriodSameStartAndEnd;
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

    /**
     * Updates single calendar day.
     * @param userId - user ID.
     * @param calendar - values of the calendar.
     * @return calendar.
     * @throws AccountDoesNotExist - Account with this ID doesn't exist.
     * @throws MainAccountIsNotConnected - Main account isn't connected to this partner account.
     * @throws PeriodSameStartAndEnd - Can't have start and end of period in the same day.
     */
    public Calendar updateCalendar(Long userId, Calendar calendar) throws AccountDoesNotExist, MainAccountIsNotConnected, PeriodSameStartAndEnd {

        if (!userService.isMainAccount(userId)) {
            if (settingsRepository.findByPartnerUserId(userId)!=null)
                userId = settingsRepository.findByPartnerUserId(userId).getUser().getId();
            else throw new MainAccountIsNotConnected();
        }

        if (calendar.isPeriodStart() && calendar.isPeriodEnd()) throw  new PeriodSameStartAndEnd();

        if (calendarRepository.findCalendarByUserIdAndDate(userId, calendar.getDate())!=null)
            calendar.setId(calendarRepository.findCalendarByUserIdAndDate(userId, calendar.getDate()).getId());
        calendar.setUser(userService.getUser(userId));

        return calendarRepository.save(calendar);
    }

}
