package com.wimdetroyer.actuator.endpoints.quartz;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;
import java.util.Optional;

/**
 * Type-safe accessor for Quartz job data maps.
 */
public final class JobDataAccessor {

    private static final ObjectMapper MAPPER = JsonMapper.builderWithJackson2Defaults()
            .findAndAddModules()
            .build();
    private final Map<String, Object> data;

    public JobDataAccessor(Map<String, Object> data) {
        this.data = data != null ? data : Map.of();
    }

    /**
     * Gets a value by key.
     *
     * @param key the data key
     * @return the value, or empty if not present
     */
    public Optional<Object> get(String key) {
        return Optional.ofNullable(data.get(key));
    }

    /**
     * Gets a String value by key.
     *
     * @param key the data key
     * @return the String value, or empty if not present or not a String
     */
    public Optional<String> getString(String key) {
        return get(key).flatMap(v -> {
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
     * Gets an Integer value by key.
     *
     * @param key the data key
     * @return the Integer value, or empty if not present or cannot be parsed
     */
    public Optional<Integer> getInteger(String key) {
        return get(key).flatMap(v -> {
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
     * Gets a Long value by key.
     *
     * @param key the data key
     * @return the Long value, or empty if not present or cannot be parsed
     */
    public Optional<Long> getLong(String key) {
        return get(key).flatMap(v -> {
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
     * Gets a Double value by key.
     *
     * @param key the data key
     * @return the Double value, or empty if not present or cannot be parsed
     */
    public Optional<Double> getDouble(String key) {
        return get(key).flatMap(v -> {
            if (v instanceof Number n) {
                return Optional.of(n.doubleValue());
            }
            if (v instanceof String s) {
                try {
                    return Optional.of(Double.parseDouble(s));
                } catch (NumberFormatException e) {
                    return Optional.empty();
                }
            }
            return Optional.empty();
        });
    }

    /**
     * Gets a Boolean value by key.
     *
     * @param key the data key
     * @return the Boolean value, or empty if not present or not a boolean
     */
    public Optional<Boolean> getBoolean(String key) {
        return get(key).flatMap(v -> {
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
     * Gets a typed value by key using Jackson conversion.
     *
     * @param key  the data key
     * @param type the target type
     * @param <T>  the type parameter
     * @return the typed value, or empty if not present or conversion fails
     */
    public <T> Optional<T> get(String key, Class<T> type) {
        return get(key).flatMap(v -> {
            try {
                return Optional.of(MAPPER.convertValue(v, type));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        });
    }

    /**
     * Returns the underlying data map.
     *
     * @return the raw data map
     */
    public Map<String, Object> raw() {
        return data;
    }

    /**
     * Checks if the data map contains a key.
     *
     * @param key the data key
     * @return true if the key is present
     */
    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    /**
     * Checks if the data map is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
