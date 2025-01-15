package fr.boubou.valorant4j.builder;

import fr.boubou.valorant4j.ValorantAPI;
import fr.boubou.valorant4j.exceptions.ApiException;
import fr.boubou.valorant4j.model.ValorantMmr;
import fr.boubou.valorant4j.model.mmr.MmrV2;
import fr.boubou.valorant4j.model.mmr.MmrV3;
import fr.boubou.valorant4j.route.MmrService;
import fr.boubou.valorant4j.util.ApiVersion;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * @author Boubou
 * @date 14/01/2025 15:04
 */

@Setter
@Accessors(fluent = true, chain = true)
public class MmrRequestBuilder {

    private ApiVersion apiVersion = ApiVersion.V3; // Default to V3
    private String region;
    private String platform;

    private String name;
    private String tag;
    private String puuid;

    private String season; // Only for V2

    public ValorantMmr fetch(ValorantAPI api) throws ApiException {
        validateParameters();

        MmrV2 mmrV2 = null;
        MmrV3 mmrV3 = null;

        if (shouldFetchVersion(ApiVersion.V2)) {
            mmrV2 = fetchVersion(api, ApiVersion.V2, MmrV2.class);
        }
        if (shouldFetchVersion(ApiVersion.V3)) {
            mmrV3 = fetchVersion(api, ApiVersion.V3, MmrV3.class);
        }

        return new ValorantMmr(mmrV2, mmrV3);
    }

    private boolean shouldFetchVersion(ApiVersion version) {
        return apiVersion == version || apiVersion == null;
    }

    private <T> T fetchVersion(ValorantAPI api, ApiVersion version, @NotNull Class<T> clazz) throws ApiException {
        final MmrService service = new MmrService(api, version);

        return clazz.cast(service.fetch(this));
    }

    public String buildEndpoint(ApiVersion version) {
        validateEndpointParameters();

        String endpoint;
        if (puuid != null) {
            endpoint = version == ApiVersion.V2
                    ? String.format("%s/by-puuid/mmr/%s/%s", version.getVersion(), region, puuid)
                    : String.format("%s/by-puuid/mmr/%s/%s/%s", version.getVersion(), region, platform, puuid);
        } else {
            endpoint = version == ApiVersion.V2
                    ? String.format("%s/mmr/%s/%s/%s", version.getVersion(), region, name, tag)
                    : String.format("%s/mmr/%s/%s/%s/%s", version.getVersion(), region, platform, name, tag);
        }

        return addSeasonQuery(endpoint, version);
    }

    private String addSeasonQuery(String endpoint, ApiVersion version) {
        if (version == ApiVersion.V2 && season != null && !season.isEmpty()) {
            String separator = endpoint.contains("?") ? "&" : "?";
            return endpoint + separator + "season=" + season;
        }
        return endpoint;
    }

    private void validateParameters() {
        if (puuid == null && (name == null || tag == null)) {
            throw new IllegalArgumentException("Name/Tag or PUUID must be set.");
        }
        if (region == null || platform == null) {
            throw new IllegalArgumentException("Region and Platform must be set.");
        }
    }

    private void validateEndpointParameters() {
        if (puuid == null && (name == null || tag == null)) {
            throw new IllegalStateException("Name/Tag or PUUID must be set.");
        }
    }
}