package fr.boubou.valorant4j.util;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.ValorantAPI;
import fr.boubou.valorant4j.exceptions.ApiException;
import lombok.Getter;

import java.util.Set;

/**
 * @author Lubin "Boubou" B.
 * @date 10/11/2024 23:17
 */
public  class Endpoint {

    protected final static String API_BASE_URL = "https://api.henrikdev.xyz/valorant";

    protected final ValorantAPI api;

    @Getter
    protected final ApiVersion version;

    @Getter
    protected final String endpointPath;

    @Getter
    protected final Set<ApiVersion> supportedVersions;

    protected Endpoint(ValorantAPI api, ApiVersion version, Set<ApiVersion> supportedVersions, String endpointPath) {
        this.api = api;
        this.version = version;
        this.endpointPath = endpointPath;
        this.supportedVersions = supportedVersions;

        if (!supportedVersions.contains(version)) {
            throw new UnsupportedOperationException(
                    String.format("Version %s is not supported by Henrick's API endpoint: %s. (Supported versions: %s)",
                            version.getVersion(),
                            endpointPath,
                            supportedVersions));

        }
    }

    protected String buildUrl(String endpointPath) {
        return String.format("%s/%s", API_BASE_URL, endpointPath);
    }

    protected ValorantAPI getApi() {
        return api;
    }

    protected void handleApiError(JsonNode response) throws ApiException {
        String message = response.path("message").asText();
        throw new ApiException("Erreur lors de l'appel à l'API : " + message);
    }
}
