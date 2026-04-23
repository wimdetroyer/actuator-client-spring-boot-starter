package com.wimdetroyer.actuator.endpoints.caches;

import java.util.Map;

/**
 * Response from the caches endpoint.
 */
public record CachesResponse(
        Map<String, CacheManagerInfo> cacheManagers
) {
    public record CacheManagerInfo(
            Map<String, CacheInfo> caches
    ) {}

    public record CacheInfo(
            String target
    ) {}
}
