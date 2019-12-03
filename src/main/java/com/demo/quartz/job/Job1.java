package com.demo.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

@Slf4j
public class Job1 extends BaseJob {
    @Override
    public void doExecute(JobExecutionContext context) {
        log.info("### jon1 execute =====================");
    }
}
