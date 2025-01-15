package fr.boubou.valorant4j.route;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.builder.MmrRequestBuilder;
import fr.boubou.valorant4j.ValorantAPI;
import fr.boubou.valorant4j.exceptions.ApiException;
import fr.boubou.valorant4j.model.mmr.MmrBase;
import fr.boubou.valorant4j.parser.ValorantMmrParser;
import fr.boubou.valorant4j.parser.factory.ValorantMmrParserFactory;
import fr.boubou.valorant4j.util.ApiVersion;
import fr.boubou.valorant4j.util.Endpoint;
import fr.boubou.valorant4j.util.HttpService;
import java.util.Set;

/**
 * @author Boubou
 * @date 10/11/2024 23:00
 */
public class MmrService extends Endpoint {

    private static final Set<ApiVersion> SUPPORTED_VERSIONS = Set.of(ApiVersion.V2, ApiVersion.V3);
    private final HttpService httpService;

    public MmrService(ValorantAPI api, ApiVersion version) {
        super(api, version, SUPPORTED_VERSIONS, "valorant/mmr");
        this.httpService = new HttpService(api.getMaxRequestsPerMinute(), api.isRateLimitEnabled());
    }

    public MmrBase fetch(MmrRequestBuilder builder) throws ApiException {
        String endpoint = builder.buildEndpoint(version);
        return fetchMmr(endpoint);
    }

    private MmrBase fetchMmr(String endpoint) throws ApiException {
        final String url = buildUrl(endpoint).replace(" ", "%20");

        try {
            final JsonNode response = httpService.get(url, api.getApiKey());
            if (!response.has("data")) handleApiError(response);

            return parseMmr(response.get("data"));
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des informations MMR : " + e.getMessage(), e);
        }
    }

    private MmrBase parseMmr(JsonNode data) throws Exception {
        final ValorantMmrParser parser = ValorantMmrParserFactory.getParser(version);
        return parser.parse(data);
    }
}