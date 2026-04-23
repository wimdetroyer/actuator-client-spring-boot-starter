package com.wimdetroyer.actuator.endpoints.beans;

import java.util.List;
import java.util.Map;

/**
 * Response from the beans endpoint.
 */
public record BeansResponse(
        Map<String, Context> contexts
) {
    public record Context(
            Map<String, BeanInfo> beans,
            String parentId
    ) {}

    public record BeanInfo(
            List<String> aliases,
            String scope,
            String type,
            String resource,
            List<String> dependencies
    ) {}
}
