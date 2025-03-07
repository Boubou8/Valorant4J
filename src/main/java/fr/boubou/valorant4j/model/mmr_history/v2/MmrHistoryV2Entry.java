package fr.boubou.valorant4j.model.mmr_history.v2;

import fr.boubou.valorant4j.model.match.v4.MatchMetadataMapV4;
import fr.boubou.valorant4j.model.mmr.v3.DataPeakTierV3;
import fr.boubou.valorant4j.model.mmr.v3.SeasonIdShortComboV3;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryEntryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:33
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class MmrHistoryV2Entry extends MmrHistoryEntryBase {

    private DataPeakTierV3 tier;
    private SeasonIdShortComboV3 season;
    private int rr;
    private int last_change;
    private int refunded_rr;
    private boolean was_derank_protected;
}
