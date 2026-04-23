package com.wimdetroyer.actuator.endpoints.quartz;

import java.util.List;
import java.util.Map;

/**
 * Response from the quartz/jobs endpoint.
 */
public record QuartzJobsResponse(
        Map<String, GroupInfo> groups
) {
    public record GroupInfo(
            List<String> jobs
    ) {}
}
