package com.wimdetroyer.actuator.endpoints.metrics;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the metrics endpoint.
 */
public record Metrics(RestClient restClient, String basePath) {

    /**
     * Get all available metric names.
     * GET /actuator/metrics
     */
    public MetricsResponse getAll() {
        return restClient.get()
                .uri(basePath + "/metrics")
                .retrieve()
                .body(MetricsResponse.class);
    }

    /**
     * Get a specific metric by name.
     * Returns a request builder to optionally specify tags.
     * GET /actuator/metrics/{name}
     */
    public GetMetricRequest get(String name) {
        return new GetMetricRequest(restClient, basePath, name);
    }
}
