package com.wimdetroyer.actuator.endpoints.liquibase;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Response from the liquibase endpoint.
 */
public record LiquibaseResponse(
        Map<String, Context> contexts
) {
    public record Context(
            Map<String, LiquibaseBean> liquibaseBeans,
            String parentId
    ) {}

    public record LiquibaseBean(
            List<ChangeSet> changeSets
    ) {}

    public record ChangeSet(
            String id,
            String author,
            String changeLog,
            Instant dateExecuted,
            String orderExecuted,
            String execType,
            String checksum,
            String description,
            String comments,
            String tag,
            List<String> contexts,
            List<String> labels,
            String deploymentId
    ) {}
}
