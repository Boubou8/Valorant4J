package fr.boubou.valorant4j.model.match.v2;

import lombok.Data;

/**
 * @author Boubou
 * @date 11/01/2025 18:22
 */

@Data
public class MatchObserverV2 {

    private String puuid;
    private String name;
    private String tag;
    private MatchPlayerPlatformV2 platform;
    private MatchPlayerSessionPlaytimeV2 session_playtime;
    private String team;
    private int level;
    private String player_card;
    private String player_title;
    private String party_id;
}
