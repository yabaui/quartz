package com.demo.quartz.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInfo {
    private String jobName;
    private String triggerName;
    private String triggerKey;
    private String cronSchedule;
}
