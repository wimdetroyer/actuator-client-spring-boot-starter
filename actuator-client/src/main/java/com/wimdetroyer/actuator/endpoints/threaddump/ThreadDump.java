package com.wimdetroyer.actuator.endpoints.threaddump;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

/**
 * Fluent API for the threaddump endpoint.
 */
public record ThreadDump(RestClient restClient, String basePath) {

    /**
     * Get a thread dump in JSON format.
     * GET /actuator/threaddump
     */
    public ThreadDumpResponse get() {
        return restClient.get()
                .uri(basePath + "/threaddump")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ThreadDumpResponse.class);
    }

    /**
     * Get a thread dump in the specified format.
     * GET /actuator/threaddump
     */
    public String get(ThreadDumpFormat format) {
        return restClient.get()
                .uri(basePath + "/threaddump")
                .accept(MediaType.parseMediaType(format.getMediaType()))
                .retrieve()
                .body(String.class);
    }

    /**
     * Get a thread dump as plain text.
     * GET /actuator/threaddump
     */
    public String getAsText() {
        return get(ThreadDumpFormat.TEXT);
    }
}
