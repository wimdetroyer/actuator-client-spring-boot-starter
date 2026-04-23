package com.wimdetroyer.actuator.endpoints.httpexchanges;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class HttpExchangesResponseTest {

    private final ObjectMapper objectMapper = JsonMapper.builderWithJackson2Defaults()
            .findAndAddModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

    @Test
    void deserialize_shouldParseIsoDurationTimeTaken() throws Exception {
        String json = """
                {
                    "exchanges": [
                        {
                            "timestamp": "2026-04-23T11:43:43.506974Z",
                            "request": {
                                "uri": "http://localhost:8081/actuator/health",
                                "method": "GET",
                                "headers": {}
                            },
                            "response": {
                                "status": 200,
                                "headers": {}
                            },
                            "timeTaken": "PT0.004353S"
                        }
                    ]
                }
                """;

        HttpExchangesResponse response = objectMapper.readValue(json, HttpExchangesResponse.class);

        assertThat(response.exchanges()).singleElement()
                .satisfies(exchange -> assertThat(exchange.timeTaken()).isEqualTo(Duration.parse("PT0.004353S")));
    }
}
