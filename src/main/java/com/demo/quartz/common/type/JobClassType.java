package com.demo.quartz.common.type;

import com.demo.quartz.job.Job1;
import com.demo.quartz.job.Job2;
import org.quartz.Job;

import java.util.Arrays;

public enum JobClassType {
    TYPE_JOB1(Job1.class, "job1"),
    TYPE_JOB2(Job2.class, "job2"),
    ;

    private Class<? extends Job> job;
    private String jobName;

    JobClassType(Class<? extends Job> job, String jobName) {
        this.job = job;
        this.jobName = jobName;
    }

    public Class<? extends Job> getJob() {
        return job;
    }

    public String getJobName() {
        return jobName;
    }

    public static JobClassType getJobClassByJobName(String jobName) {
        return Arrays.stream(JobClassType.values()).filter(jobClass -> jobClass.getJobName().equals(jobName)).findFirst().orElse(JobClassType.TYPE_JOB1);
    }
}
