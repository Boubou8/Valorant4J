package fr.boubou.valorant4j.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 10:35
 */

@Getter
@AllArgsConstructor
public enum MatchMode {

    COMPETITIVE("competitive"),
    UNRATED("unrated"),
    SPIKE_RUSH("spikerush"),
    SWIFT_PLAY("swiftplay"),
    DEATH_MATCH("deathmatch"),
    TEAM_DEATH_MATCH("teamdeathmatch"),
    SNOWBALL_FIGHT("snowballfight"),
    NEW_MAP("newmap"),
    ESCALATION("escalation"),
    REPLICATION("replication"),
    CUSTOM("custom"),
    UNKNOWN("unknown");

    private final String mode;

    public static MatchMode fromString(String mode) {
        for (MatchMode m : MatchMode.values()) {
            if (m.getMode().equalsIgnoreCase(mode)) {
                return m;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String toString() {
        return mode;
    }
}