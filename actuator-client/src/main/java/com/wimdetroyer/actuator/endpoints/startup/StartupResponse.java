package com.wimdetroyer.actuator.endpoints.startup;

import java.time.Instant;
import java.util.List;

/**
 * Response from the startup endpoint.
 */
public record StartupResponse(
        String springBootVersion,
        Timeline timeline
) {
    public record Timeline(
            Instant startTime,
            List<Event> events
    ) {}

    public record Event(
            Instant startTime,
            Instant endTime,
            long duration,
            StartupStep startupStep
    ) {}

    public record StartupStep(
            String name,
            String id,
            String parentId,
            List<Tag> tags
    ) {}

    public record Tag(
            String key,
            String value
    ) {}
}
