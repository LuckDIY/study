package com.study.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class TaskDemo {

    @Autowired
    private ConfigurerSchedulingConfig configurerSchedulingConfig;

    @Autowired
    private ScheduledAnnotationBeanPostProcessor scheduledAnnotationProcessor;

    @Scheduled(cron = "0/10 * * * * ?")
    public void test() {

        log.error("发生异常:{}", System.currentTimeMillis());
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void refresh(){
        ScheduledTaskRegistrar scheduledTaskRegistrar = configurerSchedulingConfig.getScheduledTaskRegistrar();

        List<TriggerTask> triggerTaskList = scheduledTaskRegistrar.getTriggerTaskList();
        scheduledTaskRegistrar.getScheduledTasks();
        log.info("triggerTaskList :{}",triggerTaskList);


        Set<ScheduledTask> scheduledTasks = scheduledAnnotationProcessor.getScheduledTasks();
        log.info("scheduledTasks :{}",scheduledTasks);

    }

}
