package com.wimdetroyer.actuator.endpoints.quartz;

import java.util.Map;

/**
 * Response from the quartz/triggers/{group} endpoint.
 */
public record QuartzTriggerGroupResponse(
        String group,
        boolean paused,
        Triggers triggers
) {
    public record Triggers(
            Map<String, TriggerSummary> cron,
            Map<String, TriggerSummary> simple,
            Map<String, TriggerSummary> dailyTimeInterval,
            Map<String, TriggerSummary> calendarInterval,
            Map<String, TriggerSummary> custom
    ) {}

    public record TriggerSummary(
            String previousFireTime,
            String nextFireTime,
            int priority,
            String expression
    ) {}
}
