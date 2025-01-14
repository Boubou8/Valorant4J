package fr.boubou.valorant4j.model.match.v4;

import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Lubin "Boubou" B.
 * @date 20/11/2024 17:54
 */

@Data
public class MatchTeamV4 {

    private String team_id;
    private MatchTeamRoundsV4 rounds;
    private boolean won;
    @Nullable private MatchTeamRosterV4 premier_roster;

    @Data
    public static class MatchTeamRoundsV4 {
        private int won;
        private int lost;
    }

    @Data
    public static class MatchTeamRosterV4 {
        private String id;
        private String name;
        private String tag;
        private List<String> members;
        private MatchTeamRosterCustomizationV4 customization;

        @Data
        public static class MatchTeamRosterCustomizationV4 {
            private String icon;
            private String image;
            private String primary_color;
            private String secondary_color;
            private String tertiary_color;
        }
    }
}
