package com.wimdetroyer.actuator.endpoints.prometheus;

/**
 * Enum representing Prometheus metrics output formats.
 */
public enum PrometheusFormat {
    PLAIN_TEXT("text/plain"),
    OPENMETRICS("application/openmetrics-text");

    private final String mediaType;

    PrometheusFormat(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }
}
