package dev.boubou.valorant4j.model.match.v4;

import dev.boubou.valorant4j.model.match.common.MatchLocation;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 20/11/2024 17:58
 */

@Data
public class MatchRoundV4 {

    private int id;
    private String result;
    private String ceremony;
    private String winning_team;
    @Nullable private MatchRoundPlantV4 plant;
    @Nullable private MatchRoundDefuseV4 defuse;
    private List<MatchRoundStatsV4> stats;

    @Data
    private static class MatchRoundPlantV4 {
        private int round_time_in_ms;
        private String site;
        private MatchLocation location;
        private MatchRoundPlayerV4 player;
        private List<MatchRoundPlayerLocationV4> player_locations;
    }

    @Data
    private static class MatchRoundDefuseV4 {

        private int round_time_in_ms;
        private MatchLocation location;
        private MatchRoundPlayerV4 player;
        private List<MatchRoundPlayerLocationV4> player_locations;
    }

    @Data
    private static class MatchRoundStatsV4 {
        private MatchRoundPlayerV4 player;
        private MatchAbilitiesV4 ability_casts;
        private List<MatchDamageEventV4> damage_events;
        private MatchStatsV4 stats;
        private MatchEconomyV4 economy;
        private boolean was_afk;
        private boolean received_penalty;
        private boolean stayed_in_spawn;

        @Data
        private static class MatchDamageEventV4 {
            private MatchRoundPlayerV4 player;
            private int bodyshots;
            private int headshots;
            private int legshots;
            private int damage;
        }

        @Data
        private static class MatchStatsV4 {
            private int bodyshots;
            private int headshots;
            private int legshots;
            private int damage;
            private int kills;
            private int deaths;
            private int assists;
            private int score;
        }

        @Data
        private static class MatchEconomyV4 {
            private int loadout_value;
            private int remaining;
            private MatchWeaponV4 weapon;
            @Nullable private MatchEconomyArmorV4 armor;

            @Data
            private static class MatchEconomyArmorV4 {
                private String id;
                private String name;
            }
        }
    }
}
