package com.wimdetroyer.actuator.endpoints.quartz;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JobDataAccessorTest {

    @Test
    void get_shouldReturnValue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("key", "value"));

        assertThat(accessor.get("key")).contains("value");
    }

    @Test
    void get_shouldReturnEmptyForMissingKey() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("key", "value"));

        assertThat(accessor.get("missing")).isEmpty();
    }

    @Test
    void getString_shouldReturnStringValue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("name", "test-job"));

        assertThat(accessor.getString("name")).contains("test-job");
    }

    @Test
    void getString_shouldConvertNonStringToString() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("count", 42));

        assertThat(accessor.getString("count")).contains("42");
    }

    @Test
    void getInteger_shouldReturnIntegerValue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("count", 10));

        assertThat(accessor.getInteger("count")).contains(10);
    }

    @Test
    void getInteger_shouldParseStringValue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("count", "25"));

        assertThat(accessor.getInteger("count")).contains(25);
    }

    @Test
    void getInteger_shouldReturnEmptyForInvalidString() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("count", "invalid"));

        assertThat(accessor.getInteger("count")).isEmpty();
    }

    @Test
    void getLong_shouldReturnLongValue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("timestamp", 1704067200000L));

        assertThat(accessor.getLong("timestamp")).contains(1704067200000L);
    }

    @Test
    void getDouble_shouldReturnDoubleValue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("rate", 0.75));

        assertThat(accessor.getDouble("rate")).contains(0.75);
    }

    @Test
    void getBoolean_shouldReturnBooleanValue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("active", true));

        assertThat(accessor.getBoolean("active")).contains(true);
    }

    @Test
    void getBoolean_shouldParseStringTrue() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("active", "true"));

        assertThat(accessor.getBoolean("active")).contains(true);
    }

    @Test
    void getBoolean_shouldParseStringFalse() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("active", "FALSE"));

        assertThat(accessor.getBoolean("active")).contains(false);
    }

    @Test
    void getTyped_shouldConvertToCustomType() {
        record JobConfig(String name, int priority) {}
        JobDataAccessor accessor = new JobDataAccessor(Map.of(
                "config", Map.of("name", "test", "priority", 5)
        ));

        assertThat(accessor.get("config", JobConfig.class))
                .hasValueSatisfying(config -> {
                    assertThat(config.name()).isEqualTo("test");
                    assertThat(config.priority()).isEqualTo(5);
                });
    }

    @Test
    void raw_shouldReturnUnderlyingMap() {
        Map<String, Object> data = Map.of("key", "value");
        JobDataAccessor accessor = new JobDataAccessor(data);

        assertThat(accessor.raw()).isEqualTo(data);
    }

    @Test
    void containsKey_shouldReturnTrueForExistingKey() {
        JobDataAccessor accessor = new JobDataAccessor(Map.of("key", "value"));

        assertThat(accessor.containsKey("key")).isTrue();
        assertThat(accessor.containsKey("missing")).isFalse();
    }

    @Test
    void isEmpty_shouldReturnCorrectValue() {
        assertThat(new JobDataAccessor(Map.of()).isEmpty()).isTrue();
        assertThat(new JobDataAccessor(Map.of("key", "value")).isEmpty()).isFalse();
    }

    @Test
    void constructor_shouldHandleNullData() {
        JobDataAccessor accessor = new JobDataAccessor(null);

        assertThat(accessor.isEmpty()).isTrue();
        assertThat(accessor.get("any")).isEmpty();
    }
}
