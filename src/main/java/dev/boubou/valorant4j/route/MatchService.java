package dev.boubou.valorant4j.route;

import com.fasterxml.jackson.databind.JsonNode;
import dev.boubou.valorant4j.ValorantAPI;
import dev.boubou.valorant4j.exceptions.ApiException;
import dev.boubou.valorant4j.model.match.MatchBase;
import dev.boubou.valorant4j.parser.ValorantMatchParser;
import dev.boubou.valorant4j.parser.factory.ValorantMatchParserFactory;
import dev.boubou.valorant4j.util.ApiVersion;
import dev.boubou.valorant4j.util.Endpoint;
import dev.boubou.valorant4j.util.HttpService;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author Lubin "Boubou" B.
 * @date 10/11/2024 20:08
 */

@Slf4j
public class MatchService extends Endpoint {

    private static final Set<ApiVersion> SUPPORTED_VERSIONS = Set.of(ApiVersion.V2, ApiVersion.V4);

    private final HttpService httpService;

    public MatchService(ValorantAPI api, ApiVersion version) {
        super(api, version, SUPPORTED_VERSIONS, "valorant/match");
        this.httpService = new HttpService(api.getMaxRequestsPerMinute(), api.isRateLimitEnabled());
    }

    public MatchBase fetchByMatchId(String matchId) throws ApiException {
        final String url = buildUrl(String.format("%s/match/%s", version.getVersion(), version == ApiVersion.V2 ? matchId : "EU/" + matchId));

        try {
            final JsonNode response = httpService.get(url, api.getApiKey());

            if (!response.has("data")) handleApiError(response);

            return parseMatch(response.get("data"));
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération du compte : " + e.getMessage(), e);
        }
    }

    private MatchBase parseMatch(JsonNode data) throws Exception {
        final ValorantMatchParser parser = ValorantMatchParserFactory.getParser(version);
        return parser.parse(data);
    }
}