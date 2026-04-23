package com.wimdetroyer.actuator.endpoints.configprops;

import java.util.Map;

/**
 * Response from the configprops endpoint.
 */
public record ConfigPropsResponse(
        Map<String, Context> contexts
) {
    public record Context(
            Map<String, ConfigurationPropertiesBean> beans,
            String parentId
    ) {}

    public record ConfigurationPropertiesBean(
            String prefix,
            Map<String, Object> properties,
            Map<String, Object> inputs
    ) {
        /**
         * Returns a navigator for traversing nested properties.
         *
         * @return the property navigator
         */
        public ConfigPropertyNavigator navigator() {
            return new ConfigPropertyNavigator(properties);
        }

        /**
         * Returns a navigator for traversing nested inputs (original values before binding).
         *
         * @return the inputs navigator
         */
        public ConfigPropertyNavigator inputsNavigator() {
            return new ConfigPropertyNavigator(inputs);
        }
    }
}
