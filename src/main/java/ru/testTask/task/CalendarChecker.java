package ru.testTask.task;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Variables should be specified in work-calendar.properties
 * weekendDays=
 * shortenedDays=
 * start.time.of.the.working.day=
 * end.time.of.the.working.day=
 *
 **/
public class CalendarChecker {
    private final Month month;

    public CalendarChecker(Month month) {
        this.month = month;
    }

    private void validateDate(String date) throws IllegalArgumentException {
        String datePatternRegex =
                "[0-9]{1,2}.[0-9]{1,2}.[0-9]{4}";
        Pattern pattern = Pattern.compile(datePatternRegex);
        Matcher matcher = pattern.matcher(date);
        if(!matcher.matches())
            throw new IllegalArgumentException("Invalid date format. Date must be in the format dd.MM.yyyy");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            dateFormat.parse(date);
        } catch (DateTimeParseException | ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Date must be in the format dd.MM.yyyy");
        }
    }

    private void validateTime(String time) throws IllegalArgumentException {
        Pattern timePatternRegex = Pattern.compile("(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]");
        Matcher matcher = timePatternRegex.matcher(time);
        if(!matcher.matches())
            throw new IllegalArgumentException("Invalid time format. Time must be in the format hh:mm");
    }

    private String decrementTimeForShortenedDays(String time){
        int hour = Integer.parseInt(time.substring(0, 2))-1;
        if(hour <= 0) time = String.format("%s%s%s", 0, 0, time.substring(2, 5));
        else if(hour < 10) time = String.format("%s%s%s", 0, Integer.parseInt(time.substring(0, 2))-1, time.substring(2, 5));
        else time = String.format("%s%s", Integer.parseInt(time.substring(0, 2))-1, time.substring(2, 5));
        return time;
    }

    private boolean timeComparator(String time1, String time2){
        int hour1 = Integer.parseInt(time1.substring(0, 2));
        int minutes1 = Integer.parseInt(time1.substring(3, 5));
        int hour2 = Integer.parseInt(time2.substring(0, 2));
        int minutes2 = Integer.parseInt(time2.substring(3, 5));
        return hour1 > hour2 || hour1 == hour2 && minutes1 >= minutes2;
    }

    /**
     * @param date pattern dd.MM.yyyy
     * @return true if date is weekend
     */
    public boolean checkIfDateIsWeekend(String date) throws IllegalArgumentException {
        validateDate(date);
        Day day = new Day();
        day.setNumberOfADay(Integer.parseInt(date.substring(0, 2)));
        return month.getWeekendsList().contains(day);
    }

    /**
     * @param date pattern dd.MM.yyyy
     * @param time pattern hh:mm
     * @return true if day isn't working one
     */
    public boolean checkIfDateTimeAreNotWorking(String date, String time) throws IllegalArgumentException {
        validateTime(time);
        Day day = new Day();
        day.setNumberOfADay(Integer.parseInt(date.substring(0, 2)));
        if(checkIfDateIsWeekend(date)) return true;
        String startTime = month.getSchedule().startTimeOfTheWorkingDay();
        String endTime = month.getSchedule().endTimeOfTheWorkingDay();
        if (month.getShortenedDaysList().contains(day)) endTime = decrementTimeForShortenedDays(endTime);

        return timeComparator(time, endTime) || !timeComparator(time, startTime);
    }
}
