package com.wimdetroyer.actuator.endpoints.loggers;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

class SetLoggerLevelRequestTest {

    private HttpServer server;

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void clear_shouldSendNullConfiguredLevel() throws Exception {
        AtomicReference<String> requestBody = new AtomicReference<>();
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/actuator/loggers/test", exchange -> {
            requestBody.set(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
            exchange.sendResponseHeaders(204, -1);
            exchange.close();
        });
        server.start();

        SetLoggerLevelRequest request = new SetLoggerLevelRequest(restClient(), "/actuator", "test");

        request.clear();

        assertThat(requestBody.get()).isEqualTo("{\"configuredLevel\":null}");
    }

    private RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:" + server.getAddress().getPort())
                .build();
    }
}
