package com.wimdetroyer.actuator.endpoints.configprops;

import java.util.Map;
import java.util.Optional;

/**
 * Navigator utility for traversing nested configuration properties.
 */
public final class ConfigPropertyNavigator {

    private final Map<String, Object> properties;

    public ConfigPropertyNavigator(Map<String, Object> properties) {
        this.properties = properties != null ? properties : Map.of();
    }

    /**
     * Gets a nested value by dot-separated path.
     *
     * @param path the dot-separated path (e.g., "ssl.enabled")
     * @return the value at the path, or empty if not found
     */
    public Optional<Object> get(String path) {
        if (path == null || path.isEmpty()) {
            return Optional.empty();
        }

        String[] parts = path.split("\\.");
        Object current = properties;

        for (String part : parts) {
            if (current instanceof Map<?, ?> map) {
                current = map.get(part);
                if (current == null) {
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }
        }

        return Optional.of(current);
    }

    /**
     * Gets a String value by dot-separated path.
     *
     * @param path the dot-separated path
     * @return the String value at the path, or empty if not found or not a String
     */
    public Optional<String> getString(String path) {
        return get(path).flatMap(v -> {
            if (v instanceof String s) {
                return Optional.of(s);
            }
            if (v != null) {
                return Optional.of(String.valueOf(v));
            }
            return Optional.empty();
        });
    }

    /**
     * Gets an Integer value by dot-separated path.
     *
     * @param path the dot-separated path
     * @return the Integer value at the path, or empty if not found or cannot be parsed
     */
    public Optional<Integer> getInteger(String path) {
        return get(path).flatMap(v -> {
            if (v instanceof Number n) {
                return Optional.of(n.intValue());
            }
            if (v instanceof String s) {
                try {
                    return Optional.of(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    return Optional.empty();
                }
            }
            return Optional.empty();
        });
    }

    /**
     * Gets a Long value by dot-separated path.
     *
     * @param path the dot-separated path
     * @return the Long value at the path, or empty if not found or cannot be parsed
     */
    public Optional<Long> getLong(String path) {
        return get(path).flatMap(v -> {
            if (v instanceof Number n) {
                return Optional.of(n.longValue());
            }
            if (v instanceof String s) {
                try {
                    return Optional.of(Long.parseLong(s));
                } catch (NumberFormatException e) {
                    return Optional.empty();
                }
            }
            return Optional.empty();
        });
    }

    /**
     * Gets a Boolean value by dot-separated path.
     *
     * @param path the dot-separated path
     * @return the Boolean value at the path, or empty if not found or not a boolean
     */
    public Optional<Boolean> getBoolean(String path) {
        return get(path).flatMap(v -> {
            if (v instanceof Boolean b) {
                return Optional.of(b);
            }
            if (v instanceof String s) {
                if ("true".equalsIgnoreCase(s)) {
                    return Optional.of(true);
                }
                if ("false".equalsIgnoreCase(s)) {
                    return Optional.of(false);
                }
            }
            return Optional.empty();
        });
    }

    /**
     * Gets a nested Map by dot-separated path.
     *
     * @param path the dot-separated path
     * @return the Map value at the path, or empty if not found or not a Map
     */
    @SuppressWarnings("unchecked")
    public Optional<Map<String, Object>> getMap(String path) {
        return get(path).flatMap(v -> {
            if (v instanceof Map) {
                return Optional.of((Map<String, Object>) v);
            }
            return Optional.empty();
        });
    }

    /**
     * Creates a navigator for a nested path.
     *
     * @param path the dot-separated path
     * @return a navigator for the nested properties, or an empty navigator if not found
     */
    public ConfigPropertyNavigator navigate(String path) {
        return getMap(path)
                .map(ConfigPropertyNavigator::new)
                .orElseGet(() -> new ConfigPropertyNavigator(Map.of()));
    }
}
