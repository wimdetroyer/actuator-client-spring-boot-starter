package com.wimdetroyer.actuator.endpoints.loggers;

import java.util.List;
import java.util.Map;

/**
 * Response from the loggers endpoint.
 */
public record LoggersResponse(
        List<LogLevel> levels,
        Map<String, LoggerInfo> loggers,
        Map<String, GroupInfo> groups
) {
    public record LoggerInfo(
            LogLevel configuredLevel,
            LogLevel effectiveLevel
    ) {}

    public record GroupInfo(
            LogLevel configuredLevel,
            List<String> members
    ) {}
}
