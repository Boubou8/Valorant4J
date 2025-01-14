package fr.boubou.valorant4j.model.mmr.v3;

import lombok.Data;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 12:23
 */

@Data
public class CurrentLeaderboardV3 {
    private int rank;
    private String updated_at;
}
