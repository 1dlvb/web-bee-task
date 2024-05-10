package ru.testTask.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;

/**
 * Hello world!
 **/

public class App
{
    public static void main( String[] args ) throws ParseException{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class);

        WeekendChecker checker = context.getBean("weekendChecker", WeekendChecker.class);
        System.out.println(checker.checkIfDayIsWeekend("09.05.2024"));
    }
}
