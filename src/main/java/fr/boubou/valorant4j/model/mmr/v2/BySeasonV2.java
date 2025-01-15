package fr.boubou.valorant4j.model.mmr.v2;

import lombok.Data;
import org.jetbrains.annotations.Nullable;
import java.util.List;

/**
 * @author Boubou
 * @date 14/01/2025 11:50
 */

@Data
public class BySeasonV2 {

    private BySeason e1a1;
    private BySeason e1a2;
    private BySeason e1a3;
    private BySeason e2a1;
    private BySeason e2a2;
    private BySeason e2a3;
    private BySeason e3a1;
    private BySeason e3a2;
    private BySeason e3a3;
    private BySeason e4a1;
    private BySeason e4a2;
    private BySeason e4a3;
    private BySeason e5a1;
    private BySeason e5a2;
    private BySeason e5a3;
    private BySeason e6a1;
    private BySeason e6a2;
    private BySeason e6a3;
    private BySeason e7a1;
    private BySeason e7a2;
    private BySeason e7a3;
    private BySeason e8a1;
    private BySeason e8a2;
    private BySeason e8a3;
    private BySeason e9a1;
    private BySeason e9a2;
    private BySeason e9a3;
    private BySeason e10a1;
    @Nullable private BySeason e10a2;
    @Nullable private BySeason e10a3;
    @Nullable private BySeason e11aIV;
    @Nullable private BySeason e11aV;
    @Nullable private BySeason e11aVI;

    @Data
    public static class BySeason {
        @Nullable private String error;
        private int wins;
        private int number_of_games;
        private int final_rank;
        private String final_rank_patched;
        private List<ActRankWinsV2> act_rank_wins;
        private boolean old;

        @Data
        public static class ActRankWinsV2 {
            private String patched_tier;
            private int tier;
        }
    }
}