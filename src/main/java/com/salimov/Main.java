package com.salimov;

import com.salimov.calendar.CalendarMonth;
import com.salimov.calendar.CalendarYear;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        final int monthNumber = parseMonthNumber(args);
        final CalendarMonth calendarMonth = calendarMonth(monthNumber);
        System.out.println(calendarMonth);
    }

    private static int parseMonthNumber(final String[] args) {
        final int monthNumber;
        if (args.length > 0) {
            monthNumber = Integer.parseInt(args[0]);
        } else {
            monthNumber = 0;
        }
        return monthNumber;
    }

    private static CalendarMonth calendarMonth(final int number) {
        final CalendarYear calendarYear = CalendarYear.now();
        return calendarYear.calendarMonth(number);
    }
}
