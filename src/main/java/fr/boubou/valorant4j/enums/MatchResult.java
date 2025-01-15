package fr.boubou.valorant4j.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Boubou
 * @date 14/01/2025 10:42
 */

@Getter
@AllArgsConstructor
public enum MatchResult {

    WIN("Victory"),
    LOSS("Defeat"),
    DRAW("Draw"),
    UNKNOWN("Unknown");

    private final String result;

    public static MatchResult fromString(String result) {
        for (MatchResult r : MatchResult.values()) {
            if (r.getResult().equalsIgnoreCase(result)) {
                return r;
            }
        }
        return UNKNOWN;
    }
}