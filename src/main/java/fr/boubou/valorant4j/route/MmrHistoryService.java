package fr.boubou.valorant4j.route;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.ValorantAPI;
import fr.boubou.valorant4j.exceptions.ApiException;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryBase;
import fr.boubou.valorant4j.parser.ValorantMmrHistoryParser;
import fr.boubou.valorant4j.parser.factory.ValorantMmrHistoryParserFactory;
import fr.boubou.valorant4j.util.ApiVersion;
import fr.boubou.valorant4j.util.Endpoint;
import fr.boubou.valorant4j.util.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
import java.util.Set;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:40
 */
@Slf4j
public class MmrHistoryService extends Endpoint {

    private static final Set<ApiVersion> SUPPORTED_VERSIONS = Set.of(ApiVersion.V1, ApiVersion.V2);
    private final HttpService httpService;

    public MmrHistoryService(ValorantAPI api, ApiVersion version) {
        super(api, version, SUPPORTED_VERSIONS, "valorant/mmr-history");
        this.httpService = new HttpService(api.getMaxRequestsPerMinute(), api.isRateLimitEnabled());
    }

    public MmrHistoryBase fetchByNameTag(String region, String platform, String name, String tag) throws ApiException {
        return fetchMmrHistory(buildNameTagEndpoint(region, platform, name, tag));
    }

    public MmrHistoryBase fetchByPuuid(String region, String platform, String puuid) throws ApiException {
        return fetchMmrHistory(buildPuuidEndpoint(region, platform, puuid));
    }

    private MmrHistoryBase fetchMmrHistory(String endpoint) throws ApiException {
        final String url = buildUrl(endpoint);

        try {
            final JsonNode response = httpService.get(url, api.getApiKey());

            if (!response.has("data")) handleApiError(response);

            return version == ApiVersion.V1 ? parseMatch(response) : parseMatch(response.get("data"));
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération de l'historique de MMR : " + e.getMessage(), e);
        }
    }

    private MmrHistoryBase parseMatch(JsonNode data) throws Exception {
        final ValorantMmrHistoryParser parser = ValorantMmrHistoryParserFactory.getParser(version);
        return parser.parse(data);
    }

    private String buildNameTagEndpoint(String region, String platform, String name, String tag) {
        if (version == ApiVersion.V1) {
            return String.format("%s/mmr-history/%s/%s/%s", version.getVersion(), region, name, tag);
        } else {
            // V2
            return String.format("%s/mmr-history/%s/%s/%s/%s", version.getVersion(), region, platform, name, tag);
        }
    }

    private String buildPuuidEndpoint(String region, String platform, String puuid) {
        if (version == ApiVersion.V1) {
            return String.format("%s/mmr-history/%s/%s", version.getVersion(), region, puuid);
        } else {
            // V2
            return String.format("%s/mmr-history/%s/%s/%s", version.getVersion(), region, platform, puuid);
        }
    }
}
