package com.wimdetroyer.actuator.endpoints.caches;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the caches endpoint.
 */
public record Caches(RestClient restClient, String basePath) {

    /**
     * Get all caches.
     * GET /actuator/caches
     */
    public CachesResponse getAll() {
        return restClient.get()
                .uri(basePath + "/caches")
                .retrieve()
                .body(CachesResponse.class);
    }

    /**
     * Get a specific cache by name.
     * GET /actuator/caches/{name}
     */
    public CacheResponse get(String name) {
        return restClient.get()
                .uri(basePath + "/caches/{name}", name)
                .retrieve()
                .body(CacheResponse.class);
    }

    /**
     * Clear all caches.
     * DELETE /actuator/caches
     */
    public void clearAll() {
        restClient.delete()
                .uri(basePath + "/caches")
                .retrieve()
                .toBodilessEntity();
    }

    /**
     * Clear a specific cache by name.
     * DELETE /actuator/caches/{name}
     */
    public void clear(String name) {
        restClient.delete()
                .uri(basePath + "/caches/{name}", name)
                .retrieve()
                .toBodilessEntity();
    }
}
