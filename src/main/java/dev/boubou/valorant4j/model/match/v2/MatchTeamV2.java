package dev.boubou.valorant4j.model.match.v2;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 11/01/2025 18:32
 */

@Data
public class MatchTeamV2 {

    @Nullable private Boolean has_won;
    @Nullable private Integer rounds_won;
    @Nullable private Integer rounds_lost;
    @Nullable private TeamRoasterV2 roster;

    @Data
    public static class TeamRoasterV2 {
        private List<String> members;
        private String name;
        private String tag;
        private TeamRoasterCustomizationV2 customization;

        @Data
        public static class TeamRoasterCustomizationV2 {
            private String icon;
            private String image;
            private String primary;
            private String secondary;
            private String tertiary;
        }
    }
}
