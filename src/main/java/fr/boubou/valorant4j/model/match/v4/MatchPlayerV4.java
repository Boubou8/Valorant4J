package fr.boubou.valorant4j.model.match.v4;

import lombok.Data;

import javax.annotation.Nullable;

/**
 * @author Lubin "Boubou" B.
 * @date 20/11/2024 17:18
 */

@Data
public class MatchPlayerV4 {

    private String puuid;
    private String name;
    private String tag;
    private String team_id;
    private String platform;
    private String party_id;
    private MatchAgentV4 agent;
    private MatchStatsV4 stats;
    private MatchAbilitiesV4 ability_casts;
    private MatchTierV4 tier;
    private String card_id;
    private String title_id;
    private MatchPlayerCustomizationV4 customization;
    private int account_level;
    private int session_playtime_in_ms;
    private MatchBehaviorV4 behavior;
    private MatchEconomyV4 economy;

    @Data
    public static class MatchAgentV4 {
        private String id;
        private String name;
    }

    @Data
    public static class MatchStatsV4 {
        private int score;
        private int kills;
        private int deaths;
        private int assists;
        private int headshots;
        private int legshots;
        private int bodyshots;
        private StatsDamageV4 damage;

        @Data
        public static class StatsDamageV4 {
            private int dealt;
            private int received;
        }
    }

    @Data
    public static class MatchTierV4 {
        private String id;
        private String name;
    }

    @Data
    public static class MatchPlayerCustomizationV4 {
        private String card;
        private String title;
        private String preferred_level_border;
    }

    @Data
    public static class MatchBehaviorV4 {
        private int afk_rounds;
        private BehaviorFriendlyFireV4 friendly_fire;
        private int rounds_in_spawn;

        @Data
        public static class BehaviorFriendlyFireV4 {
            private int incoming;
            private int outgoing;
        }
    }

    @Data
    public static class MatchEconomyV4 {
        private MatchEconomySpentV4 spent;
        private MatchEconomySpentV4 loadout_value;

        @Data
        public static class MatchEconomySpentV4 {
            private int overall;
            private int average;
        }
    }
}
