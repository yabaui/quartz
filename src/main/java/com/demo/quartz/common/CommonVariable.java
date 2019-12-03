package com.demo.quartz.common;

import org.quartz.*;

class CommonVariable {
    static Scheduler scheduler;

    static Trigger getTrigger(String triggerKey, String cronSchedule) {
        return TriggerBuilder.newTrigger()
                .withIdentity(new TriggerKey(triggerKey))
                .withSchedule(CronScheduleBuilder.cronSchedule(cronSchedule))
                .build();
    }
}
