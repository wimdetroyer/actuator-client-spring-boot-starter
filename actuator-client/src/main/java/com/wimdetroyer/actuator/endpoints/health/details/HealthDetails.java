package com.wimdetroyer.actuator.endpoints.health.details;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Sealed interface for typed health indicator details.
 * <p>
 * Uses Jackson DEDUCTION to infer the concrete type from field presence:
 * <ul>
 *   <li>{@link DiskSpaceDetails} - has total, free, threshold, path, exists</li>
 *   <li>{@link DataSourceDetails} - has database, validationQuery</li>
 *   <li>{@link UnknownHealthDetails} - fallback for unrecognized details</li>
 * </ul>
 *
 * @see DiskSpaceDetails
 * @see DataSourceDetails
 * @see UnknownHealthDetails
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = UnknownHealthDetails.class)
@JsonSubTypes({
        @JsonSubTypes.Type(DiskSpaceDetails.class),
        @JsonSubTypes.Type(DataSourceDetails.class)
})
public sealed interface HealthDetails
        permits DiskSpaceDetails, DataSourceDetails, UnknownHealthDetails {
}
