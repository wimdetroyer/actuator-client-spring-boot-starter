package com.wimdetroyer.actuator.endpoints.conditions;

import java.util.List;
import java.util.Map;

/**
 * Response from the conditions endpoint.
 */
public record ConditionsResponse(
        Map<String, Context> contexts
) {
    public record Context(
            Map<String, List<MatchedCondition>> positiveMatches,
            Map<String, NegativeMatch> negativeMatches,
            List<String> unconditionalClasses,
            String parentId
    ) {}

    public record MatchedCondition(
            String condition,
            String message
    ) {}

    public record NegativeMatch(
            List<MatchedCondition> notMatched,
            List<MatchedCondition> matched
    ) {}
}
