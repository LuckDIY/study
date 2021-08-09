package com.study.quartz;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;


@Component
public class Job extends QuartzJobBean {

    Logger logger = LoggerFactory.getLogger(Job.class);


    @Autowired
    private Scheduler scheduler;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        logger.info("执行"+System.currentTimeMillis()/1000);

        scheduler.toString();


       /* Trigger trigger = jobExecutionContext.getTrigger();
        String cron = "0/5 * * * * ? *";

        try {
            CronTrigger cronTrigger = (CronTrigger)
                    scheduler.getTrigger(trigger.getKey());
            String cronExpression = cronTrigger.getCronExpression();
            if (!cron.equals(cronExpression)) {
                //创建表达式调度器
                CronScheduleBuilder cronSchedule =
                        CronScheduleBuilder.cronSchedule(cron);
                //重构
                cronTrigger = cronTrigger.getTriggerBuilder()
                        .withIdentity(trigger.getKey())
                        .withSchedule(cronSchedule)
                        .build();
                //scheduler.rescheduleJob(trigger.getKey(), cronTrigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }*/

    }
}
