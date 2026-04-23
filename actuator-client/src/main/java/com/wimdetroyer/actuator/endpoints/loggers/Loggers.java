package com.wimdetroyer.actuator.endpoints.loggers;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the loggers endpoint.
 */
public record Loggers(RestClient restClient, String basePath) {

    /**
     * Get all loggers and their levels.
     * GET /actuator/loggers
     */
    public LoggersResponse getAll() {
        return restClient.get()
                .uri(basePath + "/loggers")
                .retrieve()
                .body(LoggersResponse.class);
    }

    /**
     * Get a specific logger by name.
     * GET /actuator/loggers/{name}
     */
    public LoggerResponse get(String name) {
        return restClient.get()
                .uri(basePath + "/loggers/{name}", name)
                .retrieve()
                .body(LoggerResponse.class);
    }

    /**
     * Set the level of a logger.
     * Returns a request builder to specify the level.
     * POST /actuator/loggers/{name}
     */
    public SetLoggerLevelRequest setLevel(String name) {
        return new SetLoggerLevelRequest(restClient, basePath, name);
    }
}
