package com.wimdetroyer.actuator.endpoints.mappings;

import java.util.List;
import java.util.Map;

/**
 * Response from the mappings endpoint.
 */
public record MappingsResponse(
        Map<String, Context> contexts
) {
    public record Context(
            Mappings mappings,
            String parentId
    ) {}

    public record Mappings(
            Map<String, List<DispatcherMapping>> dispatcherServlets,
            List<ServletFilter> servletFilters,
            List<Servlet> servlets
    ) {}

    public record DispatcherMapping(
            String handler,
            String predicate,
            RequestMappingConditions details
    ) {}

    public record RequestMappingConditions(
            List<MediaTypeCondition> consumes,
            List<String> headers,
            List<String> methods,
            List<String> params,
            List<String> patterns,
            List<MediaTypeCondition> produces,
            RequestMethodsRequestCondition requestMethods
    ) {}

    public record MediaTypeCondition(
            String mediaType,
            boolean negated
    ) {}

    public record RequestMethodsRequestCondition(
            List<String> methods
    ) {}

    public record ServletFilter(
            List<String> servletNameMappings,
            List<String> urlPatternMappings,
            String name,
            String className
    ) {}

    public record Servlet(
            List<String> mappings,
            String name,
            String className
    ) {}
}
