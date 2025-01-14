package fr.boubou.valorant4j.model.mmr;

import fr.boubou.valorant4j.model.mmr.v3.AccountV3;
import fr.boubou.valorant4j.model.mmr.v3.BySeasonV3;
import fr.boubou.valorant4j.model.mmr.v3.DataCurrentV3;
import fr.boubou.valorant4j.model.mmr.v3.DataPeakV3;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 11:44
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MmrV3 extends MmrBase{

    private AccountV3 account;
    @Nullable private DataPeakV3 peak;
    private DataCurrentV3 current;
    private List<BySeasonV3> seasonal;
}
