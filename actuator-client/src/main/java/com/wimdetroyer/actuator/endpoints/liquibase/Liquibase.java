package com.wimdetroyer.actuator.endpoints.liquibase;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the liquibase endpoint.
 */
public record Liquibase(RestClient restClient, String basePath) {

    /**
     * Get Liquibase database migration information.
     * GET /actuator/liquibase
     */
    public LiquibaseResponse get() {
        return restClient.get()
                .uri(basePath + "/liquibase")
                .retrieve()
                .body(LiquibaseResponse.class);
    }
}
