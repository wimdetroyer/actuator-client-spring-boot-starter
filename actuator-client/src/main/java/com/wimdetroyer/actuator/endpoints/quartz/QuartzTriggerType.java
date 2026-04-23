package com.wimdetroyer.actuator.endpoints.quartz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing Quartz trigger types.
 */
public enum QuartzTriggerType {
    CRON("cron"),
    SIMPLE("simple"),
    DAILY_TIME_INTERVAL("dailyTimeInterval"),
    CALENDAR_INTERVAL("calendarInterval"),
    CUSTOM("custom");

    private final String value;

    QuartzTriggerType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static QuartzTriggerType fromValue(String value) {
        for (QuartzTriggerType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return CUSTOM;
    }
}
