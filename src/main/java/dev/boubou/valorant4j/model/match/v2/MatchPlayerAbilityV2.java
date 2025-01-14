package dev.boubou.valorant4j.model.match.v2;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * @author Lubin "Boubou" B.
 * @date 11/01/2025 19:01
 */

@Data
public class MatchPlayerAbilityV2 {
    @Nullable private Integer c_cast;
    @Nullable private Integer q_cast;
    @Nullable private Integer e_cast;
    @Nullable private Integer x_cast;
}
