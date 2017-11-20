package com.example.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * Created by cdc89 on 11.02.2017.
 */
public class QuartzJob implements Job {
public void execute(JobExecutionContext context)
throws JobExecutionException {
//JobKey jobKey = context.getJobDetail().getKey();
System.out.println("Quartz job " + new Date());
}
}

