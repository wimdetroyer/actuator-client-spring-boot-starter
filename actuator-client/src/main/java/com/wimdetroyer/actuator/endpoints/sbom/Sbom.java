package com.wimdetroyer.actuator.endpoints.sbom;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the sbom endpoint.
 */
public record Sbom(RestClient restClient, String basePath) {

    /**
     * Get available SBOM IDs.
     * GET /actuator/sbom
     */
    public SbomResponse getAll() {
        return restClient.get()
                .uri(basePath + "/sbom")
                .retrieve()
                .body(SbomResponse.class);
    }

    /**
     * Get a specific SBOM by ID.
     * GET /actuator/sbom/{id}
     */
    public SbomDetailResponse get(String id) {
        return restClient.get()
                .uri(basePath + "/sbom/{id}", id)
                .retrieve()
                .body(SbomDetailResponse.class);
    }

    /**
     * Get a specific SBOM by ID as raw string (useful for non-CycloneDX formats).
     * GET /actuator/sbom/{id}
     */
    public String getRaw(String id) {
        return restClient.get()
                .uri(basePath + "/sbom/{id}", id)
                .retrieve()
                .body(String.class);
    }
}
