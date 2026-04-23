package com.wimdetroyer.actuator.endpoints.scheduledtasks;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the scheduledtasks endpoint.
 */
public record ScheduledTasks(RestClient restClient, String basePath) {

    /**
     * Get all scheduled tasks.
     * GET /actuator/scheduledtasks
     */
    public ScheduledTasksResponse get() {
        return restClient.get()
                .uri(basePath + "/scheduledtasks")
                .retrieve()
                .body(ScheduledTasksResponse.class);
    }
}
