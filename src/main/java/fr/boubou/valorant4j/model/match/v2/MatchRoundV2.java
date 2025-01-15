package fr.boubou.valorant4j.model.match.v2;

import fr.boubou.valorant4j.model.match.common.MatchLocation;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 11/01/2025 18:49
 */

@Data
public class MatchRoundV2 {

    private String winning_team;
    private String end_type;
    @Nullable private Boolean bomb_planted;
    @Nullable private Boolean bomb_defused;
    @Nullable private MatchRoundPlantEventsV2 plant_events;
    @Nullable private MatchRoundDefuseEventsV2 defuse_events;
    private List<PlayerStatsV2> player_stats;

    @Data
    public static class MatchRoundPlantEventsV2 {
        @Nullable private MatchLocation plant_location;
        @Nullable private MatchPlantByV2 planted_by;
        @Nullable private String plant_site;
        @Nullable private Integer plant_time_in_round;
        @Nullable private List<MatchKillPlayerLocationOnV2> player_locations_on_plant;

        @Data
        public static class MatchPlantByV2 {
            private String puuid;
            private String display_name;
            private String team;
        }
    }

    @Data
    public static class MatchRoundDefuseEventsV2 {
        @Nullable private MatchLocation defuse_location;
        @Nullable private MatchDefuseByV2 defused_by;
        @Nullable private Integer defuse_time_in_round;
        @Nullable private List<MatchKillPlayerLocationOnV2> player_locations_on_defuse;

        @Data
        public static class MatchDefuseByV2 {
            private String puuid;
            private String display_name;
            private String team;
        }
    }

    @Data
    public static class MatchKillPlayerLocationOnV2 {
        private String player_puuid;
        private String player_display_name;
        private String player_team;
        private MatchLocation location;
        private double view_radians;
    }

    @Data
    public static class PlayerStatsV2 {
        private PlayerAbilityV2 ability_casts;
        private String player_puuid;
        private String player_display_name;
        private String player_team;
        private List<DamageEventV2> damage_events;
        private int damage;
        private int bodyshots;
        private int headshots;
        private int legshots;
        private List<MatchPlayerKillEventV2> kill_events;
        private int kills;
        private int score;
        private EconomyV2 economy;
        private boolean was_afk;
        private boolean was_penalized;
        private boolean stayed_in_spawn;

        @Data
        public static class PlayerAbilityV2 {
            @Nullable private Integer c_casts;
            @Nullable private Integer q_casts;
            @Nullable private Integer e_casts;
            @Nullable private Integer x_casts;
        }

        @Data
        public static class DamageEventV2 {
            private String receiver_puuid;
            private String receiver_display_name;
            private String receiver_team;
            private int bodyshots;
            private int damage;
            private int headshots;
            private int legshots;
        }

        @Data
        public static class EconomyV2 {
            private int loadout_value;
            private EconomyWeaponV2 weapon;
            private EconomyArmorV2 armor;
            private int remaining;
            private int spent;

            @Data
            public static class EconomyWeaponV2 {
                private String id;
                private String name;
                private EconomyWeaponAssetV2 assets;

                @Data
                public static class EconomyWeaponAssetV2 {
                    private String display_icon;
                    private String killfeed_icon;
                }
            }

            @Data
            public static class EconomyArmorV2 {
                private String id;
                private String name;
                private EconomyArmorAssetV2 assets;

                @Data
                public static class EconomyArmorAssetV2 {
                    private String display_icon;
                }
            }
        }
    }
}
