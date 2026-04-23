package com.wimdetroyer.actuator.endpoints.env;

import java.util.List;
import java.util.Map;

/**
 * Response from the env endpoint.
 */
public record EnvResponse(
        List<String> activeProfiles,
        List<String> defaultProfiles,
        List<PropertySource> propertySources
) {
    public record PropertySource(
            String name,
            Map<String, PropertyValue> properties
    ) {}

    public record PropertyValue(
            Object value,
            String origin
    ) {
        /**
         * Returns a type-safe wrapper for this property value.
         *
         * @return the typed property value wrapper
         */
        public TypedPropertyValue typed() {
            return new TypedPropertyValue(value, origin);
        }
    }
}
