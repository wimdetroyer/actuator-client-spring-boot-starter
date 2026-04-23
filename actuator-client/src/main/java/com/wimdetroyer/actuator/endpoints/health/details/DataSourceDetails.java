package com.wimdetroyer.actuator.endpoints.health.details;

/**
 * Typed details for the db/dataSource health indicator.
 *
 * @param database        the database product name (e.g., "H2", "PostgreSQL")
 * @param validationQuery the validation query used, or "isValid()" if using connection validation
 * @param result          the result of executing the validation query, or {@code null} if using isValid()
 */
public record DataSourceDetails(
        String database,
        String validationQuery,
        Object result
) implements HealthDetails {}
