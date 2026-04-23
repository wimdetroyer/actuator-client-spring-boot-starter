package com.wimdetroyer.actuator.endpoints.metrics;

import java.util.List;

/**
 * Response from the metrics/{name} endpoint.
 */
public record MetricResponse(
        String name,
        String description,
        String baseUnit,
        List<Measurement> measurements,
        List<Tag> availableTags
) {
    public record Measurement(
            String statistic,
            double value
    ) {}

    public record Tag(
            String tag,
            List<String> values
    ) {}
}
