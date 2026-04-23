package com.wimdetroyer.actuator.client;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;

/**
 * Factory for creating ActuatorClient instances with pre-configured timeouts.
 */
public class ActuatorClientFactory {

    private static final String DEFAULT_ACTUATOR_PATH = "/actuator";

    private final Duration connectTimeout;
    private final Duration readTimeout;
    private final JsonMapper jsonMapper;

    public ActuatorClientFactory(Duration connectTimeout, Duration readTimeout) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.jsonMapper = createJsonMapper();
    }

    private static JsonMapper createJsonMapper() {
        return JsonMapper.builderWithJackson2Defaults()
                .findAndAddModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .build();
    }

    /**
     * Create an ActuatorClient for the given base URL.
     */
    public ActuatorClient forUrl(String baseUrl) {
        return forUrl(baseUrl, DEFAULT_ACTUATOR_PATH);
    }

    /**
     * Create an ActuatorClient with custom actuator path.
     */
    public ActuatorClient forUrl(String baseUrl, String actuatorPath) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);

        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .configureMessageConverters(converters -> converters
                        .registerDefaults()
                        .withJsonConverter(new JacksonJsonHttpMessageConverter(jsonMapper)))
                .build();

        return new ActuatorClient(restClient, actuatorPath);
    }
}
