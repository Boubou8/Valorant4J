package dev.boubou.valorant4j.builder;

import dev.boubou.valorant4j.ValorantAPI;
import dev.boubou.valorant4j.exceptions.ApiException;
import dev.boubou.valorant4j.model.ValorantMmr;
import dev.boubou.valorant4j.model.mmr.MmrV2;
import dev.boubou.valorant4j.model.mmr.MmrV3;
import dev.boubou.valorant4j.route.MmrService;
import dev.boubou.valorant4j.util.ApiVersion;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 15:04
 */
@Setter
@Accessors(fluent = true, chain = true)
public class MmrRequestBuilder {

    private ApiVersion apiVersion = ApiVersion.V3; // Valeur par défaut
    private String region;
    private String platform;

    private String name;
    private String tag;
    private String puuid;

    private String season; // Disponible uniquement pour V2

    /**
     * Effectue la requête MMR et retourne un ValorantMmr.
     *
     * @param api L'instance de ValorantAPI à utiliser pour la requête.
     * @return Une instance de ValorantMmr combinant les résultats des différentes versions.
     * @throws ApiException En cas d'erreur lors de l'appel API.
     */
    public ValorantMmr fetch(ValorantAPI api) throws ApiException {
        MmrV2 mmrV2 = null;
        MmrV3 mmrV3 = null;

        if (apiVersion == ApiVersion.V2 || apiVersion == null) {
            MmrService service = new MmrService(api, ApiVersion.V2);
            mmrV2 = (MmrV2) fetchSingleVersion(service);
        }

        if (apiVersion == ApiVersion.V3 || apiVersion == null) {
            MmrService service = new MmrService(api, ApiVersion.V3);
            mmrV3 = (MmrV3) fetchSingleVersion(service);
        }

        return new ValorantMmr(mmrV2, mmrV3);
    }

    /**
     * Effectue une requête sur une version spécifique via le service.
     *
     * @param service L'instance de MmrService configurée pour une version spécifique.
     * @return Une instance de MmrBase (MmrV2 ou MmrV3).
     * @throws ApiException En cas d'erreur API.
     */
    private Object fetchSingleVersion(MmrService service) throws ApiException {
        if (puuid != null) {
            return service.fetch(
                    new MmrRequestBuilder()
                            .region(region)
                            .platform(platform)
                            .puuid(puuid)
                            .season(season)
            );
        } else if (name != null && tag != null) {
            return service.fetch(
                    new MmrRequestBuilder()
                            .region(region)
                            .platform(platform)
                            .name(name)
                            .tag(tag)
                            .season(season)
            );
        } else {
            throw new IllegalArgumentException("Il faut renseigner soit (name et tag), soit puuid !");
        }
    }

    public String buildEndpoint(ApiVersion version) {
        if (name != null && tag != null) {
            // Endpoint pour name/tag
            String endpoint = version == ApiVersion.V2
                    ? String.format("%s/mmr/%s/%s/%s", version.getVersion(), region, name, tag)
                    : String.format("%s/mmr/%s/%s/%s/%s", version.getVersion(), region, platform, name, tag);
            return addSeasonQuery(endpoint, version);
        } else if (puuid != null) {
            // Endpoint pour puuid
            String endpoint = version == ApiVersion.V2
                    ? String.format("%s/by-puuid/mmr/%s/%s", version.getVersion(), region, puuid)
                    : String.format("%s/by-puuid/mmr/%s/%s/%s", version.getVersion(), region, platform, puuid);
            return addSeasonQuery(endpoint, version);
        } else {
            throw new IllegalStateException("Name/Tag ou PUUID doit être défini.");
        }
    }

    private String addSeasonQuery(String endpoint, ApiVersion version) {
        if (version == ApiVersion.V2 && season != null && !season.isEmpty()) {
            String separator = endpoint.contains("?") ? "&" : "?";
            return endpoint + separator + "season=" + season;
        }
        return endpoint;
    }
}