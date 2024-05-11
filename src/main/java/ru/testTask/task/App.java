package ru.testTask.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;



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
