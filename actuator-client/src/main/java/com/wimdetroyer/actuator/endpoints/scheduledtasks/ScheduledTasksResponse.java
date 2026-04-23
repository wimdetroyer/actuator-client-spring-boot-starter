package com.wimdetroyer.actuator.endpoints.scheduledtasks;

import java.util.List;

/**
 * Response from the scheduledtasks endpoint.
 */
public record ScheduledTasksResponse(
        List<CronTask> cron,
        List<FixedDelayTask> fixedDelay,
        List<FixedRateTask> fixedRate,
        List<CustomTask> custom
) {
    public record CronTask(
            Runnable runnable,
            String expression
    ) {}

    public record FixedDelayTask(
            Runnable runnable,
            long initialDelay,
            long interval
    ) {}

    public record FixedRateTask(
            Runnable runnable,
            long initialDelay,
            long interval
    ) {}

    public record CustomTask(
            Runnable runnable,
            String trigger
    ) {}

    public record Runnable(
            String target
    ) {}
}
