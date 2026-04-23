package com.wimdetroyer.actuator.endpoints.configprops;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigPropertyNavigatorTest {

    @Test
    void get_shouldReturnValueAtPath() {
        Map<String, Object> properties = Map.of(
                "server", Map.of(
                        "port", 8080,
                        "ssl", Map.of(
                                "enabled", true,
                                "keyStore", "/path/to/keystore"
                        )
                )
        );
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(properties);

        assertThat(navigator.get("server.port")).contains(8080);
        assertThat(navigator.get("server.ssl.enabled")).contains(true);
        assertThat(navigator.get("server.ssl.keyStore")).contains("/path/to/keystore");
    }

    @Test
    void get_shouldReturnEmptyForNonExistentPath() {
        Map<String, Object> properties = Map.of("key", "value");
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(properties);

        assertThat(navigator.get("nonexistent")).isEmpty();
        assertThat(navigator.get("key.nested")).isEmpty();
    }

    @Test
    void get_shouldReturnEmptyForNullPath() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("key", "value"));

        assertThat(navigator.get(null)).isEmpty();
        assertThat(navigator.get("")).isEmpty();
    }

    @Test
    void getString_shouldReturnStringValue() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("name", "test"));

        assertThat(navigator.getString("name")).contains("test");
    }

    @Test
    void getString_shouldConvertNonStringToString() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("port", 8080));

        assertThat(navigator.getString("port")).contains("8080");
    }

    @Test
    void getInteger_shouldReturnIntegerValue() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("port", 8080));

        assertThat(navigator.getInteger("port")).contains(8080);
    }

    @Test
    void getInteger_shouldParseStringValue() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("port", "9000"));

        assertThat(navigator.getInteger("port")).contains(9000);
    }

    @Test
    void getLong_shouldReturnLongValue() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("size", 9999999999L));

        assertThat(navigator.getLong("size")).contains(9999999999L);
    }

    @Test
    void getBoolean_shouldReturnBooleanValue() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("enabled", true));

        assertThat(navigator.getBoolean("enabled")).contains(true);
    }

    @Test
    void getBoolean_shouldParseStringValue() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("enabled", "true"));

        assertThat(navigator.getBoolean("enabled")).contains(true);
    }

    @Test
    void getMap_shouldReturnNestedMap() {
        Map<String, Object> ssl = Map.of("enabled", true);
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("ssl", ssl));

        assertThat(navigator.getMap("ssl")).contains(ssl);
    }

    @Test
    void navigate_shouldReturnNavigatorForNestedPath() {
        Map<String, Object> properties = Map.of(
                "server", Map.of(
                        "ssl", Map.of("enabled", true)
                )
        );
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(properties);

        ConfigPropertyNavigator sslNavigator = navigator.navigate("server.ssl");
        assertThat(sslNavigator.getBoolean("enabled")).contains(true);
    }

    @Test
    void navigate_shouldReturnEmptyNavigatorForNonExistentPath() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(Map.of("key", "value"));

        ConfigPropertyNavigator nested = navigator.navigate("nonexistent");
        assertThat(nested.get("anything")).isEmpty();
    }

    @Test
    void constructor_shouldHandleNullProperties() {
        ConfigPropertyNavigator navigator = new ConfigPropertyNavigator(null);

        assertThat(navigator.get("any")).isEmpty();
    }
}
