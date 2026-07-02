package fr.boubou.valorant4j;

import lombok.Getter;

/**
 * @author Lubin "Boubou" B.
 * @date 02/07/2026 16:29
 */
@Getter
public final class RateLimitConfig {

    private final int maxRequestsPerMinute;
    private final boolean enabled;

    public RateLimitConfig(int maxRequestsPerMinute, boolean enabled) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.enabled = enabled;
    }
}