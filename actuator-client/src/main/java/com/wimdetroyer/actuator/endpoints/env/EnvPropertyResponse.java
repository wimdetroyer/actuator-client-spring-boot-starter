package com.wimdetroyer.actuator.endpoints.env;

import java.util.List;

/**
 * Response from the env/{property} endpoint.
 */
public record EnvPropertyResponse(
        Property property,
        List<String> activeProfiles,
        List<PropertySourceValue> propertySources
) {
    public record Property(
            String source,
            Object value
    ) {
        /**
         * Returns a type-safe wrapper for this property value.
         *
         * @return the typed property value wrapper
         */
        public TypedPropertyValue typed() {
            return new TypedPropertyValue(value, null);
        }
    }

    public record PropertySourceValue(
            String name,
            PropertyValue property
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
