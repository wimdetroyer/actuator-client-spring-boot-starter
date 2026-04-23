package com.wimdetroyer.actuator.endpoints.sbom;

import java.util.List;
import java.util.Map;

/**
 * Response from the sbom/{id} endpoint (CycloneDX format).
 */
public record SbomDetailResponse(
        String bomFormat,
        String specVersion,
        String serialNumber,
        int version,
        Metadata metadata,
        List<Component> components,
        List<Dependency> dependencies
) {
    public record Metadata(
            String timestamp,
            List<Tool> tools,
            Component component
    ) {}

    public record Tool(
            String vendor,
            String name,
            String version
    ) {}

    public record Component(
            String type,
            String bomRef,
            String group,
            String name,
            String version,
            String description,
            String scope,
            List<Hash> hashes,
            List<License> licenses,
            String purl,
            List<ExternalReference> externalReferences,
            Map<String, Object> properties
    ) {}

    public record Hash(
            String alg,
            String content
    ) {}

    public record License(
            LicenseInfo license
    ) {
        public record LicenseInfo(
                String id,
                String name,
                String url
        ) {}
    }

    public record ExternalReference(
            String type,
            String url
    ) {}

    public record Dependency(
            String ref,
            List<String> dependsOn
    ) {}
}
