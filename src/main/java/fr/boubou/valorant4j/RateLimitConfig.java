package fr.boubou.valorant4j;

/**
 * @author Lubin "Boubou" B.
 * @date 02/07/2026 16:29
 */
public record RateLimitConfig(int maxRequestsPerMinute, boolean enabled) {

    public RateLimitConfig {
        if (maxRequestsPerMinute < 0)
            throw new IllegalArgumentException(
                    "maxRequestsPerMinute must be >= 0"
            );
    }
}