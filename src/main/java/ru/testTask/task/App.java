package ru.testTask.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Variables should be specified in work-calendar.properties
 * weekendDays=
 * shortenedDays=
 * start.time.of.the.working.day=
 * end.time.of.the.working.day=
 *
 **/

public class App
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);

        CalendarChecker checker = context.getBean("calendarChecker", CalendarChecker.class);
        System.out.println(checker.checkIfDateIsWeekend("09.05.2024"));
        System.out.println(checker.checkIfDateTimeAreNotWorking("06.05.2024", "09:00"));
    }
}
