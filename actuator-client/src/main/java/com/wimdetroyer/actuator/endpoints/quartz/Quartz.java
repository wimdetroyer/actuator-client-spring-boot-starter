package com.wimdetroyer.actuator.endpoints.quartz;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the quartz endpoint.
 */
public record Quartz(RestClient restClient, String basePath) {

    /**
     * Get Quartz scheduler overview.
     * GET /actuator/quartz
     */
    public QuartzResponse get() {
        return restClient.get()
                .uri(basePath + "/quartz")
                .retrieve()
                .body(QuartzResponse.class);
    }

    /**
     * Get all Quartz jobs.
     * GET /actuator/quartz/jobs
     */
    public QuartzJobsResponse getJobs() {
        return restClient.get()
                .uri(basePath + "/quartz/jobs")
                .retrieve()
                .body(QuartzJobsResponse.class);
    }

    /**
     * Get all Quartz triggers.
     * GET /actuator/quartz/triggers
     */
    public QuartzTriggersResponse getTriggers() {
        return restClient.get()
                .uri(basePath + "/quartz/triggers")
                .retrieve()
                .body(QuartzTriggersResponse.class);
    }

    /**
     * Get jobs in a specific group.
     * GET /actuator/quartz/jobs/{group}
     */
    public QuartzJobGroupResponse getJobGroup(String group) {
        return restClient.get()
                .uri(basePath + "/quartz/jobs/{group}", group)
                .retrieve()
                .body(QuartzJobGroupResponse.class);
    }

    /**
     * Get triggers in a specific group.
     * GET /actuator/quartz/triggers/{group}
     */
    public QuartzTriggerGroupResponse getTriggerGroup(String group) {
        return restClient.get()
                .uri(basePath + "/quartz/triggers/{group}", group)
                .retrieve()
                .body(QuartzTriggerGroupResponse.class);
    }

    /**
     * Get a specific job by group and name.
     * GET /actuator/quartz/jobs/{group}/{name}
     */
    public QuartzJobDetailResponse getJob(String group, String name) {
        return restClient.get()
                .uri(basePath + "/quartz/jobs/{group}/{name}", group, name)
                .retrieve()
                .body(QuartzJobDetailResponse.class);
    }

    /**
     * Trigger a specific job by group and name.
     * POST /actuator/quartz/jobs/{group}/{name}
     */
    public void triggerJob(String group, String name) {
        restClient.post()
                .uri(basePath + "/quartz/jobs/{group}/{name}", group, name)
                .retrieve()
                .toBodilessEntity();
    }

    /**
     * Get a specific trigger by group and name.
     * GET /actuator/quartz/triggers/{group}/{name}
     */
    public QuartzTriggerDetailResponse getTrigger(String group, String name) {
        return restClient.get()
                .uri(basePath + "/quartz/triggers/{group}/{name}", group, name)
                .retrieve()
                .body(QuartzTriggerDetailResponse.class);
    }
}
