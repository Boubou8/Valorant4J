package fr.boubou.valorant4j.model;

import fr.boubou.valorant4j.enums.MatchResult;
import fr.boubou.valorant4j.model.match.v2.MatchPlayerV2;
import fr.boubou.valorant4j.model.match.v4.MatchPlayerV4;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lubin "Boubou" B.
 * @date 11/01/2025 23:16
 */

@NoArgsConstructor
public class ValorantPlayer {

    private ValorantMatch match;
    private MatchPlayerV2 playerV2;
    private MatchPlayerV4 playerV4;

    @Getter private boolean hasV2;
    @Getter private boolean hasV4;

    public ValorantPlayer(ValorantMatch match, MatchPlayerV2 playerV2, MatchPlayerV4 playerV4) {
        this.match = match;
        this.playerV2 = playerV2;
        this.playerV4 = playerV4;
        this.hasV2 = (playerV2 != null);
        this.hasV4 = (playerV4 != null);
    }

    public MatchPlayerV2 getV2() {
        return playerV2;
    }

    public MatchPlayerV4 getV4() {
        return playerV4;
    }

    public String getUuid() {
        return (hasV2) ? playerV2.getPuuid() : (hasV4) ? playerV4.getPuuid() : null;
    }

    public String getName() {
        return (hasV2) ? playerV2.getName() : (hasV4) ? playerV4.getName() : null;
    }

    public String getTag() {
        return (hasV2) ? playerV2.getTag() : (hasV4) ? playerV4.getTag() : null;
    }

    public String getFullName() {
        return (hasV2) ? playerV2.getName() + "#" + playerV2.getTag() : (hasV4) ? playerV4.getName() + "#" + playerV4.getTag() : null;
    }

    public String getTeam() {
        return (hasV2) ? playerV2.getTeam() : (hasV4) ? playerV4.getTeam_id() : null;
    }

    public boolean isTeamRed() {
        return (hasV2) ? playerV2.getTeam().equals("Red") : (hasV4) ? playerV4.getTeam_id().equals("Red") : false;
    }

    public boolean isTeamBlue() {
        return (hasV2) ? playerV2.getTeam().equals("Blue") : (hasV4) ? playerV4.getTeam_id().equals("Blue") : false;
    }

    public boolean hasWon() {
        final String team = getTeam();
        final Map<String, Integer> scores = getScores();
        return (team.equals("Blue") && scores.get("blue") > scores.get("red"))
                || (team.equals("Red") && scores.get("red") > scores.get("blue"));
    }

    private @NotNull Map<String, Integer> getScores() {
        final Map<String, Integer> scores = new HashMap<>();
        scores.put("blue", match.getBlueScore());
        scores.put("red", match.getRedScore());
        return scores;
    }

    public MatchResult getResult() {
        final String team = getTeam();
        final Map<String, Integer> scores = getScores();
        final int blueScore = scores.get("blue");
        final int redScore = scores.get("red");

        if (blueScore == redScore) {
            return MatchResult.DRAW;
        }
        return hasWon() ? MatchResult.WIN : MatchResult.LOSS;
    }

    public int getKills() {
        return (hasV2) ? playerV2.getStats().getKills() : (hasV4) ? playerV4.getStats().getKills() : -1;
    }

    public int getDeaths() {
        return (hasV2) ? playerV2.getStats().getDeaths() : (hasV4) ? playerV4.getStats().getDeaths() : -1;
    }

    public int getAssists() {
        return (hasV2) ? playerV2.getStats().getAssists() : (hasV4) ? playerV4.getStats().getAssists() : -1;
    }

    public int getScore() {
        return (hasV2) ? playerV2.getStats().getScore() : (hasV4) ? playerV4.getStats().getScore() : -1;
    }

    public int getDamageMade() {
        return (hasV2) ? playerV2.getDamage_made() : (hasV4) ? playerV4.getStats().getDamage().getDealt() : -1;
    }

    public int getDamageReceived() {
        return (hasV2) ? playerV2.getDamage_received() : (hasV4) ? playerV4.getStats().getDamage().getReceived() : -1;
    }

    public int getHeadshots() {
        return (hasV2) ? playerV2.getStats().getHeadshots() : (hasV4) ? playerV4.getStats().getHeadshots() : -1;
    }

    public int getBodyshots() {
        return (hasV2) ? playerV2.getStats().getBodyshots() : (hasV4) ? playerV4.getStats().getBodyshots() : -1;
    }

    public int getLegshots() {
        return (hasV2) ? playerV2.getStats().getLegshots() : (hasV4) ? playerV4.getStats().getLegshots() : -1;
    }

    public @Nullable String getAgent() {
        return (hasV2) ? playerV2.getCharacter() : (hasV4) ? playerV4.getAgent().getName() : null;
    }
}