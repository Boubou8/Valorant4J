package fr.boubou.valorant4j.model.match.v2;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * @author Lubin "Boubou" B.
 * @date 10/01/2025 20:00
 */

@Data
public class MatchPlayerV2 {

    private String puuid;
    private String name;
    private String tag;
    private String team;
    private int level;
    private String character;
    private int currenttier;
    private String currenttier_patched;
    private String player_card;
    private String player_title;
    private String party_id;
    private MatchPlayerSessionPlaytimeV2 session_playtime;
    private MatchPlayerAssetsV2 assets;
    private MatchPlayerBehaviourV2 behavior;
    private MatchPlayerPlatformV2 platform;
    private MatchPlayerAbilityV2 ability_casts;
    private MatchPlayerStatsV2 stats;
    private MatchPlayerEconomyV2 economy;
    private int damage_made;
    private int damage_received;

    @Data
    public static class MatchPlayerAssetsV2 {
        private AssetsCardV2 card;
        private AssetsAgentV2 agent;

        @Data
        public static class AssetsCardV2 {
            private String small;
            private String large;
            private String wide;
        }

        @Data
        public static class AssetsAgentV2 {
            private String small;
            private String full;
            private String bust;
            private String killfeed;
        }
    }

    @Data
    public static class MatchPlayerBehaviourV2 {
        private int afk_rounds;
        private FriendlyFireV2 friendly_fire;
        private int rounds_in_spawn;

        @Data
        public static class FriendlyFireV2 {
            private int incoming;
            private int outgoing;
        }
    }

    @Data
    public static class MatchPlayerStatsV2 {
        private int score;
        private int kills;
        private int deaths;
        private int assists;
        private int bodyshots;
        private int headshots;
        private int legshots;
    }

    @Data
    public static class MatchPlayerEconomyV2 {
        private PlayerEconomyV2 spent;
        private PlayerEconomyV2 loadout_value;

        @Data
        public static class PlayerEconomyV2 {
            private int overall;
            private int average;
        }
    }
}