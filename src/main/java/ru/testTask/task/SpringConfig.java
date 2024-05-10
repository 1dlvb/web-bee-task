package ru.testTask.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
@ComponentScan("ru.testTask.task")
@PropertySource("classpath:work-calendar.properties")
public class SpringConfig {

    @Value("#{'${weekendDays}'.split(',')}")
    private List<Integer> weekendDays;

    @Value("#{'${shortenedDays}'.split(',')}")
    private List<Integer> shortenedDays;

    @Value("#{'${start.time.of.the.working.day}'}")
    private String startTimeOfTheWorkingDay;

    @Value("#{'${end.time.of.the.working.day}'}")
    private String endTimeOfTheWorkingDay;

    @Bean
    @Scope(value = "prototype")
    public Day day(){
        return new Day();
    }
    @Bean
    public Schedule schedule(){
        return new Schedule(startTimeOfTheWorkingDay, endTimeOfTheWorkingDay);
    }

    @Bean
    @Scope(value = "prototype")
    public Month month(){
        return new Month(schedule(), weekendDays, shortenedDays);
    }

    @Bean
    public CalendarChecker calendarChecker(){
        return new CalendarChecker(month());
    }

}