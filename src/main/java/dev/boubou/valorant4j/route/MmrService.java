package dev.boubou.valorant4j.route;

import com.fasterxml.jackson.databind.JsonNode;
import dev.boubou.valorant4j.builder.MmrRequestBuilder;
import dev.boubou.valorant4j.ValorantAPI;
import dev.boubou.valorant4j.exceptions.ApiException;
import dev.boubou.valorant4j.model.mmr.MmrBase;
import dev.boubou.valorant4j.parser.ValorantMmrParser;
import dev.boubou.valorant4j.parser.factory.ValorantMmrParserFactory;
import dev.boubou.valorant4j.util.ApiVersion;
import dev.boubou.valorant4j.util.Endpoint;
import dev.boubou.valorant4j.util.HttpService;

import java.util.Set;

/**
 * Service permettant de récupérer les informations MMR via les endpoints V2 et V3.
 * Endpoints disponibles :
 * - par name/tag
 * - par puuid
 * La query "season" est disponible uniquement pour la version V2.
 *
 * @author Lubin
 * @date 10/11/2024
 */
public class MmrService extends Endpoint {

    private static final Set<ApiVersion> SUPPORTED_VERSIONS = Set.of(ApiVersion.V2, ApiVersion.V3);
    private final HttpService httpService;

    public MmrService(ValorantAPI api, ApiVersion version) {
        super(api, version, SUPPORTED_VERSIONS, "valorant/mmr");
        this.httpService = new HttpService(api.getMaxRequestsPerMinute(), api.isRateLimitEnabled());
    }

    /**
     * Effectue une requête MMR en utilisant un builder pour construire l'endpoint.
     */
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