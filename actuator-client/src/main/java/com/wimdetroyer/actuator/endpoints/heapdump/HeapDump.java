package com.wimdetroyer.actuator.endpoints.heapdump;

import org.springframework.core.io.Resource;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

/**
 * Fluent API for the heapdump endpoint.
 */
public record HeapDump(RestClient restClient, String basePath) {

    /**
     * Get a heap dump as an input stream.
     * GET /actuator/heapdump
     *
     * Note: The caller is responsible for closing the returned InputStream.
     */
    public InputStream get() {
        Resource resource = restClient.get()
                .uri(basePath + "/heapdump")
                .retrieve()
                .body(Resource.class);

        if (resource == null) {
            return null;
        }

        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to open heap dump response stream", e);
        }
    }

    /**
     * Get a heap dump as a byte array.
     * GET /actuator/heapdump
     *
     * Warning: Heap dumps can be very large. Use get() for streaming access.
     */
    public byte[] getAsBytes() {
        return restClient.get()
                .uri(basePath + "/heapdump")
                .retrieve()
                .body(byte[].class);
    }
}
