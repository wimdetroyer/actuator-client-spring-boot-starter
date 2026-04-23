package com.wimdetroyer.actuator.endpoints.sessions;

import java.time.Instant;
import java.util.List;

/**
 * Response from the sessions/{id} endpoint.
 */
public record SessionResponse(
        String id,
        List<String> attributeNames,
        Instant creationTime,
        Instant lastAccessedTime,
        long maxInactiveInterval,
        boolean expired
) {}
