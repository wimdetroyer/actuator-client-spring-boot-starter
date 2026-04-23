package com.wimdetroyer.actuator.endpoints.loggers;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Request builder for setting logger levels.
 */
public final class SetLoggerLevelRequest {

    private final RestClient restClient;
    private final String basePath;
    private final String loggerName;
    private LogLevel level;

    public SetLoggerLevelRequest(RestClient restClient, String basePath, String loggerName) {
        this.restClient = restClient;
        this.basePath = basePath;
        this.loggerName = loggerName;
    }

    /**
     * Set the log level.
     */
    public SetLoggerLevelRequest level(LogLevel level) {
        SetLoggerLevelRequest request = new SetLoggerLevelRequest(restClient, basePath, loggerName);
        request.level = level;
        return request;
    }

    /**
     * Execute the request to set the logger level.
     * POST /actuator/loggers/{name}
     */
    public void execute() {
        Map<String, String> body = level != null
                ? Map.of("configuredLevel", level.getValue())
                : Map.of();

        restClient.post()
                .uri(basePath + "/loggers/{name}", loggerName)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }

    /**
     * Clear the configured level (reset to effective level from parent).
     * POST /actuator/loggers/{name}
     */
    public void clear() {
        Map<String, Object> body = new HashMap<>();
        body.put("configuredLevel", null);

        restClient.post()
                .uri(basePath + "/loggers/{name}", loggerName)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toBodilessEntity();
    }
}
