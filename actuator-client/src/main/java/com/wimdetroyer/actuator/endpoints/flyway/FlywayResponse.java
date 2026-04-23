package com.wimdetroyer.actuator.endpoints.flyway;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Response from the flyway endpoint.
 */
public record FlywayResponse(
        Map<String, Context> contexts
) {
    public record Context(
            Map<String, FlywayBean> flywayBeans,
            String parentId
    ) {}

    public record FlywayBean(
            List<Migration> migrations
    ) {}

    public record Migration(
            String type,
            Integer checksum,
            String version,
            String description,
            String script,
            String state,
            String installedBy,
            Instant installedOn,
            Integer installedRank,
            Integer executionTime
    ) {}
}
