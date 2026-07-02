package fr.boubou.valorant4j;

import lombok.Getter;

/**
 * @author Lubin "Boubou" B.
 * @date 02/07/2026 16:29
 */
@Getter
public enum RateLimitPreset {

    DEFAULT(new RateLimitConfig(30, true)),
    UPGRADED(new RateLimitConfig(60, true)),
    DISABLED(new RateLimitConfig(0, false));

    private final RateLimitConfig config;

    RateLimitPreset(RateLimitConfig config) {
        this.config = config;
    }
}