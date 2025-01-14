package dev.boubou.valorant4j.model.mmr.v3;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Lubin "Boubou" B.
 * @date 14/01/2025 12:21
 */

@Data
public class SeasonIdShortComboV3 {
    private String id;
    @JsonProperty("short") private String shortz;
}
