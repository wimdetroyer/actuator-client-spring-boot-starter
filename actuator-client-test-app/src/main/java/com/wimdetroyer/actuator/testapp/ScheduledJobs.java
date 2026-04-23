package com.wimdetroyer.actuator.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Sample scheduled jobs for testing the scheduledtasks actuator endpoint.
 */
@Component
public class ScheduledJobs {

    private static final Logger log = LoggerFactory.getLogger(ScheduledJobs.class);

    @Scheduled(fixedRate = 60000)
    public void fixedRateJob() {
        log.debug("Fixed rate job executed");
    }

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void fixedDelayJob() {
        log.debug("Fixed delay job executed");
    }

    @Scheduled(cron = "0 0 * * * *")
    public void hourlyJob() {
        log.debug("Hourly cron job executed");
    }
}
