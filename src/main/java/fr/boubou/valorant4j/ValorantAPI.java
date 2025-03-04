package fr.boubou.valorant4j;

import fr.boubou.valorant4j.builder.MatchHistoryBuilder;
import fr.boubou.valorant4j.builder.MmrRequestBuilder;
import fr.boubou.valorant4j.exceptions.ApiException;
import fr.boubou.valorant4j.model.ValorantAccount;
import fr.boubou.valorant4j.model.ValorantMatch;
import fr.boubou.valorant4j.model.ValorantMmr;
import fr.boubou.valorant4j.model.ValorantMmrHistory;
import fr.boubou.valorant4j.model.account.AccountV1;
import fr.boubou.valorant4j.model.account.AccountV2;
import fr.boubou.valorant4j.model.match.MatchBase;
import fr.boubou.valorant4j.model.match.MatchV2;
import fr.boubou.valorant4j.model.match.MatchV4;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryV1;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryV2;
import fr.boubou.valorant4j.route.AccountService;
import fr.boubou.valorant4j.route.MatchService;
import fr.boubou.valorant4j.route.MmrHistoryService;
import fr.boubou.valorant4j.util.ApiVersion;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Boubou
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

    public ValorantAPI(String apiKey) {
        this.apiKey = apiKey;
        this.maxRequestsPerMinute = 30;
        this.rateLimitEnabled = true;
    }

    public ValorantAPI setMaxRateLimit(int maxRequestsPerMinute) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        return this;
    }

    public ValorantAPI manageRateLimit(boolean rateLimitEnabled) {
        this.rateLimitEnabled = rateLimitEnabled;
        return this;
    }

    public ValorantAPI setRateLimiter(int maxRequestsPerMinute, boolean rateLimitEnabled) {
        this.maxRequestsPerMinute = maxRequestsPerMinute;
        this.rateLimitEnabled = rateLimitEnabled;
        return this;
    }

    public ValorantAccount fetchAccountByNameTag(String name, String tag) throws ApiException {
        final AccountV1 accountV1 = (AccountV1) new AccountService(this, ApiVersion.V1).fetchByNameTag(name, tag);
        final AccountV2 accountV2 = (AccountV2) new AccountService(this, ApiVersion.V2).fetchByNameTag(name, tag);

        return new ValorantAccount(accountV1, accountV2);
    }

    public ValorantAccount fetchAccountByPuuid(String puuid) throws ApiException {
        final AccountV1 accountV1 = (AccountV1) new AccountService(this, ApiVersion.V1).fetchByPuuid(puuid);
        final AccountV2 accountV2 = (AccountV2) new AccountService(this, ApiVersion.V2).fetchByPuuid(puuid);

        return new ValorantAccount(accountV1, accountV2);
    }

    public ValorantMatch fetchMatch(String matchId) {
        final MatchV2 matchV2 = (MatchV2) new MatchService(this, ApiVersion.V2).fetchByMatchId(matchId);
        final MatchV4 matchV4 = (MatchV4) new MatchService(this, ApiVersion.V4).fetchByMatchId(matchId);

        return new ValorantMatch(matchV2, matchV4);
    }

    public ValorantMatch fetchMatch(String matchId, ApiVersion version) throws UnsupportedOperationException {
        final MatchBase match = new MatchService(this, version).fetchByMatchId(matchId);
        return switch (version) {
            case V2 -> new ValorantMatch((MatchV2) match, null);
            case V4 -> new ValorantMatch(null, (MatchV4) match);
            default -> throw new UnsupportedOperationException("Unsupported API version: " + version);
        };
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

    public ValorantMmr fetchMmrByPuuid(@NonNull String region, @NonNull String platform, @NonNull String puuid, @Nullable String season, @Nullable ApiVersion version) throws ApiException {
        return mmrRequestBuilder()
                .region(region)
                .platform(platform)
                .puuid(puuid)
                .season(season)
                .apiVersion(version)
                .fetch(this);
    }

    public ValorantMmrHistory fetchMmrHistoryByNameTag(@NonNull String region, @NonNull String platform, @NonNull String name, @NonNull String tag) throws ApiException {
        final MmrHistoryV1 mmrHistoryV1 = (MmrHistoryV1) new MmrHistoryService(this, ApiVersion.V1).fetchByNameTag(region, platform, name, tag);
        final MmrHistoryV2 mmrHistoryV2 = (MmrHistoryV2) new MmrHistoryService(this, ApiVersion.V2).fetchByNameTag(region, platform, name, tag);

        return new ValorantMmrHistory(mmrHistoryV1, mmrHistoryV2);
    }

    public ValorantMmrHistory fetchMmrHistoryByPuuid(@NonNull String region, @NonNull String platform, @NonNull String puuid) throws ApiException {
        final MmrHistoryV1 mmrHistoryV1 = (MmrHistoryV1) new MmrHistoryService(this, ApiVersion.V1).fetchByPuuid(region, platform, puuid);
        final MmrHistoryV2 mmrHistoryV2 = (MmrHistoryV2) new MmrHistoryService(this, ApiVersion.V2).fetchByPuuid(region, platform, puuid);

        return new ValorantMmrHistory(mmrHistoryV1, mmrHistoryV2);
    }

    public MatchHistoryBuilder matchHistoryBuilder() {
        return new MatchHistoryBuilder();
    }

    public MmrRequestBuilder mmrRequestBuilder() {
        return new MmrRequestBuilder();
    }
}