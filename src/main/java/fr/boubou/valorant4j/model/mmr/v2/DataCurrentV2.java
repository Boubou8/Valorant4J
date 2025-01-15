package fr.boubou.valorant4j.model.mmr.v2;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * @author Boubou
 * @date 14/01/2025 11:47
 */

@Data
public class DataCurrentV2 {

    private int currenttier;
    @Nullable private String currenttierpatched;
    private MmrDataImagesV2 images;
    private int ranking_in_tier;
    private int mmr_change_to_last_game;
    private int elo;
    private int games_needed_for_rating;
    private boolean old;

    @Data
    public static class MmrDataImagesV2 {

        private String small;
        private String large;
        private String triangle_down;
        private String triangle_up;
    }
}