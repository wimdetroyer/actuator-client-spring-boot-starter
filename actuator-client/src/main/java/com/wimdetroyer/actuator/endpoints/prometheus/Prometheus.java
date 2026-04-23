package com.wimdetroyer.actuator.endpoints.prometheus;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Fluent API for the prometheus endpoint.
 */
public record Prometheus(RestClient restClient, String basePath) {

    /**
     * Get Prometheus metrics in plain text format.
     * GET /actuator/prometheus
     */
    public String get() {
        return get(PrometheusFormat.PLAIN_TEXT);
    }

    /**
     * Get Prometheus metrics in the specified format.
     * GET /actuator/prometheus
     */
    public String get(PrometheusFormat format) {
        return restClient.get()
                .uri(basePath + "/prometheus")
                .accept(MediaType.parseMediaType(format.getMediaType()))
                .retrieve()
                .body(String.class);
    }

    /**
     * Get Prometheus metrics with filtering options.
     * Returns a request builder to specify included metric names.
     */
    public GetPrometheusRequest getWithFilter() {
        return new GetPrometheusRequest(restClient, basePath);
    }
}
