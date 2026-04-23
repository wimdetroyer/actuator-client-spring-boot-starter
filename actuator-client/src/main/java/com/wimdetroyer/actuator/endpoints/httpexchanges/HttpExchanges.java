package com.wimdetroyer.actuator.endpoints.httpexchanges;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the httpexchanges endpoint.
 */
public record HttpExchanges(RestClient restClient, String basePath) {

    /**
     * Get recent HTTP exchanges.
     * GET /actuator/httpexchanges
     */
    public HttpExchangesResponse get() {
        return restClient.get()
                .uri(basePath + "/httpexchanges")
                .retrieve()
                .body(HttpExchangesResponse.class);
    }
}
