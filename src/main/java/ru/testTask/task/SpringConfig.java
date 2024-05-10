package ru.testTask.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.util.List;

@Configuration
@ComponentScan("ru.testTask.task")
@PropertySource("classpath:month.properties")
public class SpringConfig {

    @Value("#{'${weekendDays}'.split(',')}")
    private List<Integer> weekendDays;

    @Bean
    @Scope(value = "prototype")
    public Day day(){
        return new Day();
    }

    @Bean
    @Scope(value = "prototype")
    public Month month(){
        return new Month(weekendDays);
    }

    @Bean
    public WeekendChecker weekendChecker(){
        return new WeekendChecker(month());
    }

}
