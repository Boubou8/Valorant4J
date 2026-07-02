package fr.boubou.valorant4j;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Lubin "Boubou" B.
 * @date 02/07/2026 16:40
 */
public class ValorantAPIBuilder {

    private String apiKey;

    private RateLimitConfig rateLimit =
            RateLimitPreset.DEFAULT.getConfig();

    public ValorantAPIBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public ValorantAPIBuilder rateLimit(@NotNull RateLimitPreset preset) {
        this.rateLimit = preset.getConfig();
        return this;
    }

    public ValorantAPIBuilder rateLimit(@NotNull RateLimitConfig config) {
        this.rateLimit = config;
        return this;
    }

    public ValorantAPIBuilder noRateLimit() {
        this.rateLimit = RateLimitPreset.DISABLED.getConfig();
        return this;
    }

    public ValorantAPIBuilder defaultRateLimit() {
        this.rateLimit = RateLimitPreset.DEFAULT.getConfig();
        return this;
    }

    public ValorantAPI build() {

        Objects.requireNonNull(apiKey, "API key cannot be null");

        return new ValorantAPI(apiKey, rateLimit);
    }
}