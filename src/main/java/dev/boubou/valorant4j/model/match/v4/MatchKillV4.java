package dev.boubou.valorant4j.model.match.v4;

import dev.boubou.valorant4j.model.match.common.MatchLocation;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 20/11/2024 18:25
 */

@Data
public class MatchKillV4 {

    private int round;
    private int time_in_round_in_ms;
    private int time_in_match_in_ms;
    private MatchRoundPlayerV4 killer;
    private MatchRoundPlayerV4 victim;
    private List<MatchRoundPlayerV4> assistants;
    @Nullable private MatchLocation location;
    private MatchWeaponV4 weapon;
    private boolean secondary_fire_mode;
    private List<MatchRoundPlayerLocationV4> player_locations;
}
