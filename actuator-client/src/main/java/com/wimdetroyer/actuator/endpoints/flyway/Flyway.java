package com.wimdetroyer.actuator.endpoints.flyway;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the flyway endpoint.
 */
public record Flyway(RestClient restClient, String basePath) {

    /**
     * Get Flyway database migration information.
     * GET /actuator/flyway
     */
    public FlywayResponse get() {
        return restClient.get()
                .uri(basePath + "/flyway")
                .retrieve()
                .body(FlywayResponse.class);
    }
}
