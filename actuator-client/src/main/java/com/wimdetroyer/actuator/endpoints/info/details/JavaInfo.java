package com.wimdetroyer.actuator.endpoints.info.details;

/**
 * Typed Java runtime information from the info endpoint.
 *
 * @param version the Java version (from {@code java.version} system property)
 * @param vendor  the Java vendor information
 * @param runtime the Java Runtime Environment information
 * @param jvm     the Java Virtual Machine information
 */
public record JavaInfo(
        String version,
        Vendor vendor,
        Runtime runtime,
        Jvm jvm
) {
    /**
     * Java vendor information.
     *
     * @param name    the vendor name (from {@code java.vendor} system property)
     * @param version the vendor version (from {@code java.vendor.version} system property)
     */
    public record Vendor(
            String name,
            String version
    ) {}

    /**
     * Java Runtime Environment information.
     *
     * @param name    the runtime name (from {@code java.runtime.name} system property)
     * @param version the runtime version (from {@code java.runtime.version} system property)
     */
    public record Runtime(
            String name,
            String version
    ) {}

    /**
     * Java Virtual Machine information.
     *
     * @param name    the JVM name (from {@code java.vm.name} system property)
     * @param vendor  the JVM vendor (from {@code java.vm.vendor} system property)
     * @param version the JVM version (from {@code java.vm.version} system property)
     */
    public record Jvm(
            String name,
            String vendor,
            String version
    ) {}
}
