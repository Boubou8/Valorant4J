package fr.boubou.valorant4j.model.match.v2;

import lombok.Data;

/**
 * @author Boubou
 * @date 11/01/2025 18:24
 */

@Data
public class MatchPlayerSessionPlaytimeV2 {
    private int minutes;
    private int seconds;
    private int milliseconds;
}
