package dev.boubou.valorant4j.model;

import dev.boubou.valorant4j.model.match.MatchV2;
import dev.boubou.valorant4j.model.match.MatchV4;
import dev.boubou.valorant4j.model.match.v2.MatchPlayerV2;
import dev.boubou.valorant4j.model.match.v2.MatchTeamV2;
import dev.boubou.valorant4j.model.match.v4.MatchPlayerV4;
import dev.boubou.valorant4j.model.match.v4.MatchTeamV4;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Lubin "Boubou" B.
 * @date 10/01/2025 19:10
 */
@Slf4j
@ToString
public final class ValorantMatch {

    private final MatchV2 matchV2;
    private final MatchV4 matchV4;

    @Getter private final boolean hasV2;
    @Getter private final boolean hasV4;

    private List<ValorantPlayer> valorantPlayers;

    /**
     * Constructeur privé ou package-private,
     * afin de forcer l'utilisation de la factory ou d'un service pour instancier.
     */
    public ValorantMatch(MatchV2 matchV2, MatchV4 matchV4) {
        this.matchV2 = matchV2;
        this.matchV4 = matchV4;
        this.hasV2 = (matchV2 != null);
        this.hasV4 = (matchV4 != null);
    }

    public MatchV2 getV2() {
        return matchV2;
    }
    public MatchV4 getV4() {
        return matchV4;
    }

    public @Nullable Instant getMatchStart() {
        return (hasV2) ? Instant.ofEpochMilli(matchV2.getMetadata().getGame_start()) : (hasV4) ? Instant.parse(matchV4.getMetadata().getStarted_at()) : null;
    }

    public @Nullable String getMatchId() {
        return (hasV2) ? matchV2.getMetadata().getMatchid() : (hasV4) ? matchV4.getMetadata().getMatch_id() : null;
    }

    public @Nullable String getMapName() {
        return (hasV2) ? matchV2.getMetadata().getMap() : (hasV4) ? matchV4.getMetadata().getMap().getName() : null;
    }

    public @Nullable String getMapId() {
        return (hasV4) ? matchV4.getMetadata().getMap().getId() : null;
    }

    public @Nullable String getGameVersion() {
        return (hasV2) ? matchV2.getMetadata().getGame_version() : (hasV4) ? matchV4.getMetadata().getGame_version() : null;
    }

    public @Nullable String getGameLength() {
        return (hasV2) ? String.valueOf(matchV2.getMetadata().getGame_length()) : (hasV4) ? String.valueOf(matchV4.getMetadata().getGame_length_in_ms()) : null;
    }

    public @Nullable String getGameStart() {
        return (hasV2) ? String.valueOf(matchV2.getMetadata().getGame_start()) : (hasV4) ? matchV4.getMetadata().getStarted_at() : null;
    }

    public @Nullable String getGameStartPatched() {
        return (hasV2) ? matchV2.getMetadata().getGame_start_patched() : null;
    }

    public @Nullable String getRoundsPlayed() {
        return (hasV2) ? String.valueOf(matchV2.getMetadata().getRounds_played()) : null;
    }

    public @Nullable String getMode() {
        return (hasV2) ? matchV2.getMetadata().getMode() : (hasV4) ? matchV4.getMetadata().getQueue().getName() : null;
    }

    public @Nullable String getModeId() {
        return (hasV2) ? matchV2.getMetadata().getMode_id() : (hasV4) ? matchV4.getMetadata().getQueue().getId() : null;
    }

    public @Nullable String getQueue() {
        return (hasV2) ? matchV2.getMetadata().getQueue() : (hasV4) ? matchV4.getMetadata().getQueue().getName() : null;
    }

    public @Nullable String getSeasonId() {
        return (hasV2) ? matchV2.getMetadata().getSeason_id() : (hasV4) ? matchV4.getMetadata().getSeason().getId() : null;
    }

    public @Nullable String getSeasonShort() {
        return (hasV4) ? matchV4.getMetadata().getSeason().getShortz() : null;
    }

    public @Nullable String getPlatform() {
        return (hasV2) ? matchV2.getMetadata().getPlatform() : (hasV4) ? matchV4.getMetadata().getPlatform() : null;
    }

    public @Nullable String getRegion() {
        return (hasV2) ? matchV2.getMetadata().getRegion() : (hasV4) ? matchV4.getMetadata().getRegion() : null;
    }

