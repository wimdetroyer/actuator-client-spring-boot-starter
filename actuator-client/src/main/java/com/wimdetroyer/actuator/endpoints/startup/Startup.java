package com.wimdetroyer.actuator.endpoints.startup;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the startup endpoint.
 */
public record Startup(RestClient restClient, String basePath) {

    /**
     * Get application startup information.
     * GET /actuator/startup
     */
    public StartupResponse get() {
        return restClient.get()
                .uri(basePath + "/startup")
                .retrieve()
                .body(StartupResponse.class);
    }

    /**
     * Get and drain the startup buffer (returns and clears startup events).
     * POST /actuator/startup
     */
    public StartupResponse drain() {
        return restClient.post()
                .uri(basePath + "/startup")
                .retrieve()
                .body(StartupResponse.class);
    }
}
