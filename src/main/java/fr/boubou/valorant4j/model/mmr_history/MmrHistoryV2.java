package fr.boubou.valorant4j.model.mmr_history;

import fr.boubou.valorant4j.model.mmr_history.v2.MmrHistoryAccountV2;
import fr.boubou.valorant4j.model.mmr_history.v2.MmrHistoryV2Entry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:32
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MmrHistoryV2 extends MmrHistoryBase {

    private MmrHistoryAccountV2 account;
    private List<MmrHistoryV2Entry> history;
}