    public @Nullable String getCluster() {
        return (hasV2) ? matchV2.getMetadata().getCluster() : (hasV4) ? matchV4.getMetadata().getCluster() : null;
    }

    public List<ValorantPlayer> getPlayers() {
        if (this.valorantPlayers == null || this.valorantPlayers.isEmpty()) {
            final Map<String, MatchPlayerV2> playerV2Map = extractPlayersV2();
            final Map<String, MatchPlayerV4> playerV4Map = extractPlayersV4();

            // Combiner les joueurs des deux sources
            final Set<String> allPuuids = new HashSet<>();
            allPuuids.addAll(playerV2Map.keySet());
            allPuuids.addAll(playerV4Map.keySet());

            this.valorantPlayers = allPuuids.stream()
                    .map(puuid -> {
                        MatchPlayerV2 playerV2 = playerV2Map.get(puuid);
                        MatchPlayerV4 playerV4 = playerV4Map.get(puuid);
                        return new ValorantPlayer(this, playerV2, playerV4); // Ajoute même si playerV2 ou playerV4 est null
                    })
                    .collect(Collectors.toList());
        }
        return this.valorantPlayers;
    }

    private Map<String, MatchPlayerV2> extractPlayersV2() {
        if (matchV2 == null || matchV2.getPlayers() == null || matchV2.getPlayers().getAll_players() == null) {
            return Collections.emptyMap();
        }
        return matchV2.getPlayers().getAll_players().stream()
                .collect(Collectors.toMap(MatchPlayerV2::getPuuid, Function.identity()));
    }

    private Map<String, MatchPlayerV4> extractPlayersV4() {
        if (matchV4 == null || matchV4.getPlayers() == null) {
            return Collections.emptyMap();
        }
        return matchV4.getPlayers().stream()
                .collect(Collectors.toMap(MatchPlayerV4::getPuuid, Function.identity()));
    }


    public @Nullable ValorantPlayer fetchByPlayerUuid(String playerUuid) {
        for (ValorantPlayer player : getPlayers()) {
            if (player.getUuid().equals(playerUuid)) {
                return player;
            }
        }
        return null;
    }

    public @Nullable ValorantPlayer fetchByPlayerNameTag(String playerName, String playerTag) {
        for (ValorantPlayer player : getPlayers()) {
            if (player.getName().equals(playerName) && player.getTag().equals(playerTag)) {
                return player;
            }
        }
        return null;
    }

    public @Nullable String getWinner() {
        if (hasV2) {
            Boolean blueWon = matchV2.getTeams().getBlue().getHas_won();
            Boolean redWon = matchV2.getTeams().getRed().getHas_won();

            if (Boolean.TRUE.equals(blueWon)) return "Blue";
            if (Boolean.TRUE.equals(redWon)) return "Red";
            return "Draw";
        }

        if (hasV4) {
            boolean anyTeamWon = matchV4.getTeams().stream().anyMatch(MatchTeamV4::isWon);

            if (!anyTeamWon) {
                return "Draw"; // Aucun gagnant dans les équipes
            }

            return matchV4.getTeams().stream()
                    .filter(MatchTeamV4::isWon)
                    .map(MatchTeamV4::getTeam_id)
                    .findFirst()
                    .orElse(null);
        }

        return null;
    }

    public int getBlueScore() {
        if (hasV2) {
            return Optional.ofNullable(matchV2.getTeams().getBlue())
                    .map(MatchTeamV2::getRounds_won)
                    .orElse(-1);
        }

        if (hasV4) {
            return matchV4.getTeams().stream()
                    .filter(team -> "Blue".equals(team.getTeam_id()))
                    .map(team -> team.getRounds().getWon())
                    .findFirst()
                    .orElse(-1);
        }

        return -1;
    }

    public int getRedScore() {
        if (hasV2) {
            return Optional.ofNullable(matchV2.getTeams().getRed())
                    .map(MatchTeamV2::getRounds_won)
                    .orElse(-1);
        }

        if (hasV4) {
            return matchV4.getTeams().stream()
                    .filter(team -> "Red".equals(team.getTeam_id()))
                    .map(team -> team.getRounds().getWon())
                    .findFirst()
                    .orElse(-1);
        }

        return -1;
    }


}
