package com.wimdetroyer.actuator.endpoints.httpexchanges;

import java.time.Instant;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Response from the httpexchanges endpoint.
 */
public record HttpExchangesResponse(
        List<Exchange> exchanges
) {
    public record Exchange(
            Instant timestamp,
            Request request,
            Response response,
            Principal principal,
            String sessionId,
            Duration timeTaken
    ) {}

    public record Request(
            String uri,
            String method,
            Map<String, List<String>> headers,
            String remoteAddress
    ) {}

    public record Response(
            int status,
            Map<String, List<String>> headers
    ) {}

    public record Principal(
            String name
    ) {}
}
