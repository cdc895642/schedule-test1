package com.example.schedule;

//import org.springframework.context.annotation.Bean;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by cdc89 on 11.02.2017.
 */
@Configuration
public class QuartzConfiguration {
    @Bean
    public Job getQuartzJob() {
//        return context -> {
//            System.out.println("Quartz job");
//        };
        return new QuartzJob();

    }

    @Bean
    public Trigger getTrigger() {
        return newTrigger().withIdentity("CronTrigger1", "CronGroup1").withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
    }

    @Bean
    public JobDetail getJobDetail() {
        return newJob(getQuartzJob().getClass()).withIdentity("CronJob1", "CronGroup1").build();
    }

    @Bean
    public Scheduler getSchduler() throws SchedulerException, InterruptedException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //scheduler.start();
        scheduler.scheduleJob(getJobDetail(), getTrigger());
        //scheduler.pauseAll();
        //Thread.sleep(30000);
        //scheduler.start();
        return scheduler;
    }
}
