package fr.boubou.valorant4j.model.mmr.v3;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * @author Boubou
 * @date 14/01/2025 12:01
 */

@Data
public class DataCurrentV3 {

    private DataPeakTierV3 tier;
    private int rr;
    private int last_change;
    private int elo;
    private int games_needed_for_rating;
    @Nullable private CurrentLeaderboardV3 leaderboard_placement;
}