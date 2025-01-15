package fr.boubou.valorant4j.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.boubou.valorant4j.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;

/**
 * @author Boubou
 * @date 10/11/2024 21:34
 */

@Slf4j
public class HttpService {

    private final RateLimiter rateLimiter;

    public HttpService(int maxRequestsPerMinute, boolean rateLimitEnabled) {
        this.rateLimiter = new RateLimiter(maxRequestsPerMinute, rateLimitEnabled);
    }

    public JsonNode get(String url, String token) throws Exception {
        log.info("Fetching data from: {}", url);

        log.debug("Acquiring rate limiter");
        rateLimiter.acquire();

        log.debug("Executing request");
        try {

            log.debug("Building request");
            final Content content = Request.get(url)
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", token)
                    .execute().returnContent();

            log.debug("Parsing response");
            return new ObjectMapper().readTree(content.asString());

        } catch (HttpResponseException e) {

            if (e.getStatusCode() == 429) {
                log.warn("Rate limit reached (429), waiting...");
                rateLimiter.handle429Response();

                log.debug("Retrying request");
                rateLimiter.acquire();
                return get(url, token);
            }

            log.error("HTTP error code: {}, message: {}", e.getStatusCode(), e.getMessage());
            throw new ApiException("HTTP error: " + e.getMessage(), e);
        }
    }
}