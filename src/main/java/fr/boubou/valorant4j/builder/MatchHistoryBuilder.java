package fr.boubou.valorant4j.builder;

import fr.boubou.valorant4j.ValorantAPI;
import fr.boubou.valorant4j.enums.MatchMode;
import fr.boubou.valorant4j.exceptions.ApiException;
import fr.boubou.valorant4j.model.ValorantMatch;
import fr.boubou.valorant4j.route.MatchlistService;
import fr.boubou.valorant4j.util.ApiVersion;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.time.Instant;
import java.util.List;

/**
 * @author Boubou
 * @date 12/01/2025 02:22
 *
 * @en-description
 * Builder for fetching match history
 *
 * @fr-description
 * Builder pour récupérer l'historique des parties
 */

@Setter
@Accessors(fluent = true, chain = true)
public class MatchHistoryBuilder {

    private ApiVersion apiVersion = ApiVersion.V4;
    private String region;
    private String platform;

    private String name;
    private String tag;
    private String puuid;

    private MatchMode mode;
    private String map;
    private Integer size;
    private Integer start;

    private Instant startTime;

    public List<ValorantMatch> fetch(ValorantAPI api) throws ApiException {
        validateParameters();

        final MatchlistService service = new MatchlistService(api, apiVersion);

        return (puuid != null)
                ? service.fetchMatchesByPuuid(region, platform, puuid, mode, map, size, start, startTime)
                : service.fetchMatchesByNameTag(region, platform, name, tag, mode, map, size, start, startTime);
    }

    private void validateParameters() {
        if ((name == null || tag == null) && puuid == null) {
            throw new IllegalArgumentException("Name/Tag or PUUID must be set.");
        }

        if (region == null || platform == null) {
            throw new IllegalArgumentException("Region and Platform must be set.");
        }
    }
}