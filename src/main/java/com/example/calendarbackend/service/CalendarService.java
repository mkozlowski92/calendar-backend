package com.example.calendarbackend.service;

import com.example.calendarbackend.exception.AccountDoesNotExist;
import com.example.calendarbackend.exception.MainAccountIsNotConnected;
import com.example.calendarbackend.exception.PeriodSameStartAndEnd;
import com.example.calendarbackend.model.Calendar;
import com.example.calendarbackend.repository.CalendarRepository;
import com.example.calendarbackend.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Long mainUserId = getMainAccountUserId(userId);

        if (calendar.isPeriodStart() && calendar.isPeriodEnd()) throw  new PeriodSameStartAndEnd();

        if (calendarRepository.findCalendarByUserIdAndDate(mainUserId, calendar.getDate())!=null)
            calendar.setId(calendarRepository.findCalendarByUserIdAndDate(mainUserId, calendar.getDate()).getId());
        calendar.setUser(userService.getUser(mainUserId));

        return calendarRepository.save(calendar);
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
    public List<Calendar> getCalendar(Long userId, Long year, Long month) throws AccountDoesNotExist, MainAccountIsNotConnected {

        Long mainUserId = getMainAccountUserId(userId);

        return calendarRepository.findAllByUserId(mainUserId).stream().filter(c->c.getDate().getYear()==year&&c.getDate().getMonthValue()==month).toList();

    }

    /**
     * Gets ID of Main Account for Partner Account
     * @param userId - user ID.
     * @return ID of Main account.
     * @throws AccountDoesNotExist - Account with this ID doesn't exist.
     * @throws MainAccountIsNotConnected - Main account isn't connected to this partner account.
     */
    private Long getMainAccountUserId(Long userId) throws AccountDoesNotExist, MainAccountIsNotConnected {
        if (!userService.isMainAccount(userId)) {
            if (settingsRepository.findByPartnerUserId(userId) != null)
                userId = settingsRepository.findByPartnerUserId(userId).getUser().getId();
            else throw new MainAccountIsNotConnected();
        }
        return userId;
    }

}
