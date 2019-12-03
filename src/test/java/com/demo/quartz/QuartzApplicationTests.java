package com.demo.quartz;

import com.demo.quartz.job.Job1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzApplicationTests {

    @Test
    public void helloJonTest() throws Exception {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(Job1.class).build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("5 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
