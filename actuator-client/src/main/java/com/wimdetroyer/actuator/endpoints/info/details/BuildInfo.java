package com.wimdetroyer.actuator.endpoints.info.details;

import java.time.Instant;

/**
 * Typed build information from the info endpoint.
 *
 * @param artifact the artifactId of the project
 * @param name     the name of the project
 * @param version  the version of the project
 * @param group    the groupId of the project
 * @param time     the timestamp of the build
 */
public record BuildInfo(
        String artifact,
        String name,
        String version,
        String group,
        Instant time
) {}
