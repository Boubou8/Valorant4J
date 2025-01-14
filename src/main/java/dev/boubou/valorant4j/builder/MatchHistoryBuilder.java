package dev.boubou.valorant4j.builder;

import dev.boubou.valorant4j.ValorantAPI;
import dev.boubou.valorant4j.enums.MatchMode;
import dev.boubou.valorant4j.exceptions.ApiException;
import dev.boubou.valorant4j.model.ValorantMatch;
import dev.boubou.valorant4j.route.MatchlistService;
import dev.boubou.valorant4j.util.ApiVersion;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 12/01/2025 02:22
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
        // Crée un service spécifique
        MatchlistService service = new MatchlistService(api, apiVersion);

        // Construit l’endpoint selon name/tag ou puuid
        if (puuid != null) {
            // fetch par PUUID
            return service.fetchMatchesByPuuid(
                    region,
                    platform,
                    puuid,
                    mode,
                    map,
                    size,
                    start,
                    startTime
            );
        } else if (name != null && tag != null) {
            // fetch par Name/Tag
            return service.fetchMatchesByNameTag(
                    region,
                    platform,
                    name,
                    tag,
                    mode,
                    map,
                    size,
                    start,
                    startTime
            );
        } else {
            throw new IllegalArgumentException("Il faut renseigner soit (name et tag), soit puuid !");
        }
    }
}
