package com.wimdetroyer.actuator.endpoints.info.details;

/**
 * Typed operating system information from the info endpoint.
 *
 * @param name    the OS name (from {@code os.name} system property)
 * @param version the OS version (from {@code os.version} system property)
 * @param arch    the OS architecture (from {@code os.arch} system property)
 */
public record OsInfo(
        String name,
        String version,
        String arch
) {}
