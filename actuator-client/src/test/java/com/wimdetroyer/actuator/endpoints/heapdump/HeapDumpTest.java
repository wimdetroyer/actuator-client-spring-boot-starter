package com.wimdetroyer.actuator.endpoints.heapdump;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class HeapDumpTest {

    private HttpServer server;

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.stop(0);
        }
    }

    @Test
    void get_shouldReturnReadableInputStreamForBinaryHeapDump() throws Exception {
        byte[] heapDump = "heap".getBytes(StandardCharsets.UTF_8);
        server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/actuator/heapdump", exchange -> {
            exchange.getResponseHeaders().add("Content-Type", "application/octet-stream");
            exchange.sendResponseHeaders(200, heapDump.length);
            exchange.getResponseBody().write(heapDump);
            exchange.close();
        });
        server.start();

        HeapDump endpoint = new HeapDump(restClient(), "/actuator");

        try (InputStream response = endpoint.get()) {
            assertThat(response).isNotNull();
            assertThat(response.readAllBytes()).isEqualTo(heapDump);
        }
    }

    private RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:" + server.getAddress().getPort())
                .build();
    }
}
