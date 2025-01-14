package dev.boubou.valorant4j.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Lubin "Boubou" B.
 * @date 10/11/2024 18:55
 */

@Slf4j
public class RateLimiter {

    private final int maxRequestsPerMinute;
    private final boolean rateLimitEnabled;

    private long nextResetTime;
    private int requestCount;

    public RateLimiter(int maxRequestsPerMinute, boolean rateLimitEnabled) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.rateLimitEnabled = rateLimitEnabled;
        this.nextResetTime = System.currentTimeMillis() + 60_000;
        this.requestCount = 0;
    }

    public synchronized void acquire() throws InterruptedException {
        log.info("Rate limiter: {} / {}", requestCount, maxRequestsPerMinute);
        if (!rateLimitEnabled) {
            return;
        }

        long currentTime = System.currentTimeMillis();

        log.info("Current time: {}", currentTime);
        if (currentTime >= nextResetTime) {
            // Réinitialise le compteur et le temps de réinitialisation
            requestCount = 0;
            nextResetTime = currentTime + 60_000;
            log.info("Resetting rate limiter");
        }

        log.info("Next reset time: {}", nextResetTime);
        if (requestCount < maxRequestsPerMinute) {
            requestCount++;
            log.info("Request count: {}", requestCount);
        } else {
            // Attend jusqu'à la prochaine minute
            log.info("Rate limit reached, waiting...");
            long waitTime = nextResetTime - currentTime;
            if (waitTime > 0) {
                log.info("Waiting for {} ms", waitTime);
                Thread.sleep(waitTime);
                // Réinitialise après l'attente
                requestCount = 1;
                nextResetTime = System.currentTimeMillis() + 60_000;
                log.info("Rate limiter reset after waiting");
            }
        }
    }

    public synchronized void handle429Response() {
        // Ajuste le compteur en cas de code 429
        log.info("Rate limit exceeded (429), adjusting rate limiter");
        nextResetTime = System.currentTimeMillis() + 60_000;
        requestCount = maxRequestsPerMinute;
    }
}
