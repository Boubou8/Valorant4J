package fr.boubou.valorant4j.model.match.v4;

import lombok.Data;

/**
 * @author Boubou
 * @date 20/11/2024 18:27
 */
@Data
public class MatchRoundPlayerV4 {

    private String puuid;
    private String name;
    private String tag;
    private String team;
}