package com.wimdetroyer.actuator.endpoints.health;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the health endpoint.
 */
public record Health(RestClient restClient, String basePath) {

    /**
     * Get the overall health status.
     * GET /actuator/health
     */
    public HealthResponse get() {
        return restClient.get()
                .uri(basePath + "/health")
                .retrieve()
                .body(HealthResponse.class);
    }

    /**
     * Get the health status of a specific component.
     * GET /actuator/health/{component}
     */
    public HealthResponse getComponent(String component) {
        return restClient.get()
                .uri(basePath + "/health/{component}", component)
                .retrieve()
                .body(HealthResponse.class);
    }

    /**
     * Get the health status of a nested component.
     * GET /actuator/health/{component}/{subComponent}
     */
    public HealthResponse getComponent(String component, String subComponent) {
        return restClient.get()
                .uri(basePath + "/health/{component}/{subComponent}", component, subComponent)
                .retrieve()
                .body(HealthResponse.class);
    }
}
