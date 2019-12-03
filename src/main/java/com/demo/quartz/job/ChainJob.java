package com.demo.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;

@Slf4j
public abstract class ChainJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.beforeJob(context);
        this.doExecute(context);
        this.afterJob(context);
        this.scheduleNextJob(context);
    }

    private void beforeJob(JobExecutionContext context) {
        log.info("@@@ before job execute");
    }

    abstract void doExecute(JobExecutionContext context);

    private void afterJob(JobExecutionContext context) {
        log.info("@@@ before job execute");

        List<JobDetail> jobDetailList = (List<JobDetail>) context.getJobDetail().getJobDataMap().get("jobDetailList");

        if (CollectionUtils.isEmpty(jobDetailList)) {
            return;
        }

        if (jobDetailList.size() > 0) {
            jobDetailList.remove(0);
        }
    }

    private void scheduleNextJob(JobExecutionContext context) {
        log.info("$$$ Schedule next job");

        List<JobDetail> jobDetailList = (List<JobDetail>) context.getJobDetail().getJobDataMap().get("jobDetailList");

        if (CollectionUtils.isEmpty(jobDetailList)) {
            return;
        }

        if (jobDetailList.size() > 0) {
            JobDetail nextJobDetail = jobDetailList.get(0);
            nextJobDetail.getJobDataMap().put("jobDetailList", jobDetailList);
            Trigger nowTrigger = TriggerBuilder.newTrigger().build();

            try {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                scheduler.scheduleJob(nextJobDetail, nowTrigger);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Function<JobExecutionContext, List<JobDetail>> getJobDetailList = (context -> (List<JobDetail>) context.getJobDetail().getJobDataMap().get("jobDetailList"));
}
