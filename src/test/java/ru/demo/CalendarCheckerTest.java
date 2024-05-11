package ru.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.testTask.task.CalendarChecker;

import ru.testTask.task.Month;
import ru.testTask.task.Schedule;

import java.util.Arrays;
import java.util.List;



public class CalendarCheckerTest {
    List<Integer> weekendDays = Arrays.asList(1,4,5,9,10,11,12,18,19,25,26);
    List<Integer> shortenedDays = List.of(8);
    CalendarChecker checker = new CalendarChecker(new Month(new Schedule("09:00", "18:00"), weekendDays, shortenedDays));
    @Test
    public void timeIsLessThanStartTime()
    {
        // not working time
        Assertions.assertTrue(checker.checkIfDateTimeAreNotWorking("07.05.2024", "07:00"));
    }
    @Test
    public void timeIsGreaterThanEndTime()
    {
        // not working time
        Assertions.assertTrue(checker.checkIfDateTimeAreNotWorking("07.05.2024", "19:00"));
    }
    @Test
    public void timeIsEqualToStartTime()
    {
        // working time
        Assertions.assertFalse(checker.checkIfDateTimeAreNotWorking("07.05.2024", "09:00"));
    }

    @Test
    public void dateIsShortenedAndTimeIsEqualsToShortenedTime()
    {
        // not working time
        Assertions.assertTrue(checker.checkIfDateTimeAreNotWorking("08.05.2024", "17:00"));
    }
    @Test
    public void dateIsShortenedAndTimeIsEqualsToEndTime()
    {
        // working time
        Assertions.assertTrue(checker.checkIfDateTimeAreNotWorking("08.05.2024", "18:00"));
    }
    @Test
    public void timeIsEqualToEndTime()
    {
        // not working time
        Assertions.assertTrue(checker.checkIfDateTimeAreNotWorking("07.05.2024", "18:00"));
    }
    @Test
    public void dateIsWeekend()
    {
        // is weekend
        Assertions.assertTrue(checker.checkIfDateIsWeekend("01.05.2024"));
    }
    @Test
    public void dateIsNotWeekend()
    {
        // is weekend
        Assertions.assertFalse(checker.checkIfDateIsWeekend("03.05.2024"));
    }
    @Test
    public void dateIsShortened()
    {
        // is not weekend
        Assertions.assertFalse(checker.checkIfDateIsWeekend("08.05.2024"));
    }
    @Test
    public void wrongDateFormatInCheckIfDateIsWeekend()
    {
        Assertions.assertThrows( IllegalArgumentException.class, () -> checker.checkIfDateIsWeekend("08/05.2024"));
    }
    @Test
    public void wrongDateFormatInCheckIfDateTimeAreNotWorking()
    {
        Assertions.assertThrows( IllegalArgumentException.class, () ->
                checker.checkIfDateTimeAreNotWorking("08/05.2024", "17:39"));
    }
    @Test
    public void wrongTimeFormat()
    {
        Assertions.assertThrows( IllegalArgumentException.class, () ->
                checker.checkIfDateTimeAreNotWorking("08.05.2024", "17.39"));
    }
    @Test
    public void negativeDate()
    {
        Assertions.assertThrows( IllegalArgumentException.class, () -> checker.checkIfDateIsWeekend("-08.05.2024"));
    }
    @Test
    public void negativeTime()
    {
        Assertions.assertThrows( IllegalArgumentException.class, () ->
                checker.checkIfDateTimeAreNotWorking("08.05.2024", "-17:39"));    }
}
