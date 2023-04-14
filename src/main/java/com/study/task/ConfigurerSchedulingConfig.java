package com.study.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


@Slf4j
@Configuration
@EnableScheduling
public class ConfigurerSchedulingConfig implements SchedulingConfigurer {

    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        this.scheduledTaskRegistrar = scheduledTaskRegistrar;
    }

    public ScheduledTaskRegistrar getScheduledTaskRegistrar() {
        return scheduledTaskRegistrar;
    }
}
