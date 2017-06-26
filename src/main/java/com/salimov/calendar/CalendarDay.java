package com.salimov.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CalendarDay {

    private enum Color {

        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        RESET("\u001B[0m");

        private final String value;

        Color(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    private final LocalDate localDate;

    CalendarDay(final LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        final String string;
        if (isNowDay()) {
            string = nowDayToString();
        } else if (isWeekend()) {
            string = weekendDayToString();
        } else {
            string = "" + getDayOfMonth();
        }
        return string;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final CalendarDay calendarDay = (CalendarDay) object;
        return this.localDate.equals(calendarDay.localDate);
    }

    @Override
    public int hashCode() {
        return this.localDate.hashCode();
    }

    int getDayOfMonth() {
        return this.localDate.getDayOfMonth();
    }

    DayOfWeek getDayOfWeek() {
        return this.localDate.getDayOfWeek();
    }

    int getDayOfWeekValue() {
        final DayOfWeek dayOfWeek = getDayOfWeek();
        return dayOfWeek.getValue();
    }

    boolean isNowDay() {
        final LocalDate now = nowLocalDate();
        return this.localDate.equals(now);
    }

    boolean isWeekend() {
        final int dayOfWeekValue = getDayOfWeekValue();
        return (dayOfWeekValue > 5);
    }

    private LocalDate nowLocalDate() {
        return LocalDate.now();
    }

    private String nowDayToString() {
        return coloredDayToString(Color.GREEN);
    }

    private String weekendDayToString() {
        return coloredDayToString(Color.RED);
    }

    private String coloredDayToString(final Color color) {
        return color.toString() + getDayOfMonth() + Color.RESET;
    }
}
