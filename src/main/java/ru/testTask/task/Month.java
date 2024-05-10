package ru.testTask.task;


import java.util.ArrayList;
import java.util.List;

public class Month {

    private final List<Day> weekendsList;
    private final List<Day> shortenedDaysList;
    private final Schedule schedule;
    public Month(Schedule schedule, List<Integer> weekendDays, List<Integer> shortenedDays){
        weekendsList = new ArrayList<>();
        shortenedDaysList = new ArrayList<>();
        this.schedule = schedule;
        for(Integer day: weekendDays){
            weekendsList.add(new Day(true, day));
        }

        for(Integer day: shortenedDays){
            shortenedDaysList.add(new Day(false, day));
        }
    }

    public List<Day> getWeekendsList() {
        return weekendsList;
    }

    public List<Day> getShortenedDaysList() {
        return shortenedDaysList;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
