package com.example.schedule;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by cdc89 on 06.02.2017.
 */
@Component
public class Schedule1 {
    /**
     * work well.
     */
    //@Scheduled(fixedRate = 70000)
    public void scheduleFixedRateTask() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 10000);
    }

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private CronTrigger cronTrigger;

    @Autowired
    Scheduler scheduler;

    @Autowired
    JobDetail jobDetail;

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() throws SchedulerException, InterruptedException {
        /**
         * work fine
         */
        // taskScheduler.scheduleAtFixedRate(()->System.out.println(System.currentTimeMillis()/10000), 10000);
        /**
         * work fine with pause
         */
        ScheduledFuture<?> sf=taskScheduler.schedule(()->System.out.println("cronTrigger - "+new Date()), cronTrigger);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        Thread.sleep(30000);
        System.out.println("end");
        sf.cancel(true);
        Thread.sleep(30000);
        taskScheduler.schedule(()->System.out.println("cronTrigger - "+new Date()), cronTrigger);
        /**
         * quart schedule
         * pause does not work as expected
         */
//        scheduler.start();
//        scheduler.pauseJob(jobDetail.getKey());
//        System.out.println("pause");
//        Thread.sleep(30000);
//        System.out.println("end");
//        scheduler.resumeJob(jobDetail.getKey());
    }
}
