package fr.boubou.valorant4j.model.mmr_history;

import fr.boubou.valorant4j.model.mmr_history.v1.MmrHistoryV1Entry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MmrHistoryV1 extends MmrHistoryBase {

    private String name;
    private String tag;
    private List<MmrHistoryV1Entry> data;
}