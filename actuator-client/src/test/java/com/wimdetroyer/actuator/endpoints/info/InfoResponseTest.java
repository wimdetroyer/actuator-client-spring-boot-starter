package com.wimdetroyer.actuator.endpoints.info;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class InfoResponseTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = JsonMapper.builderWithJackson2Defaults()
                .findAndAddModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();
    }

    @Test
    void deserialize_shouldParseBuildInfo() throws Exception {
        String json = """
                {
                    "build": {
                        "artifact": "my-app",
                        "name": "My Application",
                        "version": "1.0.0",
                        "group": "com.example",
                        "time": "2024-01-01T00:00:00Z"
                    }
                }
                """;

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.build()).isNotNull();
        assertThat(response.build().artifact()).isEqualTo("my-app");
        assertThat(response.build().name()).isEqualTo("My Application");
        assertThat(response.build().version()).isEqualTo("1.0.0");
        assertThat(response.build().group()).isEqualTo("com.example");
        assertThat(response.build().time()).isEqualTo(Instant.parse("2024-01-01T00:00:00Z"));
    }

    @Test
    void deserialize_shouldParseGitInfo() throws Exception {
        String json = """
                {
                    "git": {
                        "branch": "main",
                        "commit": {
                            "id": "abc123",
                            "time": "2024-01-01T12:00:00Z"
                        }
                    }
                }
                """;

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.git()).isNotNull();
        assertThat(response.git().branch()).isEqualTo("main");
        assertThat(response.git().commit().id()).isEqualTo("abc123");
        assertThat(response.git().commit().time()).isEqualTo(Instant.parse("2024-01-01T12:00:00Z"));
    }

    @Test
    void deserialize_shouldParseJavaInfo() throws Exception {
        String json = """
                {
                    "java": {
                        "version": "21.0.1",
                        "vendor": {"name": "Eclipse Adoptium", "version": "Temurin-21.0.1+12"},
                        "runtime": {"name": "OpenJDK Runtime Environment", "version": "21.0.1+12"},
                        "jvm": {"name": "OpenJDK 64-Bit Server VM", "vendor": "Eclipse Adoptium", "version": "21.0.1+12"}
                    }
                }
                """;

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.java()).isNotNull();
        assertThat(response.java().version()).isEqualTo("21.0.1");
        assertThat(response.java().vendor().name()).isEqualTo("Eclipse Adoptium");
        assertThat(response.java().runtime().name()).isEqualTo("OpenJDK Runtime Environment");
        assertThat(response.java().jvm().name()).isEqualTo("OpenJDK 64-Bit Server VM");
    }

    @Test
    void deserialize_shouldParseOsInfo() throws Exception {
        String json = """
                {
                    "os": {
                        "name": "Mac OS X",
                        "version": "14.0",
                        "arch": "aarch64"
                    }
                }
                """;

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.os()).isNotNull();
        assertThat(response.os().name()).isEqualTo("Mac OS X");
        assertThat(response.os().version()).isEqualTo("14.0");
        assertThat(response.os().arch()).isEqualTo("aarch64");
    }

    @Test
    void deserialize_shouldParseProcessInfo() throws Exception {
        String json = """
                {
                    "process": {
                        "pid": 12345,
                        "parentPid": 1,
                        "owner": "user"
                    }
                }
                """;

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.process()).isNotNull();
        assertThat(response.process().pid()).isEqualTo(12345L);
        assertThat(response.process().parentPid()).isEqualTo(1L);
        assertThat(response.process().owner()).isEqualTo("user");
    }

    @Test
    void deserialize_shouldCaptureAdditionalProperties() throws Exception {
        String json = """
                {
                    "custom": {
                        "key": "value"
                    }
                }
                """;

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.additionalProperties()).containsKey("custom");
    }

    @Test
    void deserialize_shouldHandleEmptyJson() throws Exception {
        String json = "{}";

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.java()).isNull();
        assertThat(response.os()).isNull();
        assertThat(response.build()).isNull();
        assertThat(response.git()).isNull();
        assertThat(response.process()).isNull();
        assertThat(response.additionalProperties()).isEmpty();
    }

    @Test
    void deserialize_shouldHandleMixedKnownAndUnknownProperties() throws Exception {
        String json = """
                {
                    "java": {"version": "21"},
                    "custom": "value",
                    "nested": {"a": 1}
                }
                """;

        InfoResponse response = objectMapper.readValue(json, InfoResponse.class);

        assertThat(response.java()).isNotNull();
        assertThat(response.java().version()).isEqualTo("21");
        assertThat(response.additionalProperties()).containsKeys("custom", "nested");
    }
}
