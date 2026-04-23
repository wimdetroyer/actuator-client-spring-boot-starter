package com.wimdetroyer.actuator.endpoints.caches;

/**
 * Response from the caches/{name} endpoint.
 */
public record CacheResponse(
        String target,
        String name,
        String cacheManager
) {}
