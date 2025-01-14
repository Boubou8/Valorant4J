package fr.boubou.valorant4j.model.match.v2;

import lombok.Data;

/**
 * @author Lubin "Boubou" B.
 * @date 20/11/2024 16:45
 */
@Data
public  class MatchMetadataV2 {

    private String map;
    private String game_version;
    private int game_length;
    private int game_start;
    private String game_start_patched;
    private int rounds_played;
    private String mode;
    private String mode_id;
    private String queue;
    private String season_id;
    private String platform;
    private String matchid;
    private PremierInfoV2 premier_info;
    private String region;
    private String cluster;

    @Data
    public static class PremierInfoV2 {
        private String tournament_id;
        private String matchup_id;
    }
}
