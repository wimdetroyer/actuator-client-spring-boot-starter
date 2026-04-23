package com.wimdetroyer.actuator.endpoints.info.details;

import java.time.Instant;

/**
 * Typed git information from the info endpoint.
 *
 * @param branch the name of the branch
 * @param commit the commit information
 */
public record GitInfo(
        String branch,
        GitCommit commit
) {
    /**
     * Git commit information.
     *
     * @param id   the commit ID (abbreviated or full, depending on configuration)
     * @param time the timestamp of the commit
     */
    public record GitCommit(
            String id,
            Instant time
    ) {}
}
