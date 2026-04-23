package com.wimdetroyer.actuator.endpoints.integrationgraph;

import java.util.List;
import java.util.Map;

/**
 * Response from the integrationgraph endpoint.
 */
public record IntegrationGraphResponse(
        Map<String, Object> contentDescriptor,
        List<Node> nodes,
        List<Link> links
) {
    public record Node(
            int nodeId,
            String componentType,
            String integrationPatternType,
            String integrationPatternCategory,
            Map<String, Object> properties,
            String name,
            Object input,
            Object output,
            List<String> errors,
            Object discards,
            List<Object> routes,
            Object sendTimers,
            Object receiveCounters
    ) {}

    public record Link(
            int from,
            int to,
            String type
    ) {}
}
