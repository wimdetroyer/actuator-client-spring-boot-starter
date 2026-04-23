package com.wimdetroyer.actuator.endpoints.sessions;

import java.time.Instant;
import java.util.List;

/**
 * Response from the sessions endpoint.
 */
public record SessionsResponse(
        List<Session> sessions
) {
    public record Session(
            String id,
            List<String> attributeNames,
            Instant creationTime,
            Instant lastAccessedTime,
            long maxInactiveInterval,
            boolean expired
    ) {}
}
