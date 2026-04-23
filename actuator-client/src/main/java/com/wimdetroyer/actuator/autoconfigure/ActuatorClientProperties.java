package com.wimdetroyer.actuator.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Configuration properties for the Actuator Client.
 *
 * @param baseUrl        the base URL of the target application (e.g., "http://localhost:8080")
 * @param actuatorPath   the actuator base path (default "/actuator")
 * @param connectTimeout the connection timeout (default 5 seconds)
 * @param readTimeout    the read timeout (default 10 seconds)
 */
@ConfigurationProperties(prefix = "actuator.client")
public record ActuatorClientProperties(
        String baseUrl,
        String actuatorPath,
        Duration connectTimeout,
        Duration readTimeout
) {
    private static final String DEFAULT_ACTUATOR_PATH = "/actuator";
    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration DEFAULT_READ_TIMEOUT = Duration.ofSeconds(10);

    public ActuatorClientProperties {
        if (actuatorPath == null) {
            actuatorPath = DEFAULT_ACTUATOR_PATH;
        }
        if (connectTimeout == null) {
            connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        }
        if (readTimeout == null) {
            readTimeout = DEFAULT_READ_TIMEOUT;
        }
    }


}
