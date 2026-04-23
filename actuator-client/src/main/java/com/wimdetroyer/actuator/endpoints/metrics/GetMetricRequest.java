package com.wimdetroyer.actuator.endpoints.metrics;

import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Request builder for getting metrics with optional tag filtering.
 */
public final class GetMetricRequest {

    private final RestClient restClient;
    private final String basePath;
    private final String metricName;
    private final List<String> tags;

    public GetMetricRequest(RestClient restClient, String basePath, String metricName) {
        this.restClient = restClient;
        this.basePath = basePath;
        this.metricName = metricName;
        this.tags = new ArrayList<>();
    }

    private GetMetricRequest(RestClient restClient, String basePath, String metricName, List<String> tags) {
        this.restClient = restClient;
        this.basePath = basePath;
        this.metricName = metricName;
        this.tags = new ArrayList<>(tags);
    }

    /**
     * Filter by a tag.
     */
    public GetMetricRequest tag(String key, String value) {
        GetMetricRequest request = new GetMetricRequest(restClient, basePath, metricName, tags);
        request.tags.add(key + ":" + value);
        return request;
    }

    /**
     * Execute the request to get the metric.
     * GET /actuator/metrics/{name}?tag=key:value
     */
    public MetricResponse execute() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(basePath + "/metrics/{name}");

        for (String tag : tags) {
            builder.queryParam("tag", tag);
        }

        String uri = builder.buildAndExpand(metricName).toUriString();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(MetricResponse.class);
    }
}
