package com.wimdetroyer.actuator.endpoints.beans;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the beans endpoint.
 */
public record Beans(RestClient restClient, String basePath) {

    /**
     * Get all beans in the application context.
     * GET /actuator/beans
     */
    public BeansResponse get() {
        return restClient.get()
                .uri(basePath + "/beans")
                .retrieve()
                .body(BeansResponse.class);
    }
}
