package com.wimdetroyer.actuator.endpoints.info;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the info endpoint.
 */
public record Info(RestClient restClient, String basePath) {

    /**
     * Get application information.
     * GET /actuator/info
     */
    public InfoResponse get() {
        return restClient.get()
                .uri(basePath + "/info")
                .retrieve()
                .body(InfoResponse.class);
    }
}
