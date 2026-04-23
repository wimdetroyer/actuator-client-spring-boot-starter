package com.wimdetroyer.actuator.endpoints.prometheus;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Request builder for getting Prometheus metrics with filtering options.
 */
public final class GetPrometheusRequest {

    private final RestClient restClient;
    private final String basePath;
    private final List<String> includedNames;
    private PrometheusFormat format;

    public GetPrometheusRequest(RestClient restClient, String basePath) {
        this.restClient = restClient;
        this.basePath = basePath;
        this.includedNames = new ArrayList<>();
        this.format = PrometheusFormat.PLAIN_TEXT;
    }

    private GetPrometheusRequest(RestClient restClient, String basePath, List<String> includedNames, PrometheusFormat format) {
        this.restClient = restClient;
        this.basePath = basePath;
        this.includedNames = new ArrayList<>(includedNames);
        this.format = format;
    }

    /**
     * Include a metric name in the output.
     */
    public GetPrometheusRequest includeName(String name) {
        GetPrometheusRequest request = new GetPrometheusRequest(restClient, basePath, includedNames, format);
        request.includedNames.add(name);
        return request;
    }

    /**
     * Include multiple metric names in the output.
     */
    public GetPrometheusRequest includeNames(String... names) {
        GetPrometheusRequest request = new GetPrometheusRequest(restClient, basePath, includedNames, format);
        request.includedNames.addAll(List.of(names));
        return request;
    }

    /**
     * Set the output format.
     */
    public GetPrometheusRequest format(PrometheusFormat format) {
        GetPrometheusRequest request = new GetPrometheusRequest(restClient, basePath, includedNames, format);
        request.format = format;
        return request;
    }

    /**
     * Execute the request to get filtered Prometheus metrics.
     * GET /actuator/prometheus?includedNames=name1,name2
     */
    public String execute() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(basePath + "/prometheus");

        if (!includedNames.isEmpty()) {
            builder.queryParam("includedNames", String.join(",", includedNames));
        }

        String uri = builder.build().toUriString();

        return restClient.get()
                .uri(uri)
                .accept(MediaType.parseMediaType(format.getMediaType()))
                .retrieve()
                .body(String.class);
    }
}
