package com.wimdetroyer.actuator.endpoints.quartz;

import java.time.Instant;
import java.util.Map;

/**
 * Response from the quartz/triggers/{group}/{name} endpoint.
 */
public record QuartzTriggerDetailResponse(
        String group,
        String name,
        String description,
        String type,
        QuartzTriggerType triggerType,
        String state,
        String calendarName,
        Instant startTime,
        Instant endTime,
        Instant previousFireTime,
        Instant nextFireTime,
        int priority,
        Instant finalFireTime,
        Map<String, Object> data,
        CronDetails cron,
        SimpleDetails simple,
        DailyTimeIntervalDetails dailyTimeInterval,
        CalendarIntervalDetails calendarInterval
) {
    public record CronDetails(
            String expression,
            String timeZone
    ) {}

    public record SimpleDetails(
            long interval,
            int repeatCount,
            int timesTriggered
    ) {}

    public record DailyTimeIntervalDetails(
            String startTimeOfDay,
            String endTimeOfDay,
            String daysOfWeek,
            int interval,
            String intervalUnit,
            int repeatCount,
            int timesTriggered
    ) {}

    public record CalendarIntervalDetails(
            int interval,
            String intervalUnit,
            int timesTriggered,
            boolean preserveHourOfDayAcrossDaylightSavings,
            boolean skipDayIfHourDoesNotExist
    ) {}

    /**
     * Returns a type-safe accessor for the trigger data map.
     *
     * @return the job data accessor
     */
    public JobDataAccessor dataAccessor() {
        return new JobDataAccessor(data);
    }
}
