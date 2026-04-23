package com.wimdetroyer.actuator.endpoints.conditions;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the conditions endpoint.
 */
public record Conditions(RestClient restClient, String basePath) {

    /**
     * Get the conditions evaluation report.
     * GET /actuator/conditions
     */
    public ConditionsResponse get() {
        return restClient.get()
                .uri(basePath + "/conditions")
                .retrieve()
                .body(ConditionsResponse.class);
    }
}
