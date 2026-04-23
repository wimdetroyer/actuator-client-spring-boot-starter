package com.wimdetroyer.actuator.endpoints.mappings;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the mappings endpoint.
 */
public record Mappings(RestClient restClient, String basePath) {

    /**
     * Get all request mappings.
     * GET /actuator/mappings
     */
    public MappingsResponse get() {
        return restClient.get()
                .uri(basePath + "/mappings")
                .retrieve()
                .body(MappingsResponse.class);
    }
}
