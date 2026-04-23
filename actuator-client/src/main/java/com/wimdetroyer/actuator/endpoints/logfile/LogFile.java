package com.wimdetroyer.actuator.endpoints.logfile;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the logfile endpoint.
 */
public record LogFile(RestClient restClient, String basePath) {

    /**
     * Get the entire log file contents.
     * GET /actuator/logfile
     */
    public String get() {
        return restClient.get()
                .uri(basePath + "/logfile")
                .retrieve()
                .body(String.class);
    }

    /**
     * Get a portion of the log file using range requests.
     * Returns a request builder to specify the range.
     */
    public GetLogFileRequest getRange() {
        return new GetLogFileRequest(restClient, basePath);
    }
}
