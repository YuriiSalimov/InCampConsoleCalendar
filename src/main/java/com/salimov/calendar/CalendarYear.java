package com.salimov.calendar;

import java.time.DateTimeException;
import java.time.Year;
import java.time.YearMonth;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CalendarYear {

    private final static int MIN_MONTH_NUMBER = 1;
    private final static int MAX_MONTH_NUMBER = 12;

    private final static String INVALID_MONTH_NUMBER_MESSAGE =
            "Invalid value for MonthOfYear (valid values " + MIN_MONTH_NUMBER +
                    " - " + MAX_MONTH_NUMBER + "): %d";

    private final Year year;

    private CalendarYear() {
        this(Year.now());
    }

    private CalendarYear(final Year year) {
        this.year = year;
    }

    public static CalendarYear now() {
        return new CalendarYear();
    }

    public static CalendarYear of(final int number) {
        final Year year = Year.of(number);
        return new CalendarYear(year);
    }

    public CalendarMonth calendarMonth(final int number) throws DateTimeException {
        final CalendarMonth calendarMonth;
        if (number == 0) {
            calendarMonth = nowCalendarMonth();
        } else {
            checkValidMonthNumber(number);
            calendarMonth = calendarMonthByNumber(number);
        }
        return calendarMonth;
    }

    public CalendarMonth nowCalendarMonth() {
        final YearMonth yearMonth = nowYearMonth();
        return new CalendarMonth(yearMonth);
    }

    private YearMonth nowYearMonth() {
        final YearMonth yearMonth = YearMonth.now();
        return this.year.atMonth(yearMonth.getMonth());
    }

    private CalendarMonth calendarMonthByNumber(final int number) {
        final YearMonth yearMonth = yearMonth(number);
        return new CalendarMonth(yearMonth);
    }

    private YearMonth yearMonth(final int number) {
        return this.year.atMonth(number);
    }

    private void checkValidMonthNumber(final int number) throws DateTimeException {
        if (!isValidMonthNumber(number)) {
            final String message = createInvalidMonthNumberMessage(number);
            throw new DateTimeException(message);
        }
    }

    private boolean isValidMonthNumber(final int number) {
        return (number >= MIN_MONTH_NUMBER && number <= MAX_MONTH_NUMBER);
    }

    private String createInvalidMonthNumberMessage(final int number) {
        return String.format(INVALID_MONTH_NUMBER_MESSAGE, number);
    }
}
