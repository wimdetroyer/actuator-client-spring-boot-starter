package com.wimdetroyer.actuator.endpoints.quartz;

import java.util.List;

/**
 * Response from the quartz endpoint (overview).
 */
public record QuartzResponse(
        Jobs jobs,
        Triggers triggers
) {
    public record Jobs(
            List<String> groups
    ) {}

    public record Triggers(
            List<String> groups
    ) {}
}
