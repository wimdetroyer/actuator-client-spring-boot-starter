package com.wimdetroyer.actuator.endpoints.health;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the health status of an application.
 */
public enum HealthStatus {
    UP("UP"),
    DOWN("DOWN"),
    OUT_OF_SERVICE("OUT_OF_SERVICE"),
    UNKNOWN("UNKNOWN");

    private final String value;

    HealthStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static HealthStatus fromValue(String value) {
        for (HealthStatus status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return UNKNOWN;
    }
}
