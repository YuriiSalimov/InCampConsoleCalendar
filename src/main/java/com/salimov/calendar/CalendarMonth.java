package com.salimov.calendar;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CalendarMonth {

    private final YearMonth yearMonth;

    CalendarMonth(final YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    @Override
    public String toString() {
        final List<CalendarDay> calendarDays = getCalendarsDay();
        final StringBuilder sb = new StringBuilder();
        sb.append("Mo\tTu\tWe\tTh\tFr\tSa\tSu\n");
        final CalendarDay firstDay = calendarDays.get(0);
        for (int i = 0; i < firstDay.getDayOfWeekValue() - 1; i++) {
            sb.append("\t");
        }
        for (CalendarDay calendarDay : calendarDays) {
            sb.append(calendarDay);
            if (calendarDay.getDayOfWeekValue() >= 7) {
                sb.append("\n");
            } else {
                sb.append("\t");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final CalendarMonth calendarMonth = (CalendarMonth) object;
        return this.yearMonth.equals(calendarMonth.yearMonth);
    }

    @Override
    public int hashCode() {
        return this.yearMonth.hashCode();
    }

    private List<CalendarDay> getCalendarsDay() {
        final List<CalendarDay> calendarDays = new ArrayList<>();
        LocalDate localDate;
        CalendarDay calendarDay;
        for (int i = 1; i <= this.yearMonth.lengthOfMonth(); i++) {
            localDate = this.yearMonth.atDay(i);
            calendarDay = new CalendarDay(localDate);
            calendarDays.add(calendarDay);
        }
        return calendarDays;
    }
}
