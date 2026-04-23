package com.wimdetroyer.actuator.endpoints.quartz;

import java.util.List;
import java.util.Map;

/**
 * Response from the quartz/triggers endpoint.
 */
public record QuartzTriggersResponse(
        Map<String, GroupInfo> groups
) {
    public record GroupInfo(
            boolean paused,
            List<String> triggers
    ) {}
}
