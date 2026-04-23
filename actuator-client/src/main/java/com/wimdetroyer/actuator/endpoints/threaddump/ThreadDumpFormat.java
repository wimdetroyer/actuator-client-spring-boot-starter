package com.wimdetroyer.actuator.endpoints.threaddump;

/**
 * Enum representing thread dump output formats.
 */
public enum ThreadDumpFormat {
    JSON("application/json"),
    TEXT("text/plain");

    private final String mediaType;

    ThreadDumpFormat(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}
