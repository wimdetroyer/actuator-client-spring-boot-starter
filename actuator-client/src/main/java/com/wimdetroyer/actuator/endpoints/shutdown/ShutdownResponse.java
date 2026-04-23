package com.wimdetroyer.actuator.endpoints.shutdown;

/**
 * Response from the shutdown endpoint.
 */
public record ShutdownResponse(
        String message
) {}
