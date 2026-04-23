package com.wimdetroyer.actuator.endpoints.integrationgraph;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the integrationgraph endpoint.
 */
public record IntegrationGraph(RestClient restClient, String basePath) {

    /**
     * Get the Spring Integration graph.
     * GET /actuator/integrationgraph
     */
    public IntegrationGraphResponse get() {
        return restClient.get()
                .uri(basePath + "/integrationgraph")
                .retrieve()
                .body(IntegrationGraphResponse.class);
    }

    /**
     * Rebuild the Spring Integration graph.
     * POST /actuator/integrationgraph
     */
    public void rebuild() {
        restClient.post()
                .uri(basePath + "/integrationgraph")
                .retrieve()
                .toBodilessEntity();
    }
}
