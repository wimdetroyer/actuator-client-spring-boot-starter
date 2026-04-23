package com.wimdetroyer.actuator.endpoints.sessions;

import org.springframework.web.client.RestClient;

/**
 * Fluent API for the sessions endpoint.
 */
public record Sessions(RestClient restClient, String basePath) {

    /**
     * Find sessions by username.
     * GET /actuator/sessions?username={username}
     */
    public SessionsResponse findByUsername(String username) {
        return restClient.get()
                .uri(basePath + "/sessions?username={username}", username)
                .retrieve()
                .body(SessionsResponse.class);
    }

    /**
     * Get a specific session by ID.
     * GET /actuator/sessions/{id}
     */
    public SessionResponse get(String sessionId) {
        return restClient.get()
                .uri(basePath + "/sessions/{id}", sessionId)
                .retrieve()
                .body(SessionResponse.class);
    }

    /**
     * Delete a session by ID.
     * DELETE /actuator/sessions/{id}
     */
    public void delete(String sessionId) {
        restClient.delete()
                .uri(basePath + "/sessions/{id}", sessionId)
                .retrieve()
                .toBodilessEntity();
    }
}
