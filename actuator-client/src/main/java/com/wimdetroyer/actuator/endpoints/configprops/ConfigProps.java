package com.wimdetroyer.actuator.endpoints.configprops;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the configprops endpoint.
 */
public record ConfigProps(RestClient restClient, String basePath) {

    /**
     * Get all configuration properties.
     * GET /actuator/configprops
     */
    public ConfigPropsResponse getAll() {
        return restClient.get()
                .uri(basePath + "/configprops")
                .retrieve()
                .body(ConfigPropsResponse.class);
    }

    /**
     * Get configuration properties by prefix.
     * GET /actuator/configprops/{prefix}
     */
    public ConfigPropsResponse get(String prefix) {
        return restClient.get()
                .uri(basePath + "/configprops/{prefix}", prefix)
                .retrieve()
                .body(ConfigPropsResponse.class);
    }
}
