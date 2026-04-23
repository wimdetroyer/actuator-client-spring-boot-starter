package com.wimdetroyer.actuator.endpoints.shutdown;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the shutdown endpoint.
 */
public record Shutdown(RestClient restClient, String basePath) {

    /**
     * Perform a graceful shutdown of the application.
     * POST /actuator/shutdown
     *
     * Warning: This will shut down the target application!
     */
    public ShutdownResponse execute() {
        return restClient.post()
                .uri(basePath + "/shutdown")
                .retrieve()
                .body(ShutdownResponse.class);
    }
}
