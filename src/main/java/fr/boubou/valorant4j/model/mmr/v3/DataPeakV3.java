package fr.boubou.valorant4j.model.mmr.v3;

import lombok.Data;

/**
 * @author Boubou
 * @date 14/01/2025 11:57
 */

@Data
public class DataPeakV3 {

    private SeasonIdShortComboV3 season;
    private String ranking_schema;
    private DataPeakTierV3 tier;
    private int rr;
}