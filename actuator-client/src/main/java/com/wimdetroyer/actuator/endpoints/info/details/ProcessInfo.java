package com.wimdetroyer.actuator.endpoints.info.details;

/**
 * Typed process information from the info endpoint.
 *
 * @param pid       the process ID
 * @param parentPid the parent process ID, or -1 if unavailable
 * @param owner     the process owner (user), or {@code null} if unavailable
 */
public record ProcessInfo(
        Long pid,
        Long parentPid,
        String owner
) {}
