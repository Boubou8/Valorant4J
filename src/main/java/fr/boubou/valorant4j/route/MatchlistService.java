package fr.boubou.valorant4j.route;

import com.fasterxml.jackson.databind.JsonNode;
import fr.boubou.valorant4j.enums.MatchMode;
import fr.boubou.valorant4j.ValorantAPI;
import fr.boubou.valorant4j.exceptions.ApiException;
import fr.boubou.valorant4j.model.ValorantMatch;
import fr.boubou.valorant4j.model.match.MatchBase;
import fr.boubou.valorant4j.model.match.MatchV2;
import fr.boubou.valorant4j.model.match.MatchV4;
import fr.boubou.valorant4j.parser.ValorantMatchlistParser;
import fr.boubou.valorant4j.parser.factory.ValorantMatchlistParserFactory;
import fr.boubou.valorant4j.util.ApiVersion;
import fr.boubou.valorant4j.util.Endpoint;
import fr.boubou.valorant4j.util.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Boubou
 * @date 20/12/2024 18:52
 */

@Slf4j
public class MatchlistService extends Endpoint {

    private static final Set<ApiVersion> SUPPORTED_VERSIONS = Set.of(ApiVersion.V3, ApiVersion.V4);
    private final HttpService httpService;

    public MatchlistService(ValorantAPI api, ApiVersion version) {
        super(api, version, SUPPORTED_VERSIONS, "valorant/matches");
        this.httpService = new HttpService(api.getMaxRequestsPerMinute(), api.isRateLimitEnabled());
    }

    public List<MatchBase> fetchByNameTag(String region, String platform, String name, String tag) throws ApiException {
        String endpoint = version == ApiVersion.V3
                ? String.format("%s/matches/%s/%s/%s", version.getVersion(), region, name, tag)
                : String.format("%s/matches/%s/%s/%s/%s", version.getVersion(), region, platform, name, tag);

        return fetchMatchListAsBase(endpoint);
    }

    public List<MatchBase> fetchByPUuid(String region, String platform, String puuid) throws ApiException {
        String endpoint = version == ApiVersion.V3
                ? String.format("%s/by-puuid/matches/%s/%s", version.getVersion(), region, puuid)
                : String.format("%s/by-puuid/matches/%s/%s/%s", version.getVersion(), region, platform, puuid);

        return fetchMatchListAsBase(endpoint);
    }

    public List<ValorantMatch> fetchMatchesByNameTag(
            String region,
            String platform,
            String name,
            String tag,
            MatchMode mode,
            String map,
            Integer size,
            Integer start,
            Instant startTime
    ) throws ApiException {
        // 1) Construire le chemin (path)
        String endpoint = buildNameTagEndpoint(region, platform, name, tag);

        // 2) Ajouter la query string
        endpoint = addQueryParameters(endpoint, mode.toString().toLowerCase(), map, size, start);

        // 3) Récupérer la liste de ValorantMatchBase
        List<MatchBase> baseList = fetchMatchListAsBase(endpoint);

        // 4) Convertir en liste de ValorantMatch (agrégateur)
        List<ValorantMatch> valorantMatches = toAggregatedMatches(baseList);

        List<ValorantMatch> filteredMatches;
        if (startTime != null) {
            filteredMatches = valorantMatches.stream()
                    .filter(match -> match.getMatchStart() != null && match.getMatchStart().isAfter(startTime))
                    .toList();
        } else {
            filteredMatches = valorantMatches;
        }

        return filteredMatches;
    }

    public List<ValorantMatch> fetchMatchesByPuuid(
            String region,
            String platform,
            String puuid,
            MatchMode mode,
            String map,
            Integer size,
            Integer start,
            Instant startTime
    ) throws ApiException {
        // 1) Construire le chemin (path)
        String endpoint = buildPuuidEndpoint(region, platform, puuid);

        // 2) Ajouter la query string
        endpoint = addQueryParameters(endpoint, mode.toString().toLowerCase(), map, size, start);

        // 3) Récupérer la liste de ValorantMatchBase
        List<MatchBase> baseList = fetchMatchListAsBase(endpoint);

        // 4) Convertir en liste de ValorantMatch (agrégateur)
        List<ValorantMatch> valorantMatches = toAggregatedMatches(baseList);

        List<ValorantMatch> filteredMatches;
        if (startTime != null) {
            filteredMatches = valorantMatches.stream()
                    .filter(match -> match.getMatchStart() != null && match.getMatchStart().isAfter(startTime))
                    .toList();
        } else {
            filteredMatches = valorantMatches;
        }

        return filteredMatches;
    }

    // -------------------------------------------------------------------------
    // Méthodes privées pour factoriser la logique
    // -------------------------------------------------------------------------

    private String buildNameTagEndpoint(String region, String platform, String name, String tag) {
        if (version == ApiVersion.V3) {
            return String.format("%s/matches/%s/%s/%s", version.getVersion(), region, name, tag);
        } else {
            // V4
            return String.format("%s/matches/%s/%s/%s/%s", version.getVersion(), region, platform, name, tag);
        }
    }

    private String buildPuuidEndpoint(String region, String platform, String puuid) {
        if (version == ApiVersion.V3) {
            return String.format("%s/by-puuid/matches/%s/%s", version.getVersion(), region, puuid);
        } else {
            // V4
            return String.format("%s/by-puuid/matches/%s/%s/%s", version.getVersion(), region, platform, puuid);
        }
    }

    private List<MatchBase> fetchMatchListAsBase(String endpoint) throws ApiException {
        final String url = buildUrl(endpoint).replace(" ", "%20");

        try {
            final JsonNode response = httpService.get(url, api.getApiKey());
            if (!response.has("data")) handleApiError(response);

            ValorantMatchlistParser parser = ValorantMatchlistParserFactory.getParser(version);
            return parser.parse(response.get("data"));
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des listes de matchs : " + e.getMessage(), e);
        }
    }

    private @NotNull List<ValorantMatch> toAggregatedMatches(@NotNull List<MatchBase> baseList) {
        List<ValorantMatch> aggregated = new ArrayList<>();
        for (MatchBase base : baseList) {
            if (base instanceof MatchV2 v2) {
                aggregated.add(new ValorantMatch(v2, null));
            } else if (base instanceof MatchV4 v4) {
                aggregated.add(new ValorantMatch(null, v4));
            }
        }
        return aggregated;
    }

    private @NotNull String addQueryParameters(String endpoint, String mode, String map, Integer size, Integer start) {
        final StringBuilder sb = new StringBuilder(endpoint);
        String prefix = endpoint.contains("?") ? "&" : "?";

        if (mode != null && !mode.isEmpty()) {
            sb.append(prefix).append("mode=").append(mode);
            prefix = "&";
        }
        if (map != null && !map.isEmpty()) {
            sb.append(prefix).append("map=").append(map);
            prefix = "&";
        }
        if (size != null) {
            sb.append(prefix).append("size=").append(size);
            prefix = "&";
        }
        if (version == ApiVersion.V4 && start != null) {
            sb.append(prefix).append("start=").append(start);
        }

        return sb.toString();
    }
}