package com.wimdetroyer.actuator.endpoints.threaddump;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing thread states.
 */
public enum ThreadState {
    NEW("NEW"),
    RUNNABLE("RUNNABLE"),
    BLOCKED("BLOCKED"),
    WAITING("WAITING"),
    TIMED_WAITING("TIMED_WAITING"),
    TERMINATED("TERMINATED");

    private final String value;

    ThreadState(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ThreadState fromValue(String value) {
        for (ThreadState state : values()) {
            if (state.value.equalsIgnoreCase(value)) {
                return state;
            }
        }
        return null;
    }
}
