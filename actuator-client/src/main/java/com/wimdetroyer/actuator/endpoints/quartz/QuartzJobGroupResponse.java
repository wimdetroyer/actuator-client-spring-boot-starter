package com.wimdetroyer.actuator.endpoints.quartz;

import java.util.Map;

/**
 * Response from the quartz/jobs/{group} endpoint.
 */
public record QuartzJobGroupResponse(
        String group,
        Map<String, QuartzJobSummary> jobs
) {
    public record QuartzJobSummary(
            String className
    ) {}
}
