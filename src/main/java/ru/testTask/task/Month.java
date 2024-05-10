package ru.testTask.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

public class Month {

    private final List<Day> weekendsList;
    public Month(List<Integer> weekendDays){
        weekendsList = new ArrayList<>();
        for(Integer day: weekendDays){
            weekendsList.add(new Day(true, day));
        }
    }

    public List<Day> getWeekendsList() {
        return weekendsList;
    }
}
