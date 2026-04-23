package com.wimdetroyer.actuator.endpoints.metrics;

import java.util.List;

/**
 * Response from the metrics endpoint (listing available metrics).
 */
public record MetricsResponse(
        List<String> names
) {}
