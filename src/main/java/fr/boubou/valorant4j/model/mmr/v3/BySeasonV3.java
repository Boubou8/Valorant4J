package fr.boubou.valorant4j.model.mmr.v3;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 12:21
 */

@Data
public class BySeasonV3 {

    private SeasonIdShortComboV3 season;
    private int wins;
    private int games;
    private DataPeakTierV3 end_tier;
    private String ranking_schema;
    @Nullable private CurrentLeaderboardV3 leaderboard_placement;
    private List<DataPeakTierV3> act_wins;
}
