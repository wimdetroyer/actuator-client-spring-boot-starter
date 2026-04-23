package com.wimdetroyer.actuator.endpoints.logfile;

import org.springframework.web.client.RestClient;

/**
 * Request builder for getting portions of the log file using range requests.
 */
public final class GetLogFileRequest {

    private final RestClient restClient;
    private final String basePath;
    private Long start;
    private Long end;

    public GetLogFileRequest(RestClient restClient, String basePath) {
        this.restClient = restClient;
        this.basePath = basePath;
    }

    /**
     * Set the start byte position (inclusive).
     */
    public GetLogFileRequest from(long start) {
        GetLogFileRequest request = new GetLogFileRequest(restClient, basePath);
        request.start = start;
        request.end = this.end;
        return request;
    }

    /**
     * Set the end byte position (inclusive).
     */
    public GetLogFileRequest to(long end) {
        GetLogFileRequest request = new GetLogFileRequest(restClient, basePath);
        request.start = this.start;
        request.end = end;
        return request;
    }

    /**
     * Get the last N bytes of the log file.
     */
    public GetLogFileRequest last(long bytes) {
        GetLogFileRequest request = new GetLogFileRequest(restClient, basePath);
        request.start = -bytes;
        request.end = null;
        return request;
    }

    /**
     * Execute the request to get the log file portion.
     * GET /actuator/logfile with Range header
     */
    public String execute() {
        String rangeHeader = buildRangeHeader();

        return restClient.get()
                .uri(basePath + "/logfile")
                .header("Range", rangeHeader)
                .retrieve()
                .body(String.class);
    }

    private String buildRangeHeader() {
        if (start != null && start < 0) {
            // Suffix range (last N bytes)
            return "bytes=" + start;
        } else if (start != null && end != null) {
            return "bytes=" + start + "-" + end;
        } else if (start != null) {
            return "bytes=" + start + "-";
        } else if (end != null) {
            return "bytes=0-" + end;
        } else {
            return "bytes=0-";
        }
    }
}
