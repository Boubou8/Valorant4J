package fr.boubou.valorant4j.model.match.v4;

import fr.boubou.valorant4j.model.match.common.MatchLocation;
import lombok.Data;

/**
 * @author Lubin "Boubou" B.
 * @date 20/11/2024 18:32
 */
@Data
public  class MatchRoundPlayerLocationV4 {
    private MatchRoundPlayerV4 player;
    private int view_radians;
    private MatchLocation location;
}