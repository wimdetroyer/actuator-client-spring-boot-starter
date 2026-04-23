package com.wimdetroyer.actuator.endpoints.sbom;

import java.util.List;

/**
 * Response from the sbom endpoint (listing available SBOMs).
 */
public record SbomResponse(
        List<String> ids
) {}
