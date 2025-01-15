package fr.boubou.valorant4j;

import fr.boubou.valorant4j.builder.MatchHistoryBuilder;
import fr.boubou.valorant4j.builder.MmrRequestBuilder;
import fr.boubou.valorant4j.exceptions.ApiException;
import fr.boubou.valorant4j.model.ValorantMatch;
import fr.boubou.valorant4j.model.ValorantMmr;
import fr.boubou.valorant4j.model.match.MatchBase;
import fr.boubou.valorant4j.model.match.MatchV2;
import fr.boubou.valorant4j.model.match.MatchV4;
import fr.boubou.valorant4j.route.MatchService;
import fr.boubou.valorant4j.util.ApiVersion;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 10/11/2024 18:52
 */

@Slf4j
@RequiredArgsConstructor
@Getter
@Setter
public class ValorantAPI {

    private String apiKey;
    private int maxRequestsPerMinute;
    private boolean rateLimitEnabled;

    // Constructeur standard avec la clé API comme paramètre obligatoire
    public ValorantAPI(String apiKey) {
        this.apiKey = apiKey;
        this.maxRequestsPerMinute = 30;
        this.rateLimitEnabled = true;
    }

    // Méthode fluide pour configurer le rate limit
    public ValorantAPI setMaxRateLimit(int maxRequestsPerMinute) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        return this;
    }

    // Méthode fluide pour activer ou désactiver le rate limit
    public ValorantAPI manageRateLimit(boolean rateLimitEnabled) {
        this.rateLimitEnabled = rateLimitEnabled;
        return this;
    }

    public ValorantAPI setRateLimiter(int maxRequestsPerMinute, boolean rateLimitEnabled) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.rateLimitEnabled = rateLimitEnabled;
        return this;
    }

    public ValorantMatch fetchMatch(String matchId) {
        final MatchV2 matchV2 = (MatchV2) new MatchService(this, ApiVersion.V2).fetchByMatchId(matchId);
        final MatchV4 matchV4 = (MatchV4) new MatchService(this, ApiVersion.V4).fetchByMatchId(matchId);

        return new ValorantMatch(matchV2, matchV4);
    }

    public ValorantMatch fetchMatchFromVersion(String matchId, ApiVersion version) throws UnsupportedOperationException {
        final MatchBase match = new MatchService(this, version).fetchByMatchId(matchId);
        return switch (version) {
            case V2 -> new ValorantMatch((MatchV2) match, null);
            case V4 -> new ValorantMatch(null, (MatchV4) match);
            default -> throw new UnsupportedOperationException("Version non supportée : " + version);
        };
    }

    public MatchHistoryBuilder matchHistoryBuilder() {
        return new MatchHistoryBuilder();
    }

    public MmrRequestBuilder mmrRequestBuilder() {
        return new MmrRequestBuilder();
    }

    public List<ValorantMatch> fetchMatchHistoryByNameTag(String region, String platform, String name, String tag, int size) throws ApiException {
        return matchHistoryBuilder()
                .region(region)
                .platform(platform)
                .name(name)
                .tag(tag)
                .size(size)
                .fetch(this);
    }

    public List<ValorantMatch> fetchMatchHistoryByUuid(String region, String platform, String uuid, int size) throws ApiException {
        return matchHistoryBuilder()
                .region(region)
                .platform(platform)
                .puuid(uuid)
                .size(size)
                .fetch(this);
    }

    public ValorantMmr fetchMmrByNameTag(@NonNull String region, @NonNull String platform, @NonNull String name, @NonNull String tag, @Nullable String season, @Nullable ApiVersion version) throws ApiException {
        return mmrRequestBuilder()
                .region(region)
                .platform(platform)
                .name(name)
                .tag(tag)
                .season(season)
                .apiVersion(version)
                .fetch(this);
    }

    /**
     * Récupère les informations MMR par PUUID.
     */
    public ValorantMmr fetchMmrByPuuid(@NonNull String region, @NonNull String platform, @NonNull String puuid, @Nullable String season, @Nullable ApiVersion version) throws ApiException {
        return mmrRequestBuilder()
                .region(region)
                .platform(platform)
                .puuid(puuid)
                .season(season)
                .apiVersion(version)
                .fetch(this);
    }
}