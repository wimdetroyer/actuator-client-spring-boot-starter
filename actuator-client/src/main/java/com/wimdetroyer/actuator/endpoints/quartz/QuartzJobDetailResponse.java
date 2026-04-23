package com.wimdetroyer.actuator.endpoints.quartz;

import java.util.List;
import java.util.Map;

/**
 * Response from the quartz/jobs/{group}/{name} endpoint.
 */
public record QuartzJobDetailResponse(
        String group,
        String name,
        String description,
        String className,
        boolean durable,
        boolean requestRecovery,
        Map<String, Object> data,
        List<Trigger> triggers
) {
    public record Trigger(
            String group,
            String name,
            String previousFireTime,
            String nextFireTime,
            int priority
    ) {}

    /**
     * Returns a type-safe accessor for the job data map.
     *
     * @return the job data accessor
     */
    public JobDataAccessor dataAccessor() {
        return new JobDataAccessor(data);
    }
}
