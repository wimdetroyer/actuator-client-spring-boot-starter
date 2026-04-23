package com.wimdetroyer.actuator.endpoints.health;

import com.wimdetroyer.actuator.endpoints.health.details.HealthDetails;

import java.util.Map;

/**
 * Response from the health endpoint.
 */
public record HealthResponse(
        HealthStatus status,
        Map<String, HealthComponent> components,
        HealthDetails details
) {
    public record HealthComponent(
            HealthStatus status,
            Map<String, HealthComponent> components,
            HealthDetails details
    ) {}
}
