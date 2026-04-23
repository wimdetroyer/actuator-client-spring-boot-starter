package com.wimdetroyer.actuator.endpoints.quartz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing Quartz job states.
 */
public enum QuartzJobState {
    NONE("NONE"),
    NORMAL("NORMAL"),
    PAUSED("PAUSED"),
    COMPLETE("COMPLETE"),
    ERROR("ERROR"),
    BLOCKED("BLOCKED");

    private final String value;

    QuartzJobState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static QuartzJobState fromValue(String value) {
        for (QuartzJobState state : values()) {
            if (state.value.equalsIgnoreCase(value)) {
                return state;
            }
        }
        return NONE;
    }
}
