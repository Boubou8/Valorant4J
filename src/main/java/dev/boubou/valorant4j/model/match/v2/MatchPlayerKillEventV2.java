package dev.boubou.valorant4j.model.match.v2;

import dev.boubou.valorant4j.model.match.common.MatchLocation;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 11/01/2025 19:36
 */

@Data
public class MatchPlayerKillEventV2 {
    private int kill_time_in_round;
    private int kill_time_in_match;
    @Nullable private int round;
    private String killer_puuid;
    private String killer_display_name;
    private String killer_team;
    private String victim_puuid;
    private String victim_display_name;
    private String victim_team;
    private MatchLocation victim_death_location;
    private String damage_weapon_id;
    private String damage_weapon_name;
    private DamageWeaponAssetV2 damage_weapon_assets;
    private boolean secondary_fire_mode;
    private List<MatchRoundV2.MatchKillPlayerLocationOnV2> player_locations_on_kill;
    private List<MatchKillAssistantV2> assistants;

    @Data
    public static class DamageWeaponAssetV2 {
        private String display_icon;
        private String killfeed_icon;
    }

    @Data
    public static class MatchKillAssistantV2 {
        private String assistant_puuid;
        private String assistant_display_name;
        private String assistant_team;
    }
}
