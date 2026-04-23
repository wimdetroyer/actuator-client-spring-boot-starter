package com.wimdetroyer.actuator.endpoints.env;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TypedPropertyValueTest {

    @Test
    void asString_shouldReturnStringValue() {
        TypedPropertyValue value = new TypedPropertyValue("test", "origin");

        assertThat(value.asString()).contains("test");
    }

    @Test
    void asString_shouldConvertNonStringToString() {
        TypedPropertyValue value = new TypedPropertyValue(123, "origin");

        assertThat(value.asString()).contains("123");
    }

    @Test
    void asString_shouldReturnEmptyForNull() {
        TypedPropertyValue value = new TypedPropertyValue(null, "origin");

        assertThat(value.asString()).isEmpty();
    }

    @Test
    void asInteger_shouldReturnIntegerValue() {
        TypedPropertyValue value = new TypedPropertyValue(42, "origin");

        assertThat(value.asInteger()).contains(42);
    }

    @Test
    void asInteger_shouldParseStringValue() {
        TypedPropertyValue value = new TypedPropertyValue("123", "origin");

        assertThat(value.asInteger()).contains(123);
    }

    @Test
    void asInteger_shouldReturnEmptyForInvalidString() {
        TypedPropertyValue value = new TypedPropertyValue("not-a-number", "origin");

        assertThat(value.asInteger()).isEmpty();
    }

    @Test
    void asLong_shouldReturnLongValue() {
        TypedPropertyValue value = new TypedPropertyValue(9999999999L, "origin");

        assertThat(value.asLong()).contains(9999999999L);
    }

    @Test
    void asDouble_shouldReturnDoubleValue() {
        TypedPropertyValue value = new TypedPropertyValue(3.14, "origin");

        assertThat(value.asDouble()).contains(3.14);
    }

    @Test
    void asBoolean_shouldReturnBooleanValue() {
        TypedPropertyValue value = new TypedPropertyValue(true, "origin");

        assertThat(value.asBoolean()).contains(true);
    }

    @Test
    void asBoolean_shouldParseStringTrue() {
        TypedPropertyValue value = new TypedPropertyValue("true", "origin");

        assertThat(value.asBoolean()).contains(true);
    }

    @Test
    void asBoolean_shouldParseStringFalse() {
        TypedPropertyValue value = new TypedPropertyValue("FALSE", "origin");

        assertThat(value.asBoolean()).contains(false);
    }

    @Test
    void asBoolean_shouldReturnEmptyForNonBoolean() {
        TypedPropertyValue value = new TypedPropertyValue("yes", "origin");

        assertThat(value.asBoolean()).isEmpty();
    }

    @Test
    void isSanitized_shouldReturnTrueForSanitizedValue() {
        TypedPropertyValue value = new TypedPropertyValue("******", "origin");

        assertThat(value.isSanitized()).isTrue();
    }

    @Test
    void isSanitized_shouldReturnFalseForNormalValue() {
        TypedPropertyValue value = new TypedPropertyValue("secret", "origin");

        assertThat(value.isSanitized()).isFalse();
    }

    @Test
    void isNull_shouldReturnTrueForNullValue() {
        TypedPropertyValue value = new TypedPropertyValue(null, "origin");

        assertThat(value.isNull()).isTrue();
    }

    @Test
    void isNull_shouldReturnFalseForNonNullValue() {
        TypedPropertyValue value = new TypedPropertyValue("value", "origin");

        assertThat(value.isNull()).isFalse();
    }
}
