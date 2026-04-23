package com.wimdetroyer.actuator.testapp;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz job configuration for testing the quartz actuator endpoint.
 */
@Configuration
public class QuartzJobConfig {

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity("sampleJob", "sampleGroup")
                .withDescription("Sample Quartz Job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob("sampleJob", "sampleGroup")
                .withIdentity("sampleTrigger", "sampleGroup")
                .withDescription("Sample trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever())
                .build();
    }

    @Bean
    public JobDetail cronJobDetail() {
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity("cronJob", "cronGroup")
                .withDescription("Sample Cron Job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger cronJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob("cronJob", "cronGroup")
                .withIdentity("cronTrigger", "cronGroup")
                .withDescription("Cron trigger - every hour")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
                .build();
    }

    public static class SampleJob implements Job {
        private static final Logger log = LoggerFactory.getLogger(SampleJob.class);

        @Override
        public void execute(JobExecutionContext context) {
            log.info("Executing Quartz job: {}", context.getJobDetail().getKey());
        }
    }
}
