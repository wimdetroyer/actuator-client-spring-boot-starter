package com.wimdetroyer.actuator.endpoints.loggers;

import java.util.List;

/**
 * Response from the loggers/{name} endpoint.
 */
public record LoggerResponse(
        LogLevel configuredLevel,
        LogLevel effectiveLevel,
        List<String> members
) {}
