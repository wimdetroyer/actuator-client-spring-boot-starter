package com.wimdetroyer.actuator.endpoints.env;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the env endpoint.
 */
public record Env(RestClient restClient, String basePath) {

    /**
     * Get all environment properties.
     * GET /actuator/env
     */
    public EnvResponse getAll() {
        return restClient.get()
                .uri(basePath + "/env")
                .retrieve()
                .body(EnvResponse.class);
    }

    /**
     * Get a specific environment property.
     * GET /actuator/env/{property}
     */
    public EnvPropertyResponse get(String property) {
        return restClient.get()
                .uri(basePath + "/env/{property}", property)
                .retrieve()
                .body(EnvPropertyResponse.class);
    }
}
