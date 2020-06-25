package com.demo.quartz.common;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import com.demo.quartz.common.type.JobClassType;
import com.demo.quartz.resource.ScheduleInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class SchedulerManagement {

    @Value("${schedule.file.path}")
    private String scheduleFilePath;

    @PostConstruct
    public void start() throws SchedulerException, IOException {

        log.info("### Job init start============");

        File file = new File(scheduleFilePath);

        if (!file.isFile() || !file.exists()) {
            this.printLogError("file empty");
            return;
        }

        List<ScheduleInfo> scheduleInfoList = this.getScheduleInfoList(file);

        if (CollectionUtils.isEmpty(scheduleInfoList)) {
            this.printLogError("scheduleInfoList empty");
            return;
        }

        JobDetail jobDetail;

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        CommonVariable.scheduler = schedulerFactory.getScheduler();
        CommonVariable.scheduler.start();

        for (ScheduleInfo scheduleInfo: scheduleInfoList) {
            JobClassType jobClass = JobClassType.getJobClassByJobName(scheduleInfo.getJobName());

            jobDetail = JobBuilder.newJob(jobClass.getJob()).build();
            CommonVariable.scheduler.scheduleJob(jobDetail, CommonVariable.getTrigger(scheduleInfo.getTriggerKey(), scheduleInfo.getCronSchedule()));
        }

        log.info("### Job init end============");
    }

    private List<ScheduleInfo> getScheduleInfoList(File file) {

        ObjectMapper mapper = this.getObjectMapperConstructor();

        try {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, ScheduleInfo.class));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            this.printLogError(e.getMessage());
            return Collections.emptyList();
        }
    }

    private ObjectMapper getObjectMapperConstructor() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private void printLogError(String message) {
        message = "스케쥴을 실행할 수 없습니다. / reason : " + message;

        log.error(message);

        System.exit(0);
    }
}
