package ru.testTask.task;

import java.util.Objects;

public class Day {
    private boolean isWeekendDay = false;
    private int numberOfADay;

    public Day(boolean isWeekendDay, int numberOfADay) {
        this.isWeekendDay = isWeekendDay;
        this.numberOfADay = numberOfADay;
    }
    public Day(){}

    public void setNumberOfADay(int numberOfADay) {
        this.numberOfADay = numberOfADay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return numberOfADay == day.numberOfADay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWeekendDay, numberOfADay);
    }

}
