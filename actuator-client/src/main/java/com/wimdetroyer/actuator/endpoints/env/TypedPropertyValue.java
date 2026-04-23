package com.wimdetroyer.actuator.endpoints.env;

import java.util.Optional;

/**
 * Type-safe wrapper for environment property values.
 */
public record TypedPropertyValue(
        Object value,
        String origin
) {
    private static final String SANITIZED_VALUE = "******";

    /**
     * Returns the value as a String.
     *
     * @return the value as String, or empty if null or not a String
     */
    public Optional<String> asString() {
        if (value instanceof String s) {
            return Optional.of(s);
        }
        if (value != null) {
            return Optional.of(String.valueOf(value));
        }
        return Optional.empty();
    }

    /**
     * Returns the value as an Integer.
     *
     * @return the value as Integer, or empty if null or cannot be parsed
     */
    public Optional<Integer> asInteger() {
        if (value instanceof Number n) {
            return Optional.of(n.intValue());
        }
        if (value instanceof String s) {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the value as a Long.
     *
     * @return the value as Long, or empty if null or cannot be parsed
     */
    public Optional<Long> asLong() {
        if (value instanceof Number n) {
            return Optional.of(n.longValue());
        }
        if (value instanceof String s) {
            try {
                return Optional.of(Long.parseLong(s));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the value as a Double.
     *
     * @return the value as Double, or empty if null or cannot be parsed
     */
    public Optional<Double> asDouble() {
        if (value instanceof Number n) {
            return Optional.of(n.doubleValue());
        }
        if (value instanceof String s) {
            try {
                return Optional.of(Double.parseDouble(s));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the value as a Boolean.
     *
     * @return the value as Boolean, or empty if null or not a boolean
     */
    public Optional<Boolean> asBoolean() {
        if (value instanceof Boolean b) {
            return Optional.of(b);
        }
        if (value instanceof String s) {
            if ("true".equalsIgnoreCase(s)) {
                return Optional.of(true);
            }
            if ("false".equalsIgnoreCase(s)) {
                return Optional.of(false);
            }
        }
        return Optional.empty();
    }

    /**
     * Checks if the value is sanitized (masked for security).
     *
     * @return true if the value is sanitized
     */
    public boolean isSanitized() {
        return SANITIZED_VALUE.equals(value);
    }

    /**
     * Checks if the value is null.
     *
     * @return true if the value is null
     */
    public boolean isNull() {
        return value == null;
    }
}
