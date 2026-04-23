package com.wimdetroyer.actuator.endpoints.health.details;

/**
 * Typed details for the diskSpace health indicator.
 *
 * @param total     total disk space in bytes
 * @param free      free (usable) disk space in bytes
 * @param threshold minimum disk space that should be available (in bytes)
 * @param path      the absolute path being monitored
 * @param exists    whether the path exists
 */
public record DiskSpaceDetails(
        Long total,
        Long free,
        Long threshold,
        String path,
        Boolean exists
) implements HealthDetails {}
