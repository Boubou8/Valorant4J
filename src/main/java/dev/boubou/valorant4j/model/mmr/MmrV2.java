package dev.boubou.valorant4j.model.mmr;

import dev.boubou.valorant4j.model.mmr.v2.BySeasonV2;
import dev.boubou.valorant4j.model.mmr.v2.DataCurrentV2;
import dev.boubou.valorant4j.model.mmr.v2.HighestRankV2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 11:44
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MmrV2 extends MmrBase {

    @Nullable private String name;
    @Nullable private String tag;
    private String puuid;
    private DataCurrentV2 current_data;
    private HighestRankV2 highest_rank;
    private BySeasonV2 by_season;
}