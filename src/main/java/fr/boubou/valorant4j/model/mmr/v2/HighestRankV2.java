package fr.boubou.valorant4j.model.mmr.v2;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 11:49
 */

@Data
public class HighestRankV2 {

    private boolean old;
    private int tier;
    @Nullable private String patched_tier;
    @Nullable private String season;
}
