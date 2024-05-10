package ru.testTask.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

public class WeekendChecker {
    private final Month month;

    public WeekendChecker(Month month) {
        this.month = month;
    }

    public boolean checkIfDayIsWeekend(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            dateFormat.parse(date); // Attempt to parse the date string
            Day day = new Day();
            day.setNumberOfADay(Integer.parseInt(date.substring(0, 2)));
            return month.getWeekendsList().contains(day);
        } catch (DateTimeParseException e) {
            // Throw a new exception if the date is not in the correct pattern
            throw new IllegalArgumentException("Invalid date format. Date must be in the format dd-MM-yyyy");
        }
    }
}
