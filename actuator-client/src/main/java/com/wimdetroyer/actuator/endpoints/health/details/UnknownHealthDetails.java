package com.wimdetroyer.actuator.endpoints.health.details;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Fallback for health indicator details that don't match any known type.
 * Captures all properties in a map for inspection.
 */
public final class UnknownHealthDetails implements HealthDetails {
    private final Map<String, Object> properties = new HashMap<>();

    @JsonAnySetter
    public void set(String key, Object value) {
        properties.put(key, value);
    }

    public Map<String, Object> properties() {
        return properties;
    }
}
