package fr.boubou.valorant4j.model.mmr_history.v1;

import fr.boubou.valorant4j.model.match.v4.MatchMetadataMapV4;
import fr.boubou.valorant4j.model.mmr_history.MmrHistoryEntryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lubin "Boubou" B.
 * @date 31/01/2025 18:28
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class MmrHistoryV1Entry extends MmrHistoryEntryBase {

    private int currenttier;
    private String currenttierpatched;
    private ImagesV1 images;
    private String season_id;
    private int ranking_in_tier;
    private int mmr_change_to_last_game;
    private int date_raw;

    @Data
    public static class ImagesV1 {
        private String small;
        private String large;
        private String triangle_down;
        private String triangle_up;
    }
}