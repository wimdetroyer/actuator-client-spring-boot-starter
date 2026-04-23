package com.wimdetroyer.actuator.endpoints.threaddump;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ThreadDumpResponseTest {

    private final ObjectMapper objectMapper = JsonMapper.builderWithJackson2Defaults()
            .findAndAddModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

    @Test
    void deserialize_shouldKeepBlockedTimeAsNumber() throws Exception {
        String json = """
                {
                    "threads": [
                        {
                            "threadName": "Reference Handler",
                            "threadId": 14,
                            "blockedTime": -1,
                            "blockedCount": 0,
                            "waitedTime": -1,
                            "waitedCount": 0,
                            "lockOwnerId": -1,
                            "daemon": true,
                            "inNative": false,
                            "suspended": false,
                            "threadState": "RUNNABLE",
                            "priority": 10,
                            "stackTrace": [],
                            "lockedMonitors": [],
                            "lockedSynchronizers": []
                        }
                    ]
                }
                """;

        ThreadDumpResponse response = objectMapper.readValue(json, ThreadDumpResponse.class);

        assertThat(response.threads()).singleElement()
                .satisfies(thread -> assertThat(thread.blockedTime()).isEqualTo(-1));
    }
}
